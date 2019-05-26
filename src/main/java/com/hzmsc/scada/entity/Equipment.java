package com.hzmsc.scada.entity;

import java.sql.Timestamp;

//设备信息，由主信息，EquipmentBasicInfo, 
//EquipmentConfigure和EquipmentStatus组成
public class Equipment {
	
	private int equipmentId;
	private String equipmentName;
	private String equipmentType;
	private String ipAddress;
	private int port;
	private int unitIdentifier;
	private String workshop;
	private String status;
	private String isActived;
	private Timestamp createdTime;
	private Timestamp modifiedTime;

	private EquipmentBasicInfo equipmentBasicInfo;
	private EquipmentConfigure equipmentConfigure;
	private EquipmentStatus equipmentStatus;

	public Equipment(){
		super();
	}
	
	public Equipment(String equipmentName, String ipAddress, int port, int unitIdentifier, String isActived,
			Timestamp createdTime) {
		super();
		this.equipmentName = equipmentName;
		this.ipAddress = ipAddress;
		this.port = port;
		this.unitIdentifier = unitIdentifier;
		this.isActived = isActived;
		this.createdTime = createdTime;
	}

	public Equipment(int equipmentId, String equipmentName, String equipmentType, String ipAddress, int port,
			int unitIdentifier, String workshop, String status, String isActived, Timestamp createdTime,
			Timestamp modifiedTime, EquipmentBasicInfo equipmentBasicInfo, EquipmentConfigure equipmentConfigure,
			EquipmentStatus equipmentStatus) {
		super();
		this.equipmentId = equipmentId;
		this.equipmentName = equipmentName;
		this.equipmentType = equipmentType;
		this.ipAddress = ipAddress;
		this.port = port;
		this.unitIdentifier = unitIdentifier;
		this.workshop = workshop;
		this.status = status;
		this.isActived = isActived;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
		this.equipmentBasicInfo = equipmentBasicInfo;
		this.equipmentConfigure = equipmentConfigure;
		this.equipmentStatus = equipmentStatus;
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

	public EquipmentBasicInfo getEquipmentBasicInfo() {
		return equipmentBasicInfo;
	}

	public void setEquipmentBasicInfo(EquipmentBasicInfo equipmentBasicInfo) {
		this.equipmentBasicInfo = equipmentBasicInfo;
	}

	public EquipmentConfigure getEquipmentConfigure() {
		return equipmentConfigure;
	}

	public void setEquipmentConfigure(EquipmentConfigure equipmentConfigure) {
		this.equipmentConfigure = equipmentConfigure;
	}

	public EquipmentStatus getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	@Override
	public String toString() {
		return "Equipment [equipmentId=" + equipmentId + ", equipmentName=" + equipmentName + ", equipmentType="
				+ equipmentType + ", ipAddress=" + ipAddress + ", port=" + port + ", unitIdentifier=" + unitIdentifier
				+ ", workshop=" + workshop + ", status=" + status + ", isActived=" + isActived + ", createdTime="
				+ createdTime + ", modifiedTime=" + modifiedTime + ", equipmentBasicInfo=" + equipmentBasicInfo
				+ ", equipmentConfigure=" + equipmentConfigure + ", equipmentStatus=" + equipmentStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((equipmentBasicInfo == null) ? 0 : equipmentBasicInfo.hashCode());
		result = prime * result + ((equipmentConfigure == null) ? 0 : equipmentConfigure.hashCode());
		result = prime * result + equipmentId;
		result = prime * result + ((equipmentName == null) ? 0 : equipmentName.hashCode());
		result = prime * result + ((equipmentStatus == null) ? 0 : equipmentStatus.hashCode());
		result = prime * result + ((equipmentType == null) ? 0 : equipmentType.hashCode());
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + ((isActived == null) ? 0 : isActived.hashCode());
		result = prime * result + port;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + unitIdentifier;
		result = prime * result + ((workshop == null) ? 0 : workshop.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (equipmentBasicInfo == null) {
			if (other.equipmentBasicInfo != null)
				return false;
		} else if (!equipmentBasicInfo.equals(other.equipmentBasicInfo))
			return false;
		if (equipmentConfigure == null) {
			if (other.equipmentConfigure != null)
				return false;
		} else if (!equipmentConfigure.equals(other.equipmentConfigure))
			return false;
		if (equipmentId != other.equipmentId)
			return false;
		if (equipmentName == null) {
			if (other.equipmentName != null)
				return false;
		} else if (!equipmentName.equals(other.equipmentName))
			return false;
		if (equipmentStatus == null) {
			if (other.equipmentStatus != null)
				return false;
		} else if (!equipmentStatus.equals(other.equipmentStatus))
			return false;
		if (equipmentType == null) {
			if (other.equipmentType != null)
				return false;
		} else if (!equipmentType.equals(other.equipmentType))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (isActived == null) {
			if (other.isActived != null)
				return false;
		} else if (!isActived.equals(other.isActived))
			return false;
		if (port != other.port)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (unitIdentifier != other.unitIdentifier)
			return false;
		if (workshop == null) {
			if (other.workshop != null)
				return false;
		} else if (!workshop.equals(other.workshop))
			return false;
		return true;
	}
	
	
	
}
