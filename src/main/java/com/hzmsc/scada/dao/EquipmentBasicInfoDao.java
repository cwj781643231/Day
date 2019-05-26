package com.hzmsc.scada.dao;

import java.util.List;

import com.hzmsc.scada.entity.EquipmentBasicInfo;

public interface EquipmentBasicInfoDao {
	
	public EquipmentBasicInfo createEquipmentBasicInfo(EquipmentBasicInfo equipmentBasicInfo);
	
	public void deleteEquipmentBasicInfo(EquipmentBasicInfo equipmentBasicInfo);
	public void updateEquipmentBasicInfo(EquipmentBasicInfo equipmentBasicInfo);
	
	public EquipmentBasicInfo findById(int equipmentBasicInfoId);
	public int countOfEquipmentBasicInfoId(int equipmentBasicInfoId);
	
	public List<EquipmentBasicInfo> findAll();

}
