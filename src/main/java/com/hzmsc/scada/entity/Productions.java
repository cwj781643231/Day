package com.hzmsc.scada.entity;

import java.sql.Timestamp;

public class Productions {

	private int productionId;
	private String productionName;
	private int equipmentId;
	private int scheduleTypeId;
	private Timestamp createdTime;
	private double earlyWeight;
	private double midWeight;
	private double lateWeight;
	private double lastWeight;
	private Timestamp elapsedTime;
	
	//一台设备对应一个总产量
    private Object listequipsum;
    //一台设备对应多个总产量
    private Object listsumbyid;
    //所有  设备/员工   的总产量
    private Object allduction;
    
    //设备名称
	private String equipmentName;
	
	//设备详情时间
	private String detailsTime;

	//一天产量的最初值
	private Object theItial;
	//一天产量的最终值
	private Object theEnd;
	
	
	public Productions() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Productions(double earlyWeight,double midWeight,double lateWeight,double lastWeight) {
		super();
		this.earlyWeight = earlyWeight;
		/*this.midWeight = midWeight;
		this.lateWeight = lateWeight;
		this.lastWeight = lastWeight;*/
		this.createdTime = new Timestamp(System.currentTimeMillis());;
	}

	@Override
	public String toString() {
		return "Production [produtionId=" + productionId + ", productionName=" + productionName + ", equipmentId="
				+ equipmentId + ", scheduleTypeId=" + scheduleTypeId + ", createdTime=" + createdTime + ",earlyWeight="
				+ earlyWeight + ", midWeight=" + midWeight + ",lateWeight=" +lateWeight + ",lastWeight=" +lastWeight + "]";
	}
	
	
	
	
	
	public int getProductionId() {
		return productionId;
	}
	public void setProductionId(int productionId) {
		this.productionId = productionId;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public double getEarlyWeight() {
		return earlyWeight;
	}
	public void setEarlyWeight(double earlyWeight) {
		this.earlyWeight = earlyWeight;
	}
	public double getMidWeight() {
		return midWeight;
	}
	public void setMidWeight(double midWeight) {
		this.midWeight = midWeight;
	}
	public double getLateWeight() {
		return lateWeight;
	}
	public void setLateWeight(double lateWeight) {
		this.lateWeight = lateWeight;
	}
	public Object getListequipsum() {
		return listequipsum;
	}
	public void setListequipsum(Object listequipsum) {
		this.listequipsum = listequipsum;
	}
	public Object getListsumbyid() {
		return listsumbyid;
	}
	public void setListsumbyid(Object listsumbyid) {
		this.listsumbyid = listsumbyid;
	}
	public Timestamp getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(Timestamp elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public Object getTheItial() {
		return theItial;
	}
	public void setTheItial(Object theItial) {
		this.theItial = theItial;
	}
	public Object getTheEnd() {
		return theEnd;
	}
	public void setTheEnd(Object theEnd) {
		this.theEnd = theEnd;
	}
	public String getDetailsTime() {
		return detailsTime;
	}
	public void setDetailsTime(String detailsTime) {
		this.detailsTime = detailsTime;
	}
	public Object getAllduction() {
		return allduction;
	}
	public void setAllduction(Object allduction) {
		this.allduction = allduction;
	}
	public double getLastWeight() {
		return lastWeight;
	}
	public void setLastWeight(double lastWeight) {
		this.lastWeight = lastWeight;
	}
	public int getScheduleTypeId() {
		return scheduleTypeId;
	}
	public void setScheduleTypeId(int scheduleTypeId) {
		this.scheduleTypeId = scheduleTypeId;
	}
	
	

	
}
