package com.hzmsc.scada.service.view;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.entity.EquipmentOperationRate;
import com.hzmsc.scada.entity.view.OperationRateView;
import com.hzmsc.scada.service.EquipmentOperationRateService;
import com.hzmsc.scada.service.EquipmentService;

@Service
public class OperationRateViewServiceImpl implements OperationRateViewService {
	private Logger logger = LoggerFactory.getLogger(OperationRateViewServiceImpl.class);

	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private EquipmentOperationRateService equipmentOperationRateService;
	
	public OperationRateView findById(int equipmentId) {
		
		Equipment equipment = equipmentService.findById(equipmentId);
		OperationRateView operationRateView = new OperationRateView(equipment);
		
		EquipmentOperationRate equipmentOperationRate = equipmentOperationRateService.getOperationRate(equipmentId);
		
		operationRateView.setOnTime(equipmentOperationRate.getOnTime());
		operationRateView.setOffTime(equipmentOperationRate.getOffTime());		
		operationRateView.setOperationTime(equipmentOperationRate.getOperationTime());		
		operationRateView.setWaitTime(equipmentOperationRate.getWaitTime());
		logger.debug("Ontime:{}",equipmentOperationRate.getOnTime());
		logger.debug("Offtime:{}",equipmentOperationRate.getOffTime());
		logger.debug("Operationtime:{}",equipmentOperationRate.getOperationTime());
		logger.debug("Waittime:{}",equipmentOperationRate.getWaitTime());
		
		return operationRateView;
	}

	public List<OperationRateView> findAll() {
		List<OperationRateView> listOperationRateView = new ArrayList<OperationRateView>();
		List<Equipment> listEquipment = equipmentService.findAll();
		for(Equipment equipment : listEquipment){
			OperationRateView operationRateView = this.findById(equipment.getEquipmentId());
			listOperationRateView.add(operationRateView);
		}
		return listOperationRateView;
	}

	public OperationRateView findByIdBewteenTime(int equipmentId, Timestamp beginTime, Timestamp endTime) {
		Equipment equipment = equipmentService.findById(equipmentId);
		OperationRateView operationRateView = new OperationRateView(equipment);
		
		EquipmentOperationRate equipmentOperationRate = equipmentOperationRateService.getOperationRate(equipmentId, beginTime, endTime);
		
		operationRateView.setOnTime(equipmentOperationRate.getOnTime());
		
		operationRateView.setOffTime(equipmentOperationRate.getOffTime());
			
		operationRateView.setOperationTime(equipmentOperationRate.getOperationTime());
		
		operationRateView.setWaitTime(equipmentOperationRate.getWaitTime());
		
		return operationRateView;
	}

	public List<OperationRateView> findBewteenTime(Timestamp beginTime, Timestamp endTime) {
		List<OperationRateView> listOperationRateView = new ArrayList<OperationRateView>();
		List<Equipment> listEquipment = equipmentService.findAll();
		for(Equipment equipment : listEquipment){
			OperationRateView operationRateView = this.findByIdBewteenTime(equipment.getEquipmentId(), beginTime, endTime);
			listOperationRateView.add(operationRateView);
		}
		return listOperationRateView;
	}

}
