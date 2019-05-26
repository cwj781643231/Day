package com.hzmsc.scada.service;

import java.sql.Timestamp;
import java.util.List;

import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.entity.EquipmentOperationRate;

public interface EquipmentOperationRateService {

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

	// 根据最近的状态，计算新的开机时间，总时间=开机+停机，开机=操作+等待
	public EquipmentOperationRate calculateOperationRate(EquipmentOperationRate lastEquipmentOperationRate, Equipment lastEquipment, Equipment currentEquipment);

	// 获取操作时间
	public EquipmentOperationRate getOperationRate(int equipmentId);
	public EquipmentOperationRate getOperationRate(int equipmentId, Timestamp beginTime, Timestamp endTime);

	// 获取操作时间
	public long getOperationTime(int equipmentId);
	public long getOperationTime(int equipmentId, Timestamp beginTime, Timestamp endTime);

	// 获取开机时间 = 操作时间+等待时间
	public long getOnTime(int equipmentId);
	public long getOnTime(int equipmentId, Timestamp beginTime, Timestamp endTime);

	// 获取停机时间
	public long getOffTime(int equipmentId);
	public long getOffTime(int equipmentId, Timestamp beginTime, Timestamp endTime);

	// 获取等待时间
	public long getWaitTime(int equipmentId);
	public long getWaitTime(int equipmentId, Timestamp beginTime, Timestamp endTime);

	
}
