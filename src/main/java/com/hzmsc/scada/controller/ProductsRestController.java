package com.hzmsc.scada.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzmsc.scada.entity.Production;
import com.hzmsc.scada.entity.Productions;
import com.hzmsc.scada.service.DailyScheduleService;
import com.hzmsc.scada.service.ProductionService;
import com.hzmsc.scada.service.ProductionsService;

@RestController
public class ProductsRestController {
	
	
	private static Logger logger = LoggerFactory.getLogger(EventRestController.class);

	
	@Autowired
	private ProductionService productionService;
	@Autowired
	private ProductionsService productionsService;
	@Autowired
	private DailyScheduleService dailyScheduleService;
	
	/*
	 * 数据报表     设备方面
	 * */
	
	/*
	 * 设备生产统计列表
	 * */
    
	/*
	 * 查询  Proudctions表
	 * */
	@RequestMapping(value = "/allproductions/{beginDay}/{endDay}", method = RequestMethod.GET)
	public List<Productions> productionsfindall(@PathVariable String beginDay, @PathVariable String endDay){
		logger.debug("开始时间:", new Date());
		/*System.out.println("allproductions开始时间:"+new Date());
		System.out.println("beginDay:"+beginDay);
		System.out.println("endDay:"+endDay);*/
		System.out.println("endDay:"+endDay);
		Calendar   calendar   =   new   GregorianCalendar(); 
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
		Date date;
		String str = null;
		try {
			date = sdf.parse(endDay);
			calendar.setTime(date); 
			calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
			date = calendar.getTime();
			endDay = sdf.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //System.out.println("增加一天后的结束日期为："+ endDay);
		List<Object> listsum = productionsService.productionsfindall(beginDay,endDay);
		//System.out.println("sql结束时间："+new Date());
		List<Productions> listspro = productionsService.listproductions();
		//System.out.println("listsum:"+listsum);
		for (int i = 0; i < listspro.size(); i++) { //8次 0-7
			Productions d = listspro.get(i);
			for (int p = 0; p < listsum.size(); p++) {
				  Map map = (Map) listsum.get(i); 
 				  d.setEquipmentId((int) map.get("equipmentId"));
 				  d.setEquipmentName(map.get("equipmentName").toString());
 				  System.out.println("equipmentId :"+map.get("equipmentId") +"earlyWeight:"+ map.get("earlyWeight"));
 				  if(map.get("earlyWeight") == null ){
 					 d.setEarlyWeight(0);
 				  }else{
 				  d.setEarlyWeight((double)map.get("earlyWeight"));
 				  }
 				  if(map.get("midWeight") == null){
 					 d.setMidWeight(0);
 				  }else{
 					 d.setMidWeight((double)map.get("midWeight"));
 				  }
 				  if(map.get("lateWeight") == null){
 					 d.setLateWeight(0);
 				  }else{
 					 d.setLateWeight((double)map.get("lateWeight"));
 				  }
 				  if(map.get("lastWeight") == null){
 					 d.setLastWeight(0);
 				  }else{
 					 d.setLastWeight((double)map.get("lastWeight"));
 				  }
 				  
			}	
		}
		logger.debug("allproductions结束时间:", new Date());
		//System.out.println("allproductions结束时间:"+new Date());
		return listspro;
	}
	
	/*
	 * 查询出所有设备信息 以及它们每台的最终产量
	 * */
		@RequestMapping(value = "/sumproductions/{beginDay}/{endDay}", method = RequestMethod.GET)
		public List<Production> productionSumBetweenDays(@PathVariable String beginDay, @PathVariable String endDay){
			System.out.println("sumproductions开始时间:"+new Date());
			List<Object> listsum = productionService.sumproduction(beginDay, endDay);
		    /*
		     * 查询出每台设备信息
		     * */
			List<Production> listspro = productionService.listproduction();
			
			
			for (int i = 0; i < listspro.size(); i++) {
				Production d = listspro.get(i);
				for (int p = 0; p < listsum.size(); p++) {
					  Map map = (Map) listsum.get(i); 
	 				  d.setEquipmentId((int) map.get("equipmentId"));
	 				  d.setEquipmentName(map.get("equipmentName").toString());
					  int maxWeight = Float.valueOf(String.valueOf(map.get("m"))).intValue();
					  int minWeight = Float.valueOf(String.valueOf(map.get("i"))).intValue();
					  int sumWeight =  maxWeight - minWeight;
					  
					  d.setListequipsum(sumWeight);
					 
				}	
			}
			System.out.println("sumproductions结束时间:"+new Date());
			return listspro;
		}
}
