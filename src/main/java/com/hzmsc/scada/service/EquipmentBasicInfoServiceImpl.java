package com.hzmsc.scada.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.EquipmentBasicInfoDao;
import com.hzmsc.scada.entity.EquipmentBasicInfo;

@Service
public class EquipmentBasicInfoServiceImpl implements EquipmentBasicInfoService {

	private EquipmentBasicInfoDao equipmentBasicInfoDao;
	
	public EquipmentBasicInfoDao getEquipmentBasicInfoDao() {
		return equipmentBasicInfoDao;
	}

	@Autowired
	public void setEquipmentBasicInfoDao(EquipmentBasicInfoDao equipmentBasicInfoDao) {
		this.equipmentBasicInfoDao = equipmentBasicInfoDao;
	}

	public EquipmentBasicInfo createEquipmentBasicInfo(EquipmentBasicInfo equipmentBasicInfo) {
		if(equipmentBasicInfo.getModifiedTime() == null){
			equipmentBasicInfo.setModifiedTime(new Timestamp(System.currentTimeMillis()));
		}
		return this.getEquipmentBasicInfoDao().createEquipmentBasicInfo(equipmentBasicInfo);
	}

	public void deleteEquipmentBasicInfo(EquipmentBasicInfo equipmentBasicInfo) {
		this.getEquipmentBasicInfoDao().deleteEquipmentBasicInfo(equipmentBasicInfo);

	}

	public void updateEquipmentBasicInfo(EquipmentBasicInfo equipmentStauts) {
		this.getEquipmentBasicInfoDao().updateEquipmentBasicInfo(equipmentStauts);

	}

	public EquipmentBasicInfo findById(int equipmentBasicInfoId) {
		return this.getEquipmentBasicInfoDao().findById(equipmentBasicInfoId);
	}

	public int countOfEquipmentBasicInfoId(int equipmentBasicInfoId) {
		return this.getEquipmentBasicInfoDao().countOfEquipmentBasicInfoId(equipmentBasicInfoId);
	}

	public List<EquipmentBasicInfo> findAll() {
		return this.getEquipmentBasicInfoDao().findAll();
	}

}
