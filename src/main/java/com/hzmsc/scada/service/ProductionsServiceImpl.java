package com.hzmsc.scada.service;

import java.io.Console;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.DailyScheduleDao;
import com.hzmsc.scada.dao.ProductionsDao;
import com.hzmsc.scada.dao.dic.ScheduleTypeDao;
import com.hzmsc.scada.entity.DailySchedule;
import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.entity.Production;
import com.hzmsc.scada.entity.Productions;
import com.hzmsc.scada.entity.dic.ScheduleType;
import com.hzmsc.scada.utils.OperationUtils;

@Service
public class ProductionsServiceImpl implements ProductionsService{

	private static Logger logger = LoggerFactory.getLogger(ProductionService.class);

	@Autowired
	private ProductionsDao productionDao;
	@Autowired
	private DailyScheduleDao dailyScheduleDao;
	@Autowired
	private ScheduleTypeDao scheduleTypeDao;
	
	/**
	 * 计算两个设备状态之间的产量-重量
	 * 重量 = 长度/支数
	 * 获取最近一次的重量，得到累计重量
	 */
	Date date;
	long timeStart;
	int strDateBeginH ;  
	int strDateBeginM ;  
	int strDateBeginS ;  
	int strDateEndH ;  
	int strDateEndM ;  
	int strDateEndS ;
	
	//是否在该时间段
	Boolean lean;
	//记录班次ID
	int scheduleTypeId;
	public Productions calculateProduction(Productions lastProduction, Equipment lastEquipment, Equipment currentEquipment,Timestamp stamp) {
		
		        //纱线支数
		        int yarnCount = currentEquipment.getEquipmentConfigure().getYarnCount();  
				int spindleAmount = currentEquipment.getEquipmentConfigure().getSpindleAmount(); 

		        //整机锭数
				long lastLength = OperationUtils.catTowHalfWord(lastEquipment.getEquipmentStatus().getYarnLengthHighHalfWord(),
						lastEquipment.getEquipmentStatus().getYarnLengthLowHalfWord());
				
				//新长度
				long length = OperationUtils.catTowHalfWord(currentEquipment.getEquipmentStatus().getYarnLengthHighHalfWord(),
						currentEquipment.getEquipmentStatus().getYarnLengthLowHalfWord());
				
				System.out.println("对比："+"length:"+length+"  "+"lastLength:"+lastLength);
				
				/*
				 *   设备启动后  长度会每分钟增加 200
				 * */
				if(length >= lastLength){
					
					/*
					 * 增长长度 = 最终长度 - 初始长度
					 * */
					length = length - lastLength;
					System.out.println("length:"+length);
					
				}

				double weight = 0;

				if (yarnCount == 0) {
					logger.debug("weight=length/yarnCount, but yarnCount == 0!");
				} else {
					
					/*
					 *  产量 = 增长长度 / 纱线支数 * 锭数
					 * */
					weight = (double)length / yarnCount * spindleAmount;
					System.out.println("weight:"+weight);
				}
				  Productions production = new Productions();
				  
				  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				  Date dt = stamp;
				  String strDate = sdf.format(dt);  
				    // 截取当前时间时分秒  
				    int strDateH = Integer.parseInt(strDate.substring(11, 13));  
				    int strDateM = Integer.parseInt(strDate.substring(14, 16));  
				    int strDateS = Integer.parseInt(strDate.substring(17, 19));  
				   List<ScheduleType> listSchedule= scheduleTypeDao.findByScheduleTypestate();
				   for (Iterator iterator = listSchedule.iterator(); iterator.hasNext();) {
					ScheduleType scheduleType = (ScheduleType) iterator.next();
					
					// 截取开始时间时分秒  
				    strDateBeginH = Integer.parseInt(scheduleType.getBeginTime().toString().substring(0, 2));  
				    strDateBeginM = Integer.parseInt(scheduleType.getBeginTime().toString().substring(3, 5));  
				    strDateBeginS = Integer.parseInt(scheduleType.getBeginTime().toString().substring(6, 8));  
				    // 截取结束时间时分秒  
				    strDateEndH = Integer.parseInt(scheduleType.getEndTime().toString().substring(0, 2));  
				    strDateEndM = Integer.parseInt(scheduleType.getEndTime().toString().substring(3, 5));  
				    strDateEndS = Integer.parseInt(scheduleType.getEndTime().toString().substring(6, 8));  
					
				    if(strDateEndH == 00 ){
				    	
				    	strDateEndH = 24;
				    }
				    
				    if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {  
				        // 当前时间小时数在开始时间和结束时间小时数之间  
				        if (strDateH > strDateBeginH && strDateH < strDateEndH) {  
				            lean = true;  
				            scheduleTypeId = scheduleType.getScheduleTypeId();
				            break;
				            // 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间  
				        } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM  
				                && strDateM <= strDateEndM) {  
				        	lean = true;  
				        	scheduleTypeId = scheduleType.getScheduleTypeId();
				        	break;
				            // 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间  
				        } else if (strDateH == strDateBeginH && strDateM == strDateBeginM  
				                && strDateS >= strDateBeginS && strDateS <= strDateEndS) {  
				        	lean = true;  
				        	scheduleTypeId = scheduleType.getScheduleTypeId();
				        	break;
				        }  
				        // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数  
				        else if (strDateH >= strDateBeginH && strDateH == strDateEndH  
				                && strDateM <= strDateEndM) {  
				        	lean = true;  
				        	scheduleTypeId = scheduleType.getScheduleTypeId();
				        	break;
				            // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数  
				        } else if (strDateH >= strDateBeginH && strDateH == strDateEndH  
				                && strDateM == strDateEndM && strDateS <= strDateEndS) {  
				        	lean = true;  
				        	scheduleTypeId = scheduleType.getScheduleTypeId();
				        	break;
				        } else {  
				        	lean = false;  
				        }  
				    } else {  
				    	lean = false;   
				    }  
				}
				//System.out.println("lean："+lean);  
				if(lean == true){
					System.out.println("lastProduction.getEarlyWeight()："+lastProduction.getEarlyWeight());
					weight = weight + lastProduction.getEarlyWeight();//加上最近一次的重量，获得累计重量。
					System.out.println("累计后的产量:"+weight);
					//System.out.println("早班weight："+weight);
					production.setEquipmentId(currentEquipment.getEquipmentId());
					production.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			    	production.setEarlyWeight(weight);
			    	production.setScheduleTypeId(scheduleTypeId);
				}    
		return production;
	}

	@Override
	public Productions createProductions(Productions production) {
		return productionDao.createProductions(production);
	}

	@Override
	public void updateProductions(Productions production) {
		productionDao.updateProductions(production);		
	}

	@Override
	public int countOfEquipmentId(String beginDay, String endDay ,int equipmentId, int scheduleTypeId) {
		
		return productionDao.countOfEquipmentId(beginDay, endDay ,equipmentId, scheduleTypeId);
	}

	@Override
	public Productions findByEquipmentIdLatestBeforeTime(String beginDay, String endDay ,int equipmentId, int scheduleTypeId) {
		
		return productionDao.findByEquipmentIdLatestBeforeTime(beginDay, endDay ,equipmentId, scheduleTypeId);
	}

	@Override
	public List productionsfindall(String beginDay, String endDay) {
		
		return productionDao.productionsfindall(beginDay,endDay);
	}

	public List listproductions(){
		
		return productionDao.listproductions();
	}


	
}
