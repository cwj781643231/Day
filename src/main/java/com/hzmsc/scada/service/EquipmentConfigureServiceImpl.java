package com.hzmsc.scada.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.EquipmentConfigureDao;
import com.hzmsc.scada.entity.EquipmentConfigure;

@Service
public class EquipmentConfigureServiceImpl implements EquipmentConfigureService {

	private EquipmentConfigureDao equipmentConfigureDao;
	
	public EquipmentConfigureDao getEquipmentConfigureDao() {
		return equipmentConfigureDao;
	}

	@Autowired
	public void setEquipmentConfigureDao(EquipmentConfigureDao equipmentConfigureDao) {
		this.equipmentConfigureDao = equipmentConfigureDao;
	}

	public EquipmentConfigure createEquipmentConfigure(EquipmentConfigure equipmentConfigure) {
		if(equipmentConfigure.getModifiedTime() == null){
			equipmentConfigure.setModifiedTime(new Timestamp(System.currentTimeMillis()));
		}
		return this.getEquipmentConfigureDao().createEquipmentConfigure(equipmentConfigure);
	}

	public void deleteEquipmentConfigure(EquipmentConfigure equipmentConfigure) {
		this.getEquipmentConfigureDao().deleteEquipmentConfigure(equipmentConfigure);
	}

	public void updateEquipmentConfigure(EquipmentConfigure equipmentStauts) {
		this.getEquipmentConfigureDao().updateEquipmentConfigure(equipmentStauts);

	}

	public EquipmentConfigure findById(int equipmentConfigureId) {
		return this.getEquipmentConfigureDao().findById(equipmentConfigureId);
	}

	public int countOfEquipmentConfigureId(int equipmentConfigureId) {
		return this.getEquipmentConfigureDao().countOfEquipmentConfigureId(equipmentConfigureId);
	}

	public List<EquipmentConfigure> findAll() {
		return this.getEquipmentConfigureDao().findAll();
	}

}
