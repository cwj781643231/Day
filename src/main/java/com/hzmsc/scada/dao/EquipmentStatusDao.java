package com.hzmsc.scada.dao;

import java.sql.Timestamp;
import java.util.List;

import com.hzmsc.scada.entity.EquipmentStatus;

public interface EquipmentStatusDao {
	
	public EquipmentStatus createEquipmentStatus(EquipmentStatus equipmentStatus);
	
	public void deleteEquipmentStatus(EquipmentStatus equipmentStatus);
	public void updateEquipmentStatus(EquipmentStatus equipmentStauts);
	
	public EquipmentStatus findById(int equipmentStatusId);
	public int countOfEquipmentStatusId(int equipmentStatusId);
	public int countOfEquipmentId(int equipmentId);
	public int countOfEquipmentIdBeforeTime(int equipmentId, Timestamp beginTime);
	
	public List<EquipmentStatus> findAll();
	public List<EquipmentStatus> findByEquipmentId(int equipmentId);
	public List<EquipmentStatus> findByEquipmentIdAfterTime(int equipmentId, Timestamp beginTime);

	public List<EquipmentStatus> findBetweenTime(Timestamp beginTime, Timestamp endTime);
	public List<EquipmentStatus> findBetweenTimeByEvent(Timestamp beginTime, Timestamp endTime, int malfunction);
	public List<EquipmentStatus> findBetweenTimeEvents(Timestamp beginTime, Timestamp endTime);
	
	public EquipmentStatus findByEquipmentIdLatestBeforTime(int equipmentId, Timestamp beginTime);
	
}
