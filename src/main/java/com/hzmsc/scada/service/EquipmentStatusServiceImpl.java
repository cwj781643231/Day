package com.hzmsc.scada.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.EquipmentStatusDao;
import com.hzmsc.scada.entity.EquipmentStatus;

@Service
public class EquipmentStatusServiceImpl implements EquipmentStatusService {

	private EquipmentStatusDao equipmentStatusDao;
	
	public EquipmentStatusDao getEquipmentStatusDao() {
		return equipmentStatusDao;
	}

	@Autowired
	public void setEquipmentStatusDao(EquipmentStatusDao equipmentStatusDao) {
		this.equipmentStatusDao = equipmentStatusDao;
	}

	public EquipmentStatus createEquipmentStatus(EquipmentStatus equipmentStatus) {
		if(equipmentStatus.getModifiedTime() == null){
			equipmentStatus.setModifiedTime(new Timestamp(System.currentTimeMillis()));
		}
		return this.getEquipmentStatusDao().createEquipmentStatus(equipmentStatus);
	}

	public void deleteEquipmentStatus(EquipmentStatus equipmentStatus) {
		this.getEquipmentStatusDao().deleteEquipmentStatus(equipmentStatus);

	}

	public void updateEquipmentStatus(EquipmentStatus equipmentStauts) {
		this.getEquipmentStatusDao().updateEquipmentStatus(equipmentStauts);

	}

	public EquipmentStatus findById(int equipmentStatusId) {
		return this.getEquipmentStatusDao().findById(equipmentStatusId);
	}

	public int countOfEquipmentStatusId(int equipmentStatusId) {
		return this.getEquipmentStatusDao().countOfEquipmentStatusId(equipmentStatusId);
	}

	public List<EquipmentStatus> findAll() {
		return this.getEquipmentStatusDao().findAll();
	}

	public List<EquipmentStatus> findByEquipmentId(int equipmentId) {
		return this.getEquipmentStatusDao().findByEquipmentId(equipmentId);
	}

	public List<EquipmentStatus> findByEquipmentIdAfterTime(int equipmentId, Timestamp beginTime) {
		return this.getEquipmentStatusDao().findByEquipmentIdAfterTime(equipmentId, beginTime);
	}

	public EquipmentStatus findByEquipmentIdLatestBeforTime(int equipmentId, Timestamp beginTime) {
		return this.getEquipmentStatusDao().findByEquipmentIdLatestBeforTime(equipmentId, beginTime);
	}
	
	public List<EquipmentStatus> findBetweenTime(Timestamp beginTime, Timestamp endTime){
		return this.getEquipmentStatusDao().findBetweenTime(beginTime, endTime);
	}
	
	public List<EquipmentStatus> findBetweenTimeEvents(Timestamp beginTime, Timestamp endTime){
		return this.getEquipmentStatusDao().findBetweenTimeEvents(beginTime, endTime);
	}
	
	public List<EquipmentStatus> findBetweenTimeByEvent(Timestamp beginTime, Timestamp endTime, int malfunction) {
		return this.getEquipmentStatusDao().findBetweenTimeByEvent(beginTime, endTime, malfunction);
	}
	
	public int countOfEquipmentId(int equipmentId) {
		return this.getEquipmentStatusDao().countOfEquipmentId(equipmentId);
	}

	public int countOfEquipmentIdBeforeTime(int equipmentId, Timestamp beginTime) {
		return this.getEquipmentStatusDao().countOfEquipmentIdBeforeTime(equipmentId, beginTime);
	}

	

}
