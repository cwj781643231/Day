package com.hzmsc.scada.entity.dic;

public class EventType {
	
	private int eventTypeId;
	private String eventTypeCode;
	private String eventTypeName;
	private String eventTypeCN;
	private String eventTypeEN;
	private String description;
	public EventType() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getEventTypeCN() {
		return eventTypeCN;
	}
	public void setEventTypeCN(String eventTypeCN) {
		this.eventTypeCN = eventTypeCN;
	}
	public String getEventTypeEN() {
		return eventTypeEN;
	}
	public void setEventTypeEN(String eventTypeEN) {
		this.eventTypeEN = eventTypeEN;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "EventType [eventTypeId=" + eventTypeId + ", eventTypeCode=" + eventTypeCode + ", eventTypeName="
				+ eventTypeName + ", eventTypeCN=" + eventTypeCN + ", eventTypeEN=" + eventTypeEN + ", description="
				+ description + "]";
	}
	

}
