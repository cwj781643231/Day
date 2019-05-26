package com.hzmsc.scada.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzmsc.scada.entity.DailySchedule;
import com.hzmsc.scada.entity.Production;
import com.hzmsc.scada.entity.dic.ScheduleType;
import com.hzmsc.scada.service.DailyScheduleService;
import com.hzmsc.scada.service.ProductionService;
/*excel*/
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

import com.hzmsc.scada.entity.Production;
import com.hzmsc.scada.excel.ExportExcel;


@RestController
public class ProductRestController {

	@Autowired
	private ProductionService productionService;
	@Autowired
	private DailyScheduleService dailyScheduleService;

	
	
	@RequestMapping(value = "/production", method = RequestMethod.GET)
	public List<Production> productionAll() {
		return productionService.findAll();
	}

	@RequestMapping(value = "/production/{productionCode}", method = RequestMethod.GET)
	public List<Production> productionByProductionCode(@PathVariable String productionCode) {
		try {
			productionCode = new String(productionCode.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) { // TODO Auto-generated catch
													// block
			e.printStackTrace();
		}
		//System.out.println("ProductionRestController:production.get");
		//System.out.println("productionCode:" + productionCode);
		return null;
		//return productionService.findByProductionCode(productionCode);
	}

	@RequestMapping(value = "/production", method = RequestMethod.PUT)
	public List<Production> productionUpdate(@RequestBody Production production) {
		// System.out.println("ProductionRestController:/production.post"+production);
		productionService.updateProduction(production);
		return productionService.findAll();
	}

	@RequestMapping(value = "/production", method = RequestMethod.POST)
	public List<Production> productionCreate(@RequestBody Production production) {
		// System.out.println("ProductionRestController:/production.post"+production);
		productionService.createProduction(production);
		return productionService.findAll();
	}
	


	@RequestMapping(value = "/productionDelete", method = RequestMethod.POST)
	public List<Production> productionDelete(@RequestBody Production production) {
		// System.out.println("ProductionRestController:/production.post"+production);
		productionService.deleteProduction(production);
		return productionService.findAll();
	}
	
	
	/*
	 * 数据报表     设备方面
	 * */
	
	/*
	 * 所有设备的最终产量之和
	 * */
	@RequestMapping(value = "/allproduction/{beginDay}/{endDay}", method = RequestMethod.GET)
	public int allproduction(@PathVariable String beginDay, @PathVariable String endDay){
        
		//int all = productionService.allproduction(beginDay, endDay);
		List<Object> listsum = productionService.sumproduction(beginDay, endDay);
		List<Production> listspro = productionService.listproduction();
		int all = 0;
		
			for (int p = 0; p < listsum.size(); p++) {
				  Map map = (Map) listsum.get(p); 
				  int maxWeight = Float.valueOf(String.valueOf(map.get("m"))).intValue();
				  int minWeight = Float.valueOf(String.valueOf(map.get("i"))).intValue();
				  int sumWeight =  maxWeight - minWeight;
				  all = all + sumWeight;
			}	
		
		return all;
	}
	
	/*
	 * 查询出所有设备信息 以及它们每台的最终产量
	 * */
		@RequestMapping(value = "/sumproduction/{beginDay}/{endDay}", method = RequestMethod.GET)
		public List<Production> productionSumBetweenDays(@PathVariable String beginDay, @PathVariable String endDay){
			//查询出该时间段内设备信息
			//List<DailySchedule> list= dailyScheduleService.sumproduction(beginDay, endDay);
			//System.out.println("查询设备总产量时传入的时间："+beginDay+"~~~"+ endDay);
			
			//查询出该时间段内每台设备的总产量
			//List<Object> listsum = dailyScheduleService.listint(beginDay, endDay);
			//System.out.println("设备总产量"+listsum);
			
			List<Object> listsum = productionService.sumproduction(beginDay, endDay);
			
			
			//List<Production> listspro = new ArrayList<Production>(); 
   
			List<Production> listspro = productionService.listproduction();
			//System.out.println("listspro:"+listspro.size()+"listsum:"+listsum.size());
			//int allduction = 0;
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
					 
					  //allduction = allduction + sumWeight;
				}	
				//d.setAllduction(allduction);
				//System.out.println(d.getAllduction());
			}
			//System.out.println(listspro.get(2).getEquipmentName());
			//System.out.println("设备信息"+dailyScheduleService.sumproduction(beginDay, endDay));
			return listspro;
		}
		
		
		/*
		 * 根据设备ID查询出该设备每一天的总产量      
		 * */
			@RequestMapping(value = "/sumequipbyid/{beginDay}/{endDay}/{equipmentId}", method = RequestMethod.GET)
			public List<Production> findbyid(@PathVariable String beginDay, @PathVariable String endDay,@PathVariable int equipmentId){
				//查询出这台机器的工作 天数
				//System.out.println("设备详情ID："+equipmentId);
				/*List<Production> listsum= productionService.sumequipbyid(beginDay, endDay, equipmentId);
				
				List<Object> listsumequipby = productionService.listsumequipbyid(beginDay, endDay, equipmentId);*/
				
				//查看哪几天工作了
				List<Object> workdates = productionService.findbyDateEquipmentId(beginDay, endDay, equipmentId);
             System.out.println("workdates:"+workdates);
				List<Production> listpro = new ArrayList<Production>();
				for (int i = 0; i < workdates.size(); i++) {
					
					Map map = (Map)workdates.get(i);
					Production production = productionService.findByEquipmentIdFirstAfterTime(Float.valueOf(String.valueOf(map.get("EquipmentId"))).intValue());

					
					 //传入 某一天的日期号和设备ID   得到某一天的最初产量和最终产量
					 List<Object> workdate = productionService.findbyOneDayEquipmentId(
							 map.get("createdTime").toString(), Float.valueOf(String.valueOf(map.get("EquipmentId"))).intValue());
					production.setDetailsTime(map.get("createdTime").toString());
					for (int p = 0; p < workdate.size(); p++) {
						  Map workdatemap = (Map) workdate.get(p); 
						  int maxWeight = Float.valueOf(String.valueOf(workdatemap.get("m"))).intValue();
						  int minWeight = Float.valueOf(String.valueOf(workdatemap.get("i"))).intValue();
						  int sumWeight =  maxWeight - minWeight;
						  
						  production.setListsumbyid(sumWeight);
						  production.setEquipmentName(workdatemap.get("equipmentName").toString());
						 
						  
						/*  for (Iterator iterator = listpro.iterator(); iterator.hasNext();) {
							  Production duction = (Production) iterator.next();
							  
							  duction.setListsumbyid(sumWeight);
							  duction.setEquipmentName(workdatemap.get("equipmentName").toString());
						}*/
						  listpro.add(production);
					}	
				}
			//System.out.println("listpro:"+listpro);	
		return listpro;
	}
}
