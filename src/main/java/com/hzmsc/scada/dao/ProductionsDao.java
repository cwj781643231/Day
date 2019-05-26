package com.hzmsc.scada.dao;

import java.sql.Timestamp;
import java.util.List;

import com.hzmsc.scada.entity.DailySchedule;
import com.hzmsc.scada.entity.Production;
import com.hzmsc.scada.entity.Productions;

public interface ProductionsDao {

	
	
	public Productions createProductions(final Productions production);
	
	public void updateProductions(Productions production);
	
	public int countOfEquipmentId(String beginDay, String endDay ,int equipmentId, int scheduleTypeId);
	
	public Productions findByEquipmentIdLatestBeforeTime(String beginDay, String endDay ,int equipmentId, int scheduleTypeId);

	public List<Object> productionsfindall(String beginDay, String endDay);
	
	/*
	 * 在production表  查询出   每台的设备信息
	 * */
	public List listproductions();
	
 
}
