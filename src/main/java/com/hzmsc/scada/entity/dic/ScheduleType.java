package com.hzmsc.scada.entity.dic;

import java.sql.Time;

public class ScheduleType {
	
	
	private int scheduleTypeId;
	private String scheduleTypeCode;
	private String scheduleTypeName;
	private Time beginTime;
	private Time endTime;
	private String description;
	private String state;
	
	public ScheduleType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getScheduleTypeId() {
		return scheduleTypeId;
	}

	public void setScheduleTypeId(int scheduleTypeId) {
		this.scheduleTypeId = scheduleTypeId;
	}

	public String getScheduleTypeCode() {
		return scheduleTypeCode;
	}

	public void setScheduleTypeCode(String scheduleTypeCode) {
		this.scheduleTypeCode = scheduleTypeCode;
	}

	public String getScheduleTypeName() {
		return scheduleTypeName;
	}

	public void setScheduleTypeName(String scheduleTypeName) {
		this.scheduleTypeName = scheduleTypeName;
	}

	public Time getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Time beginTime) {
		this.beginTime = beginTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ScheduleType [scheduleTypeId=" + scheduleTypeId + ", scheduleTypeCode=" + scheduleTypeCode
				+ ", scheduleTypeName=" + scheduleTypeName + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", description=" + description + ",state=" + state + "]";
	}
	
	
	
}
