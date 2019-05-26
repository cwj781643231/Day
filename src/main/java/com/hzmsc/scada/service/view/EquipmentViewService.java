package com.hzmsc.scada.service.view;

import java.sql.Timestamp;
import java.util.List;

import com.hzmsc.scada.entity.view.EquipmentView;

public interface EquipmentViewService {
	
	public EquipmentView findById(int equipmentId);
	
	public List<EquipmentView> findAll();

	public List<EquipmentView> findBetweenTimeEvents(Timestamp beginTime, Timestamp endTime);

	
	/*
	 * 设备增删改
	 * */
	public int createEquipmentView(EquipmentView equipmentview);
	
	public int updateEquipmentView(EquipmentView equipmentview);
	
	public void deleteEquipmentView(EquipmentView equipmentview);
}
