package com.hzmsc.scada.entity;

import java.sql.Timestamp;

public class Production {

	private int productionId;
	private String productionName;
	private int equipmentId;
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private Double weight;
	private float designedWeight;
	
	
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
	
	public Production() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Production(double weight) {
		super();
		this.weight = weight;
		this.createdTime = new Timestamp(System.currentTimeMillis());;
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
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public float getDesignedWeight() {
		return designedWeight;
	}
	public void setDesignedWeight(float designedWeight) {
		this.designedWeight = designedWeight;
	}
	@Override
	public String toString() {
		return "Production [produtionId=" + productionId + ", productionName=" + productionName + ", equipmentId="
				+ equipmentId + ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", weight="
				+ weight + ", designedWeight=" + designedWeight + "]";
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
	
	
	
}
