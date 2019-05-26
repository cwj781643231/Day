package com.hzmsc.scada.entity.view;

import java.sql.Timestamp;

import com.hzmsc.scada.entity.Equipment;

public class OperationRateView {
	
	private int equipmentId;
	private String equipmentName;
	private int deviceType;
	private String equipmentType;
	private String ipAddress;
	private int port;
	private int unitIdentifier;
	private String workshop;
	//private int equipmentStatus;	
	//private String status;
	private String isActived;
	private Timestamp modifiedTime;
	
	private long operationTime;
	private long operationTimeOfYear;
	private long operationTimeOfMonth;
	private long operationTimeOfDay;
	
	private long offTime;
	private long offTimeOfYear;
	private long offTimeOfMonth;
	private long offTimeOfDay;
	
	private long onTime;
	private long onTimeOfYear;
	private long onTimeOfMonth;
	private long onTimeOfDay;
	
	private long waitTime;
	private long waitTimeOfYear;
	private long waitTimeOfMonth;
	private long waitTimeOfDay;
	
	public OperationRateView() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OperationRateView(Equipment equipment) {
		super();
		this.equipmentId = equipment.getEquipmentId();
		this.equipmentName = equipment.getEquipmentName();
		this.deviceType = equipment.getEquipmentBasicInfo().getDeviceType();
		this.equipmentType = equipment.getEquipmentType();
		this.ipAddress = equipment.getIpAddress();
		this.port = equipment.getPort();
		this.unitIdentifier = equipment.getUnitIdentifier();
		this.workshop = equipment.getWorkshop();
		this.isActived = equipment.getIsActived();
		this.modifiedTime = equipment.getModifiedTime();
		
		this.operationTime = 0;
		this.operationTimeOfYear = 0;
		this.operationTimeOfMonth = 0;
		this.operationTimeOfDay = 0;
		
		this.offTime = 0;
		this.offTimeOfYear = 0;
		this.offTimeOfMonth = 0;
		this.offTimeOfDay = 0;
		
		this.onTime = 0;
		this.onTimeOfYear = 0;
		this.onTimeOfMonth = 0;
		this.onTimeOfDay = 0;
		
		this.waitTime = 0;
		this.waitTimeOfYear = 0;
		this.waitTimeOfMonth = 0;
		this.waitTimeOfDay = 0;
	}
	
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
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
	public String getIsActived() {
		return isActived;
	}
	public void setIsActived(String isActived) {
		this.isActived = isActived;
	}
	
	public long getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(long operationTime) {
		this.operationTime = operationTime;
	}
	public long getOperationTimeOfYear() {
		return operationTimeOfYear;
	}
	public void setOperationTimeOfYear(long operationTimeOfYear) {
		this.operationTimeOfYear = operationTimeOfYear;
	}
	public long getOperationTimeOfMonth() {
		return operationTimeOfMonth;
	}
	public void setOperationTimeOfMonth(long operationTimeOfMonth) {
		this.operationTimeOfMonth = operationTimeOfMonth;
	}
	public long getOperationTimeOfDay() {
		return operationTimeOfDay;
	}
	public void setOperationTimeOfDay(long operationTimeOfDay) {
		this.operationTimeOfDay = operationTimeOfDay;
	}
	public long getOffTime() {
		return offTime;
	}
	public void setOffTime(long offTime) {
		this.offTime = offTime;
	}
	public long getOffTimeOfYear() {
		return offTimeOfYear;
	}
	public void setOffTimeOfYear(long offTimeOfYear) {
		this.offTimeOfYear = offTimeOfYear;
	}
	public long getOffTimeOfMonth() {
		return offTimeOfMonth;
	}
	public void setOffTimeOfMonth(long offTimeOfMonth) {
		this.offTimeOfMonth = offTimeOfMonth;
	}
	public long getOffTimeOfDay() {
		return offTimeOfDay;
	}
	public void setOffTimeOfDay(long offTimeOfDay) {
		this.offTimeOfDay = offTimeOfDay;
	}
	public long getOnTime() {
		return onTime;
	}
	public void setOnTime(long onTime) {
		this.onTime = onTime;
	}
	public long getOnTimeOfYear() {
		return onTimeOfYear;
	}
	public void setOnTimeOfYear(long onTimeOfYear) {
		this.onTimeOfYear = onTimeOfYear;
	}
	public long getOnTimeOfMonth() {
		return onTimeOfMonth;
	}
	public void setOnTimeOfMonth(long onTimeOfMonth) {
		this.onTimeOfMonth = onTimeOfMonth;
	}
	public long getOnTimeOfDay() {
		return onTimeOfDay;
	}
	public void setOnTimeOfDay(long onTimeOfDay) {
		this.onTimeOfDay = onTimeOfDay;
	}
	public long getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(long waitTime) {
		this.waitTime = waitTime;
	}
	public long getWaitTimeOfYear() {
		return waitTimeOfYear;
	}
	public void setWaitTimeOfYear(long waitTimeOfYear) {
		this.waitTimeOfYear = waitTimeOfYear;
	}
	public long getWaitTimeOfMonth() {
		return waitTimeOfMonth;
	}
	public void setWaitTimeOfMonth(long waitTimeOfMonth) {
		this.waitTimeOfMonth = waitTimeOfMonth;
	}
	public long getWaitTimeOfDay() {
		return waitTimeOfDay;
	}
	public void setWaitTimeOfDay(long waitTimeOfDay) {
		this.waitTimeOfDay = waitTimeOfDay;
	}
	
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	@Override
	public String toString() {
		return "OperationRateView [equipmentId=" + equipmentId + ", equipmentName=" + equipmentName + ", deviceType="
				+ deviceType + ", equipmentType=" + equipmentType + ", ipAddress=" + ipAddress + ", port=" + port
				+ ", unitIdentifier=" + unitIdentifier + ", workshop=" + workshop + ", isActived=" + isActived
				+ ", modifiedTime=" + modifiedTime + ", operationTime=" + operationTime
				+ ", operationTimeOfYear=" + operationTimeOfYear + ", operationTimeOfMonth=" + operationTimeOfMonth
				+ ", operationTimeOfDay=" + operationTimeOfDay + ", offTime=" + offTime + ", offTimeOfYear="
				+ offTimeOfYear + ", offTimeOfMonth=" + offTimeOfMonth + ", offTimeOfDay=" + offTimeOfDay + ", onTime="
				+ onTime + ", onTimeOfYear=" + onTimeOfYear + ", onTimeOfMonth=" + onTimeOfMonth + ", onTimeOfDay="
				+ onTimeOfDay + ", waitTime=" + waitTime + ", waitTimeOfYear=" + waitTimeOfYear + ", waitTimeOfMonth="
				+ waitTimeOfMonth + ", waitTimeOfDay=" + waitTimeOfDay + "]";
	}
	
	
	

}
