package com.hzmsc.scada.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzmsc.scada.entity.Employee;
import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.entity.view.EquipmentView;
import com.hzmsc.scada.service.EquipmentService;
import com.hzmsc.scada.service.view.EquipmentViewService;

@RestController
public class EquipmentRestController {
	
	private static Logger logger = LoggerFactory.getLogger(EquipmentRestController.class);
	

	@Autowired
	private EquipmentViewService equipmentViewService;
	
	@Autowired
	private EquipmentService equipmentService;
	
	@RequestMapping(value = "/equipment", method = RequestMethod.GET)
	public List<EquipmentView> equipmentAll() {

		return equipmentViewService.findAll();
	}
	@RequestMapping(value = "/equipment", method = RequestMethod.POST)
	public int receiveEquipment(@RequestBody Equipment equipment) {
		
		logger.debug("equipment received:{}", equipment);
		return equipmentService.updateEquipment(equipment);
	}

	@RequestMapping(value = "/equipment/{equipmentId}", method = RequestMethod.GET)
	public Equipment equipmentById(@PathVariable int equipmentId) {
		
		return equipmentService.findById(equipmentId);
	}
	
	
	//设备信息 增删改
	@RequestMapping(value = "/equipmentUpdate", method = RequestMethod.PUT)
	public int equipmentUpdate(@RequestBody EquipmentView equipment){
		
		//System.out.println(equipmentService.updateEquipment(equipment));
		
		return equipmentViewService.updateEquipmentView(equipment);
	}
	
	@RequestMapping(value = "/equipmentCreate", method = RequestMethod.POST)
	public int equipmentCreate(@RequestBody EquipmentView equipment){
		//System.out.println("equipment :"+ equipment.getUnitIdentifier());
		equipment.setIsActived("1");
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(time);
			equipment.setCreatedTime(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//equipment.setEquipmentStatus(0);
		//equipmentViewService.createEquipmentView(equipment);
		//int i = equipmentService.createEquipment(equipment);
		//System.out.println("创建id:"+ i);
		return equipmentViewService.createEquipmentView(equipment);
	}
	
	
	@RequestMapping(value = "/equipmentDelete", method = RequestMethod.POST)
	public void equipmentDelete(@RequestBody EquipmentView equipment){
		
		equipmentViewService.deleteEquipmentView(equipment);
	}


}
