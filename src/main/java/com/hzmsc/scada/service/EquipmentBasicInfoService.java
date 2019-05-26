package com.hzmsc.scada.service;

import java.util.List;

import com.hzmsc.scada.entity.EquipmentBasicInfo;

public interface EquipmentBasicInfoService {

	public EquipmentBasicInfo createEquipmentBasicInfo(EquipmentBasicInfo equipmentBasicInfo);
	
	public void deleteEquipmentBasicInfo(EquipmentBasicInfo equipmentBasicInfo);
	public void updateEquipmentBasicInfo(EquipmentBasicInfo equipmentStauts);
	
	public EquipmentBasicInfo findById(int equipmentBasicInfoId);
	public int countOfEquipmentBasicInfoId(int equipmentBasicInfoId);
	
	public List<EquipmentBasicInfo> findAll();
	
	
	
}
