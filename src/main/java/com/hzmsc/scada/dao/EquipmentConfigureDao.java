package com.hzmsc.scada.dao;

import java.util.List;

import com.hzmsc.scada.entity.EquipmentConfigure;

public interface EquipmentConfigureDao {
	
	public EquipmentConfigure createEquipmentConfigure(EquipmentConfigure equipmentConfigure);
	
	public void deleteEquipmentConfigure(EquipmentConfigure equipmentConfigure);
	public void updateEquipmentConfigure(EquipmentConfigure equipmentStauts);
	
	public EquipmentConfigure findById(int equipmentConfigureId);
	public int countOfEquipmentConfigureId(int equipmentConfigureId);
	
	public List<EquipmentConfigure> findAll();

}
