package com.hzmsc.scada.service;

import java.sql.Timestamp;
import java.util.List;

import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.entity.Production;
import com.hzmsc.scada.entity.Productions;

public interface ProductionsService {

    public Productions createProductions(final Productions production);
	
	public void updateProductions(Productions production);
	
	public int countOfEquipmentId(String beginDay, String endDay ,int equipmentId, int scheduleTypeId);
	
	public Productions findByEquipmentIdLatestBeforeTime(String beginDay, String endDay ,int equipmentId, int scheduleTypeId);
	
	public Productions calculateProduction(Productions lastProduction, Equipment lastEquipment,
			Equipment currentEquipment,Timestamp stamp);
	
	public List<Object> productionsfindall(String beginDay, String endDay);
	
	public List listproductions();
}
