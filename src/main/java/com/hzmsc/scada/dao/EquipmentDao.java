package com.hzmsc.scada.dao;

import java.util.List;

import com.hzmsc.scada.entity.Employee;
import com.hzmsc.scada.entity.Equipment;

public interface EquipmentDao {
	
	public Equipment createEquipment(Equipment equipment);
	public void updateEquipment(Equipment equipment);
	public void deleteEquipment(Equipment equipment);
	
	public Equipment findById(int equipmentId);
	public Equipment findByIpSlave(String ipAddress, int unitIdentifier);
	
	public int countOfEquipmentId(int equipmentId);
	public int countOfEquipmentIpSlave(String ipAddress, int unitIdentifier);
	
	public List<Equipment> findAll();
	
	public List<Equipment> findActiveAll(int isActive);

}
