package com.hzmsc.scada.entity.view;

import java.sql.Timestamp;

import com.hzmsc.scada.entity.Equipment;

public class EquipmentView {
	
	private int equipmentId;
	private String equipmentName;
	private int deviceType;
	private String equipmentType;
	private String ipAddress;
	private int port;
	private int unitIdentifier;
	private String workshop;
	private int taskCompletion;
	private int equipmentStatus;	
	private String status;
	private int malfunction;	
	private String event;
	private String isActived;
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	
	public EquipmentView() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EquipmentView(Equipment equipment) {
		super();
		this.equipmentId = equipment.getEquipmentId();
		this.equipmentName = equipment.getEquipmentName();
		this.deviceType = equipment.getEquipmentBasicInfo().getDeviceType();
		this.equipmentType = equipment.getEquipmentType();
		this.ipAddress = equipment.getIpAddress();
		this.port = equipment.getPort();
		this.unitIdentifier = equipment.getUnitIdentifier();
		this.workshop = equipment.getWorkshop();
		this.taskCompletion = equipment.getEquipmentStatus().getTaskCompletion();
		this.equipmentStatus = equipment.getEquipmentStatus().getEquipmentStatus();
		this.status = equipment.getStatus();
		this.malfunction = equipment.getEquipmentStatus().getMalfunction();	
		this.event = "-";//初始化一个默认事件信息
		
		this.isActived = equipment.getIsActived();
		this.createdTime = equipment.getCreatedTime();
		this.modifiedTime = equipment.getModifiedTime();
	}
	
	
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public int getEquipmentStatus() {
		return equipmentStatus;
	}
	public void setEquipmentStatus(int equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getUnitIdentifier() {
		return unitIdentifier;
	}
	public void setUnitIdentifier(int unitIdentifier) {
		this.unitIdentifier = unitIdentifier;
	}
	public String getWorkshop() {
		return workshop;
	}
	public void setWorkshop(String workshop) {
		this.workshop = workshop;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsActived() {
		return isActived;
	}
	public void setIsActived(String isActived) {
		this.isActived = isActived;
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
	
	public int getTaskCompletion() {
		return taskCompletion;
	}
	public void setTaskCompletion(int taskCompletion) {
		this.taskCompletion = taskCompletion;
	}
	
	public int getMalfunction() {
		return malfunction;
	}
	public void setMalfunction(int malfunction) {
		this.malfunction = malfunction;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	@Override
	public String toString() {
		return "EquipmentView [equipmentId=" + equipmentId + ", equipmentName=" + equipmentName + ", deviceType="
				+ deviceType + ", equipmentType=" + equipmentType + ", ipAddress=" + ipAddress + ", port=" + port
				+ ", unitIdentifier=" + unitIdentifier + ", workshop=" + workshop + ", taskCompletion=" + taskCompletion
				+ ", equipmentStatus=" + equipmentStatus + ", status=" + status + ", malfunction=" + malfunction
				+ ", event=" + event + ", isActived=" + isActived + ", createdTime=" + createdTime + ", modifiedTime="
				+ modifiedTime + "]";
	}
	
	

}
