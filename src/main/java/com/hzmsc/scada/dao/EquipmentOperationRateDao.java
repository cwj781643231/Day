package com.hzmsc.scada.dao;

import java.sql.Timestamp;
import java.util.List;

import com.hzmsc.scada.entity.EquipmentOperationRate;

public interface EquipmentOperationRateDao {

	public EquipmentOperationRate createEquipmentOperationRate(EquipmentOperationRate equipmentOperationRate);

	public void deleteEquipmentOperationRate(EquipmentOperationRate equipmentOperationRate);

	public void updateEquipmentOperationRate(EquipmentOperationRate equipmentStauts);

	public EquipmentOperationRate findById(int equipmentOperationRateId);

	public List<EquipmentOperationRate> findAll();

	public List<EquipmentOperationRate> findByEquipmentId(int equipmentId);

	public EquipmentOperationRate findByEquipmentIdFirstAfterTime(int equipmentId);

	public EquipmentOperationRate findByEquipmentIdLatestBeforeTime(int equipmentId);

	public List<EquipmentOperationRate> findByEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime,
			Timestamp endTime);

	public EquipmentOperationRate findByEquipmentIdFirstAfterTime(int equipmentId, Timestamp beginTime);

	public EquipmentOperationRate findByEquipmentIdLatestBeforeTime(int equipmentId, Timestamp endTime);

	public int countOfEquipmentOperationRateId(int equipmentOperationRateId);

	public int countOfFindByEquipmentId(int equipmentId);

	public int countOfFindByEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime, Timestamp endTime);

}
