package com.hzmsc.scada.scheduler;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.entity.EquipmentBasicInfo;
import com.hzmsc.scada.entity.EquipmentConfigure;
import com.hzmsc.scada.entity.EquipmentOperationRate;
import com.hzmsc.scada.entity.EquipmentStatus;
import com.hzmsc.scada.entity.Event;
import com.hzmsc.scada.entity.Production;
import com.hzmsc.scada.entity.Productions;
import com.hzmsc.scada.entity.dic.EventType;
import com.hzmsc.scada.lib.modbus.ModbusClientSlave;
import com.hzmsc.scada.service.EquipmentBasicInfoService;
import com.hzmsc.scada.service.EquipmentConfigureService;
import com.hzmsc.scada.service.EquipmentOperationRateService;
import com.hzmsc.scada.service.EquipmentService;
import com.hzmsc.scada.service.EquipmentStatusService;
import com.hzmsc.scada.service.EventService;
import com.hzmsc.scada.service.ProductionService;
import com.hzmsc.scada.service.ProductionsService;
import com.hzmsc.scada.service.dic.EventTypeService;
import com.hzmsc.scada.service.dic.ScheduleTypeService;
import com.hzmsc.scada.utils.OperationUtils;

import ch.qos.logback.core.net.SyslogOutputStream;

@Component
public class ScheduledTasks {

	private static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private EquipmentOperationRateService equipmentOperationRateService;
	@Autowired
	private ProductionService productionService;
	@Autowired
	private ProductionsService productionsService;
	@Autowired
	private EventService eventService;
	@Autowired
	private EventTypeService eventTypeService;
	@Autowired
	private EquipmentStatusService equipmentStatusService;
	@Autowired
	private EquipmentConfigureService equipmentConfigureService;
	@Autowired
	private EquipmentBasicInfoService equipmentBasicInfoService;
	@Autowired
	private ScheduleTypeService scheduleTypeService;

	int sid = 0;

	private List<Equipment> lastEquipments;
	private List<Equipment> lastEquipmentsForEvents;

	public List<Equipment> getLastEquipments() {
		return lastEquipments;
	}

	public void setLastEquipments(List<Equipment> lastEquipments) {
		this.lastEquipments = lastEquipments;
	}

	public List<Equipment> getLastEquipmentsForEvents() {
		return lastEquipmentsForEvents;
	}

	public void setLastEquipmentsForEvents(List<Equipment> lastEquipmentsForEvents) {
		this.lastEquipmentsForEvents = lastEquipmentsForEvents;
	}

	@PostConstruct
	public void initData() {
		List<Equipment> equipments = equipmentService.findAll();
		this.setLastEquipments(equipments);
		this.setLastEquipmentsForEvents(equipments);
	}

	/**
	 * 定时读取设备数据，并保存到数据库Equipments中 该定时器只在本地启用，云端禁用
	 */
	//@Scheduled(initialDelay=2000, fixedDelay=1000)
	public void readDeviceData() {

		List<Equipment> equipmentList = equipmentService.findAll();

		HashMap<String, List<Equipment>> ipEquipmentHashMap = new HashMap<String, List<Equipment>>();

		ipEquipmentHashMap = equipmentService.listToHashMapByIp(equipmentList);

		Set<String> ipEquipmentHashMapKeySet = ipEquipmentHashMap.keySet();

		for (String ipAddress : ipEquipmentHashMapKeySet) {
			ModbusClientSlave modbusClientSlave = new ModbusClientSlave(ipAddress, 502);
			List<Equipment> list = ipEquipmentHashMap.get(ipAddress);
			equipmentService.readEquipmentsFromDevice(modbusClientSlave, list);
		}

	}
	
	/**
	 * 将数据库Equipments表中的数据，同步发送到云服务器 该定时器只在本地启用，云端禁用
	 * ！！！此项禁用，须有管理员慎重打开！！！
	 */
	//@Scheduled(initialDelay=3000, fixedDelay=1000)
	public void sendDataToCloud() {
		//logger.debug("send data to cloud");
		//String url = "";
		//String url = "http://139.224.56.216:8080/ii/equipment/"; 
		String url = "http://www.jmtis.com/ii/equipment/"; 
		RestTemplate restTemplate = new RestTemplate();
		for(Equipment lastEquipment : this.getLastEquipments()){
			Equipment currentEquipment = equipmentService.findById(lastEquipment.getEquipmentId());
			HttpEntity<String> request = new HttpEntity<String>(currentEquipment.toString(), getHeaders());
		    
			int i = restTemplate.postForObject(url, request, Integer.class);
			   
			logger.debug("---:{}", request.toString());
			logger.debug("returned equipmentId:{}", i);
		}
	}

