package com.hzmsc.scada.service;

import java.sql.Timestamp;
import java.util.List;

import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.entity.Production;

public interface ProductionService {

	public Production findById(int productionId);

	public List<Production> findAll();

	public Production findByEquipmentIdLatestBeforeTime(int equipmentId);

	public Production findByEquipmentIdLatestBeforeTime(int equipmentId, Timestamp endTime);

	public Production findByEquipmentIdFirstAfterTime(int equipmentId);

	public Production findByEquipmentIdFirstAfterTime(int equipmentId, Timestamp beginTime);

	public List<Production> findByEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime, Timestamp endTime);

	public int countOfId(int productionId);

	public int countOfEquipmentIdBeforeTime(int equipmentId, Timestamp endTime);

	public int countOfEquipmentIdAfterTime(int equipmentId, Timestamp beginTime);

	public int countOfEquipmentId(int equipmentId);

	public int countOfEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime, Timestamp endTime);

	public Production createProduction(final Production production);

	public void updateProduction(Production production);

	public void deleteProduction(Production production);

	public Production calculateProduction(Production lastProduction, Equipment lastEquipment,
			Equipment currentEquipment);

	public double getWeight(int equipmentId);

	public double getWeight(int equipmentId, Timestamp beginTime, Timestamp endTime);

	public List<Production> sumequipbyid(String beginDay, String endDay,int equipmentId);

	/*
	 * 数据报表     设备方面
	 * */
	/*
	 * 所有设备的最终产量之和
	 * */
	public int allproduction(String beginDay, String endDay);
	
	/*
	 * 每台设备的名称    以及该时间内的最初产量和最终产量  来计算该时间段内的产量值
	 * */
	public List<Object> sumproduction(String beginDay, String endDay);
	
	/*
	 * 在production表  查询出   每台的设备信息
	 * */
	public List<Production> listproduction();
	
	/*
	 * 根据设备ID查询出该设备   在某个时间段的每天产量
	 * */
	public List<Object> listsumequipbyid(String beginDay, String endDay, int equipmentId);

	/*
	 * 根据设备ID查询  和时间   查询出该设备在规定的时间段有哪几天工作了
	 * */
	public List<Object> findbyDateEquipmentId(String beginDay, String endDay, int equipmentId);
	
	/*
	 * 根据设备ID查询  和时间   查询出该设备在规定的哪一天的生产量
	 * */
	public List findbyOneDayEquipmentId(String beginDay, int equipmentId);
	
	
	
	/*
	 * 数据报表     员工方面
	 * */
	
}
