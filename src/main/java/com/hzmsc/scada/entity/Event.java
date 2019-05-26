package com.hzmsc.scada.entity;

import java.sql.Timestamp;

public class Event {
	
	/*
	 * `eventId` INT(11) NOT NULL AUTO_INCREMENT;
	 * `eventName` VARCHAR(50) NULL DEFAULT NULL;
	 * `eventTypeId` INT(11) NULL DEFAULT NULL;
	 * `eventTypeCode` VARCHAR(50) NULL DEFAULT NULL;
	 * `eventTypeName` VARCHAR(50) NULL DEFAULT NULL;
	 * `eventLevelId` INT(11) NULL DEFAULT NULL;
	 * `eventLevelCode` VARCHAR(50) NULL DEFAULT NULL;	
	 * `eventLevelName` VARCHAR(50) NULL DEFAULT NULL;	
	 * `equipmentId`` INT(11) NULL DEFAULT NULL;
	 * `equipmentName` VARCHAR(50) NULL DEFAULT NULL;
	 * `createdTime` DATETIME NULL DEFAULT NULL;
	 * EquipmentStatus;
	 * EquipmentConfigure;
	 * EquipmentBasicInfo; 
	 */
	
	private int eventId;
	private String eventName;
	private int eventTypeId;
	private String eventTypeCode;
	private String eventTypeName;
	private int eventLevelId;
	private String eventLevelCode;
	private String eventLevelName;
	private int equipmentId;
	private String equipmentName;
	private Timestamp createdTime;

	private EquipmentStatus equipmentStatus;
	private EquipmentConfigure equipmentConfigure;
	private EquipmentBasicInfo equipmentBasicInfo;
	
	private int status;
	
	private PageBean pageBean;

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Event(Equipment equipment) {
		this.equipmentId = equipment.getEquipmentId();
		this.equipmentName = equipment.getEquipmentName();
		this.createdTime = equipment.getModifiedTime();
		this.equipmentStatus = equipment.getEquipmentStatus();
		this.equipmentConfigure = equipment.getEquipmentConfigure();
		this.equipmentBasicInfo = equipment.getEquipmentBasicInfo();
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getEventTypeCode() {
		return eventTypeCode;
	}

	public void setEventTypeCode(String eventTypeCode) {
		this.eventTypeCode = eventTypeCode;
	}

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public int getEventLevelId() {
		return eventLevelId;
	}

	public void setEventLevelId(int eventLevelId) {
		this.eventLevelId = eventLevelId;
	}

	public String getEventLevelCode() {
		return eventLevelCode;
	}

	public void setEventLevelCode(String eventLevelCode) {
		this.eventLevelCode = eventLevelCode;
	}

	public String getEventLevelName() {
		return eventLevelName;
	}

	public void setEventLevelName(String eventLevelName) {
		this.eventLevelName = eventLevelName;
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

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public EquipmentStatus getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	public EquipmentConfigure getEquipmentConfigure() {
		return equipmentConfigure;
	}

	public void setEquipmentConfigure(EquipmentConfigure equipmentConfigure) {
		this.equipmentConfigure = equipmentConfigure;
	}

	public EquipmentBasicInfo getEquipmentBasicInfo() {
		return equipmentBasicInfo;
	}

	public void setEquipmentBasicInfo(EquipmentBasicInfo equipmentBasicInfo) {
		this.equipmentBasicInfo = equipmentBasicInfo;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", eventTypeId=" + eventTypeId
				+ ", eventTypeCode=" + eventTypeCode + ", eventTypeName=" + eventTypeName + ", eventLevelId="
				+ eventLevelId + ", eventLevelCode=" + eventLevelCode + ", eventLevelName=" + eventLevelName
				+ ", equipmentId=" + equipmentId + ", equipmentName=" + equipmentName + ", createdTime=" + createdTime
				+ ", equipmentStatus=" + equipmentStatus + ", equipmentConfigure=" + equipmentConfigure
				+ ", equipmentBasicInfo=" + equipmentBasicInfo + ", pageBean=" + pageBean + "]";
	}
//, queryInfo=" + queryInfo + "
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	
}