	/**
	 * 定时将数据库Equipments表中的数据，保存到EquipmentStatus、
	 * EquipmentConfigure和EquipmentBasicInfo中；
	 * 根据EquipmentStauts.getEquipmentStatus()，计算开机时间，保存到EquipmentOperationRate中;
	 * 根据纱线长度和支书，计算重量，保存到Production中。
	 */
	@Scheduled(initialDelay=5000, fixedDelay=60000)
	public void calculateProduction() {
		List<Equipment> currentEquipments = new ArrayList<Equipment>();
		for(Equipment lastEquipment : this.getLastEquipments()){
			int equipmentId = lastEquipment.getEquipmentId();
			Equipment currentEquipment = equipmentService.findById(equipmentId);
			
			logger.debug("lastTime-currentTime:{}-{}",lastEquipment.getModifiedTime(),currentEquipment.getModifiedTime());
			
			//计算开机率
			EquipmentOperationRate lastEquipmentOperationRate = new EquipmentOperationRate(0,0,0,0);
			if(equipmentOperationRateService.countOfFindByEquipmentId(equipmentId) > 0){
				lastEquipmentOperationRate = equipmentOperationRateService.findByEquipmentIdLatestBeforeTime(equipmentId);
			}
			/*if(equipmentId == 4){
				logger.debug("lastEquipmentOperationRate:{}",lastEquipmentOperationRate);
			}*/
			/*EquipmentOperationRate equipmentOperationRate =
					equipmentOperationRateService.calculateOperationRate(lastEquipmentOperationRate, lastEquipment, currentEquipment);
			equipmentOperationRate.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			equipmentOperationRateService.createEquipmentOperationRate(equipmentOperationRate);*/
			//---------------         王老师的  ----------------------
			//计算产量
			/*Production lastProduction = new Production(0);
			
			//判断是否已有产量
			if(productionService.countOfEquipmentId(equipmentId) > 0){
				lastProduction = productionService.findByEquipmentIdLatestBeforeTime(equipmentId);
			}
			logger.debug("lastProduction:{}",lastProduction);
			
			Production production =
					productionService.calculateProduction(lastProduction, lastEquipment, currentEquipment);
			//当前时间  也是数据创建时间
			//System.out.println("!!!!"+new Timestamp(System.currentTimeMillis()).getTime());
			production.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			productionService.createProduction(production);*/
			//---------------         王老师的     结束 ----------------------
			
			
			Productions lastProductions = new Productions(0,0,0,0);
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date data = new Date();
			String s=sdf.format(data);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(data);
			calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
			data=calendar.getTime();
			String ss=sdf.format(data);
			//System.out.println(productionsService.countOfEquipmentId(s,ss,equipmentId));
			
			//根据当前时间  查询对应的班次ID
			sid = scheduleTypeService.findByScheduleTypeTime(data);
			//System.out.println("sid:"+sid);
			//System.out.println("productionsService:"+productionsService.countOfEquipmentId(s, ss, equipmentId, sid));
			//先判断是否有数据
			//System.out.println("productionsService.countOfEquipmentId(s, ss ,equipmentId) :"+productionsService.countOfEquipmentId(s, ss ,equipmentId,sid));
			if(sid != 0){
			if(productionsService.countOfEquipmentId(s, ss, equipmentId, sid) > 0){
				//System.out.println(lastEquipment);
				//查询出当天的
				/*System.out.println("s:"+s);
				System.out.println("ss:"+ss);
				System.out.println("equipmentId:"+equipmentId);*/
				lastProductions = productionsService.findByEquipmentIdLatestBeforeTime(s , ss , equipmentId, sid);
				logger.debug("lastProduction:{}",lastProductions);
				Productions productions =
						productionsService.calculateProduction(lastProductions, lastEquipment, currentEquipment,new Timestamp(System.currentTimeMillis()));
				productions.setProductionId(lastProductions.getProductionId());
				//当前时间  也是数据创建时间
				//System.out.println("!!!!"+new Timestamp(System.currentTimeMillis()).getTime());
				//System.out.println(productions);
				productions.setElapsedTime((new Timestamp(System.currentTimeMillis())));
				//System.out.println("修改 getScheduleTypeId：" +lastProductions.getScheduleTypeId());
				productions.setScheduleTypeId(lastProductions.getScheduleTypeId());
				productionsService.updateProductions(productions);
				
			}else{//System.out.println("执行创建");
				Productions productions =
						productionsService.calculateProduction(lastProductions, lastEquipment, currentEquipment,new Timestamp(System.currentTimeMillis()));
				productions.setCreatedTime(new Timestamp(System.currentTimeMillis()));
				//System.out.println("创建 sid:"+sid);
				productions.setScheduleTypeId(sid);
				productionsService.createProductions(productions);
			}
			}
            /*
             * 陈通  数据报表修改测试
             * */		
//			Productions lastProductions = new Productions();
//			//先进行查询  判断 Productions表是否已有数据   来判断进行 创建还是修改
//		    if(productionsService.countOfEquipmentId(equipmentId) > 0){
//		    	
//		    	
//		    	lastProductions = productionsService.findByEquipmentIdLatestBeforeTime(equipmentId,new Timestamp(System.currentTimeMillis()));
//			}
//		   
//		    Timestamp stamp =  new Timestamp(System.currentTimeMillis());
//		    Productions productions =
//					productionsService.calculateProduction(lastProduction, lastEquipment, currentEquipment,stamp);
//					
//				
			//Time t = new Time(System.currentTimeMillis());	
			
			//System.out.println("afafasfaf::"+t.toString());
		/*	Calendar now = Calendar.getInstance();
			 System.out.println("年：" + now.get(Calendar.YEAR));
		        System.out.println("月：" + (now.get(Calendar.MONTH) + 1));
		        System.out.println("日：" + now.get(Calendar.DAY_OF_MONTH));
		        System.out.println("时：" + now.get(Calendar.HOUR_OF_DAY));
		        System.out.println("分：" + now.get(Calendar.MINUTE));
		        System.out.println("秒：" + now.get(Calendar.SECOND));
			System.out.println("ct测试："+new Timestamp(System.currentTimeMillis()));*/
			
			
			
			/*
			 * 测试结束
			 * */
			//保存数据
			EquipmentStatus equipmentStatus = currentEquipment.getEquipmentStatus();
			equipmentStatus.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			equipmentStatusService.createEquipmentStatus(equipmentStatus);
			
			EquipmentConfigure equipmentConfigure = currentEquipment.getEquipmentConfigure();
			equipmentConfigure.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			equipmentConfigureService.createEquipmentConfigure(equipmentConfigure);
			
			EquipmentBasicInfo equipmentBasicInfo = currentEquipment.getEquipmentBasicInfo();
			equipmentBasicInfo.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			equipmentBasicInfoService.createEquipmentBasicInfo(equipmentBasicInfo);
			
			//将新的状态添加到新的列表
			currentEquipments.add(currentEquipment);			
		}
		
		this.setLastEquipments(currentEquipments);//将新的设备状态列表保存为last记录
	}
	
