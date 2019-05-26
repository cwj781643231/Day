package com.hzmsc.scada.entity;

import java.sql.Timestamp;

public class EquipmentOperationRate {
	
	private int equipmentOperationRateId;
	private int equipmentId;	
	private String equipmentName;
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
	
	public EquipmentOperationRate() {
		super();
		
	}
	public EquipmentOperationRate(int operationTime, int offTime, int onTime, int waitTime) {
		
		this.modifiedTime = new Timestamp(System.currentTimeMillis());
		this.operationTime = operationTime;
		this.offTime = offTime;
		this.onTime = onTime;
		this.waitTime = waitTime;		
	}
	
public EquipmentOperationRate(EquipmentOperationRate equipmentOperationRate) {
		
		this.modifiedTime = new Timestamp(System.currentTimeMillis());
		this.equipmentId = equipmentOperationRate.getEquipmentId();
		this.operationTime = equipmentOperationRate.getOperationTime();
		this.offTime = equipmentOperationRate.getOffTime();
		this.onTime = equipmentOperationRate.getOnTime();
		this.waitTime = equipmentOperationRate.getWaitTime();		
	}
	
	
	public int getEquipmentOperationRateId() {
		return equipmentOperationRateId;
	}


	public void setEquipmentOperationRateId(int equipmentOperationRateId) {
		this.equipmentOperationRateId = equipmentOperationRateId;
	}


	public int getEquipmentId() {
		return equipmentId;
	}


	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}


	

	public Timestamp getModifiedTime() {
		return modifiedTime;
	}


	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
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


	@Override
	public String toString() {
		return "EquipmentOperationRate [equipmentOperationRateId=" + equipmentOperationRateId + ", equipmentId="
				+ equipmentId + ", equipmentName=" + equipmentName + ", modifiedTime=" + modifiedTime + ", operationTime="
				+ operationTime + ", operationTimeOfYear=" + operationTimeOfYear + ", operationTimeOfMonth="
				+ operationTimeOfMonth + ", operationTimeOfDay=" + operationTimeOfDay + ", offTime=" + offTime
				+ ", offTimeOfYear=" + offTimeOfYear + ", offTimeOfMonth=" + offTimeOfMonth + ", offTimeOfDay="
				+ offTimeOfDay + ", onTime=" + onTime + ", onTimeOfYear=" + onTimeOfYear + ", onTimeOfMonth="
				+ onTimeOfMonth + ", onTimeOfDay=" + onTimeOfDay + ", waitTime=" + waitTime + ", waitTimeOfYear="
				+ waitTimeOfYear + ", waitTimeOfMonth=" + waitTimeOfMonth + ", waitTimeOfDay=" + waitTimeOfDay + "]";
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	
	
}
