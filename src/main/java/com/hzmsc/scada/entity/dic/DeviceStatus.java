package com.hzmsc.scada.entity.dic;

public class DeviceStatus {
	
	private int statusId;
	private String statusName;
	private String statusCN;
	private String statusEN;
	public DeviceStatus() {
		super();
	}
	public DeviceStatus(int statusId, String statusName, String statusCN, String statusEN) {
		super();
		this.statusId = statusId;
		this.statusName = statusName;
		this.statusCN = statusCN;
		this.statusEN = statusEN;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getStatusCN() {
		return statusCN;
	}
	public void setStatusCN(String statusCN) {
		this.statusCN = statusCN;
	}
	public String getStatusEN() {
		return statusEN;
	}
	public void setStatusEN(String statusEN) {
		this.statusEN = statusEN;
	}
	@Override
	public String toString() {
		return "DeviceStatus [statusId=" + statusId + ", statusName=" + statusName + ", statusCN=" + statusCN + ", statusEN="
				+ statusEN + "]";
	}

}