	/**
	 * 定时检查数据库Equipments表，监控变化，产生事件，保存到数据库事件表中
	 * EquipmentConfigure发生变化，则产生配置更新事件；
	 * EquipmentBasicInfo发生变化，则产生系统升级事件；
	 * 状态EquipmentStauts.getEquipmentStatus()发生变化，则产生状态事件；
	 * 状态EquipmentStatus.getMalfunction()发生变化，则产生告警事件。
	 * dic_events,events(记录完整的Equipments信息备查)
	 */
	@Scheduled(initialDelay=3000, fixedDelay=1000)
	public void eventsMonitor() {
		logger.debug("events");
		List<Equipment> currentEquipments = new ArrayList<Equipment>();
		for(Equipment lastEquipment : this.getLastEquipmentsForEvents()){
			Equipment currentEquipment = equipmentService.findById(lastEquipment.getEquipmentId());
			Event event = new Event(currentEquipment);
			
			String eventName = "未定义事件";
			int eventTypeId = -1;
			String eventTypeCode = "unknown";
			String eventTypeName = "unknown";
			String eventLevelCode = "-1";
			String eventLevelName = "未定义等级";
			
			/*
			 * 状态变化事件
			 */
			int lastStatus = lastEquipment.getEquipmentStatus().getEquipmentStatus();
			int currentStatus = currentEquipment.getEquipmentStatus().getEquipmentStatus();
			logger.debug("lastStatus-currentStatus:{}-{}",lastStatus,currentStatus);
			if(lastStatus != currentStatus){
				int statusId = currentStatus;
				String myeventTypeCode = "ds" + statusId;
				String myeventLevelCode = "11";
				String myeventLevelName = "状态变化";
				if(eventTypeService.countByCode(myeventTypeCode) == 1){
					EventType eventType = eventTypeService.findByCode(myeventTypeCode);
					
					eventName = eventType.getEventTypeCN();
					eventTypeId = eventType.getEventTypeId();
					eventTypeCode = myeventTypeCode;
					eventTypeName = eventType.getEventTypeName();
					eventLevelCode = myeventLevelCode;
					eventLevelName = myeventLevelName;
					
					event.setEventName(eventName);
					event.setEventTypeId(eventTypeId);
					event.setEventTypeCode(eventTypeCode);
					event.setEventTypeName(eventTypeName);
					event.setEventLevelCode(eventLevelCode);
					event.setEventLevelName(eventLevelName);
					
					eventService.createEvent(event);
				}				
			}
			
			//告警事件
			int lastMalfunction = lastEquipment.getEquipmentStatus().getMalfunction();
			int currentMalfunction = currentEquipment.getEquipmentStatus().getMalfunction();
			logger.debug("lastMalfunction-currentMalfunction:{}-{}",lastMalfunction,currentMalfunction);
			if(lastMalfunction != currentMalfunction){
				int malfunction = currentMalfunction;
				String myeventTypeCode = "fi" + OperationUtils.getLowIndexByteFromInt(malfunction, 1);
				String myeventLevelCode = "" + OperationUtils.getLowIndexByteFromInt(malfunction, 2);
				String myeventLevelName = "告警";
				if(eventTypeService.countByCode(myeventTypeCode) == 1){
					EventType eventType = eventTypeService.findByCode(myeventTypeCode);
					
					eventName = eventType.getEventTypeCN();
					eventTypeId = eventType.getEventTypeId();
					eventTypeCode = myeventTypeCode;
					eventTypeName = eventType.getEventTypeName();
					eventLevelCode = myeventLevelCode;
					eventLevelName = myeventLevelName;
					
					event.setEventName(eventName);
					event.setEventTypeId(eventTypeId);
					event.setEventTypeCode(eventTypeCode);
					event.setEventTypeName(eventTypeName);
					event.setEventLevelCode(eventLevelCode);
					event.setEventLevelName(eventLevelName);
					
					eventService.createEvent(event);
				}
			}
			
			//参数变化（工艺更新） 事件
			if(!lastEquipment.getEquipmentConfigure().equals(currentEquipment.getEquipmentConfigure())){
			
				String myeventTypeCode = "ec1";
				String myeventLevelCode = "21";
				String myeventLevelName = "工艺更新";
				if(eventTypeService.countByCode(myeventTypeCode) == 1){
					EventType eventType = eventTypeService.findByCode(myeventTypeCode);
					
					eventName = eventType.getEventTypeCN();
					eventTypeId = eventType.getEventTypeId();
					eventTypeCode = myeventTypeCode;
					eventTypeName = eventType.getEventTypeName();
					eventLevelCode = myeventLevelCode;
					eventLevelName = myeventLevelName;
					
					event.setEventName(eventName);
					event.setEventTypeId(eventTypeId);
					event.setEventTypeCode(eventTypeCode);
					event.setEventTypeName(eventTypeName);
					event.setEventLevelCode(eventLevelCode);
					event.setEventLevelName(eventLevelName);
					
					eventService.createEvent(event);
				}
			}
			
			//软件升级事件
			if(!lastEquipment.getEquipmentBasicInfo().equals(currentEquipment.getEquipmentBasicInfo())){
				String myeventTypeCode = "eb1";
				String myeventLevelCode = "31";
				String myeventLevelName = "系统升级";
				
				if(eventTypeService.countByCode(myeventTypeCode) == 1){
					EventType eventType = eventTypeService.findByCode(myeventTypeCode);
					
					eventName = eventType.getEventTypeCN();
					eventTypeId = eventType.getEventTypeId();
					eventTypeCode = myeventTypeCode;
					eventTypeName = eventType.getEventTypeName();
					eventLevelCode = myeventLevelCode;
					eventLevelName = myeventLevelName;
					
					event.setEventName(eventName);
					event.setEventTypeId(eventTypeId);
					event.setEventTypeCode(eventTypeCode);
					event.setEventTypeName(eventTypeName);
					event.setEventLevelCode(eventLevelCode);
					event.setEventLevelName(eventLevelName);
					
					eventService.createEvent(event);
				}
			}
			
			currentEquipments.add(currentEquipment);
			
		}
		
		this.setLastEquipmentsForEvents(currentEquipments);

	}
	
	private static HttpHeaders getHeaders(){
    	String plainCredentials="restapi:restfulapi";
    	String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Authorization", "Basic " + base64Credentials);
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	return headers;
    }

	
	/*
	 * 定时读取设备数据，并保存到数据库dailyschedule中 该定时器只在本地启用，云端禁用
	 * */
	//@Scheduled(initialDelay=5000, fixedDelay=60000)
    public void calculateProductioncreateDaily(){
    	
    	Date date = new Date();
    	
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
    		/*
    		 * SimpleDateFormat 中  parse  将String  转换为 date
    		 * */
			Date d1 = format.parse( "2017-01-12 16:49:00");
			long dt = d1.getTime() - date.getTime();
			System.out.println("dt"+dt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	System.out.println("date:"+date);
    
    	
    }
}