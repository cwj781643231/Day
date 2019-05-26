package com.hzmsc.scada.entity;

import java.sql.Timestamp;
import java.util.Calendar;

/* 设备基本信息,18个数据
 * holdingregisters 40000开始
 * 软件版本号	   800	序列码3	     0
 * 硬件版本号	     0	注册码1	     0
 * 新软件版本号	     0	注册码2	     0
 * 软件版本后缀	 21041	注册码3	     0
 * 设备类型	  2563	到期年	  2066
 * 剩余天数	-30417	到期月	     2
 * 授权错误次数	     5	到期日	    24
 * 增加操作命令	     0	到期时	     9
 * 序列码1	     0		
 * 序列码2	     0		
 */

public class EquipmentBasicInfo {

	private int equipmentBasicInfoId;
	private int equipmentId;
	private Timestamp modifiedTime;

	private int softwareVersion;
	private int hardwareVersion;
	private int newsoftwareVersion;
	private int softwareVersionSuffix;
	private int deviceType;
	private int remainingDays;
	private int authorizationErrorNumber;
	private int addOperation;
	private int serialFirst;
	private int serialSecond;
	
	private int serialThird;
	private int registrationFirst;
	private int registrationSecond;
	private int registrationThird;
	private int expiredYear;
	private int expiredMonth;
	private int expiredDay;
	private int expiredHour;

	public EquipmentBasicInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	//从设备读取数据进行初始化
	public EquipmentBasicInfo(int[] registerValues) {
		super();
		if (registerValues.length == 18) {
			int baseAddress = 0;
			// this.equipmentBasicInfoId =
			this.modifiedTime = new Timestamp(Calendar.getInstance().getTime().getTime());

			this.softwareVersion = registerValues[baseAddress];
			this.hardwareVersion = registerValues[baseAddress + 1];
			this.newsoftwareVersion = registerValues[baseAddress + 2];
			this.softwareVersionSuffix = registerValues[baseAddress + 3];
			this.deviceType = registerValues[baseAddress + 4];

			this.remainingDays = registerValues[baseAddress + 5];
			this.authorizationErrorNumber = registerValues[baseAddress + 6];
			this.addOperation = registerValues[baseAddress + 7];
			this.serialFirst = registerValues[baseAddress + 8];
			this.serialSecond = registerValues[baseAddress + 9];

			this.serialThird = registerValues[baseAddress + 10];
			this.registrationFirst = registerValues[baseAddress + 11];
			this.registrationSecond = registerValues[baseAddress + 12];
			this.registrationThird = registerValues[baseAddress + 13];
			this.expiredYear = registerValues[baseAddress + 14];

			this.expiredMonth = registerValues[baseAddress + 15];
			this.expiredDay = registerValues[baseAddress + 16];
			this.expiredHour = registerValues[baseAddress + 17];
		}

	}

	public int getEquipmentBasicInfoId() {
		return equipmentBasicInfoId;
	}

	public void setEquipmentBasicInfoId(int equipmentBasicInfoId) {
		this.equipmentBasicInfoId = equipmentBasicInfoId;
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

	public int getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(int softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public int getHardwareVersion() {
		return hardwareVersion;
	}

	public void setHardwareVersion(int hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}

	public int getNewsoftwareVersion() {
		return newsoftwareVersion;
	}

	public void setNewsoftwareVersion(int newsoftwareVersion) {
		this.newsoftwareVersion = newsoftwareVersion;
	}

	public int getSoftwareVersionSuffix() {
		return softwareVersionSuffix;
	}

	public void setSoftwareVersionSuffix(int softwareVersionSuffix) {
		this.softwareVersionSuffix = softwareVersionSuffix;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public int getRemainingDays() {
		return remainingDays;
	}

	public void setRemainingDays(int remainingDays) {
		this.remainingDays = remainingDays;
	}

	public int getAuthorizationErrorNumber() {
		return authorizationErrorNumber;
	}

	public void setAuthorizationErrorNumber(int authorizationErrorNumber) {
		this.authorizationErrorNumber = authorizationErrorNumber;
	}

	public int getAddOperation() {
		return addOperation;
	}

	public void setAddOperation(int addOperation) {
		this.addOperation = addOperation;
	}

	public int getSerialFirst() {
		return serialFirst;
	}

	public void setSerialFirst(int serialFirst) {
		this.serialFirst = serialFirst;
	}

	public int getSerialSecond() {
		return serialSecond;
	}

	public void setSerialSecond(int serialSecond) {
		this.serialSecond = serialSecond;
	}

	public int getSerialThird() {
		return serialThird;
	}

	public void setSerialThird(int serialThird) {
		this.serialThird = serialThird;
	}

	public int getRegistrationFirst() {
		return registrationFirst;
	}

	public void setRegistrationFirst(int registrationFirst) {
		this.registrationFirst = registrationFirst;
	}

	public int getRegistrationSecond() {
		return registrationSecond;
	}

	public void setRegistrationSecond(int registrationSecond) {
		this.registrationSecond = registrationSecond;
	}

	public int getRegistrationThird() {
		return registrationThird;
	}

	public void setRegistrationThird(int registrationThird) {
		this.registrationThird = registrationThird;
	}

	public int getExpiredYear() {
		return expiredYear;
	}

	public void setExpiredYear(int expiredYear) {
		this.expiredYear = expiredYear;
	}

	public int getExpiredMonth() {
		return expiredMonth;
	}

	public void setExpiredMonth(int expiredMonth) {
		this.expiredMonth = expiredMonth;
	}

	public int getExpiredDay() {
		return expiredDay;
	}

	public void setExpiredDay(int expiredDay) {
		this.expiredDay = expiredDay;
	}

	public int getExpiredHour() {
		return expiredHour;
	}

	public void setExpiredHour(int expiredHour) {
		this.expiredHour = expiredHour;
	}

	@Override
	public String toString() {
		return "EquipmentBasicInfo [equipmentBasicInfoId=" + equipmentBasicInfoId + ", equipmentId=" + equipmentId
				+ ", modifiedTime=" + modifiedTime + ", softwareVersion=" + softwareVersion + ", hardwareVersion="
				+ hardwareVersion + ", newsoftwareVersion=" + newsoftwareVersion + ", softwareVersionSuffix="
				+ softwareVersionSuffix + ", deviceType=" + deviceType + ", remainingDays=" + remainingDays
				+ ", authorizationErrorNumber=" + authorizationErrorNumber + ", addOperation=" + addOperation
				+ ", serialFirst=" + serialFirst + ", serialSecond=" + serialSecond + ", serialThird=" + serialThird
				+ ", registrationFirst=" + registrationFirst + ", registrationSecond=" + registrationSecond
				+ ", registrationThird=" + registrationThird + ", expiredYear=" + expiredYear + ", expiredMonth="
				+ expiredMonth + ", expiredDay=" + expiredDay + ", expiredHour=" + expiredHour + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + addOperation;
		result = prime * result + authorizationErrorNumber;
		result = prime * result + deviceType;
		result = prime * result + equipmentId;
		result = prime * result + expiredDay;
		result = prime * result + expiredHour;
		result = prime * result + expiredMonth;
		result = prime * result + expiredYear;
		result = prime * result + hardwareVersion;
		result = prime * result + newsoftwareVersion;
		result = prime * result + registrationFirst;
		result = prime * result + registrationSecond;
		result = prime * result + registrationThird;
		/*result = prime * result + remainingDays;不监控剩余天数的变化*/
		result = prime * result + serialFirst;
		result = prime * result + serialSecond;
		result = prime * result + serialThird;
		result = prime * result + softwareVersion;
		result = prime * result + softwareVersionSuffix;
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
		EquipmentBasicInfo other = (EquipmentBasicInfo) obj;
		if (addOperation != other.addOperation)
			return false;
		if (authorizationErrorNumber != other.authorizationErrorNumber)
			return false;
		if (deviceType != other.deviceType)
			return false;
		if (equipmentId != other.equipmentId)
			return false;
		if (expiredDay != other.expiredDay)
			return false;
		if (expiredHour != other.expiredHour)
			return false;
		if (expiredMonth != other.expiredMonth)
			return false;
		if (expiredYear != other.expiredYear)
			return false;
		if (hardwareVersion != other.hardwareVersion)
			return false;
		if (newsoftwareVersion != other.newsoftwareVersion)
			return false;
		if (registrationFirst != other.registrationFirst)
			return false;
		if (registrationSecond != other.registrationSecond)
			return false;
		if (registrationThird != other.registrationThird)
			return false;
		/*if (remainingDays != other.remainingDays)
			return false;不监控剩余天数的变化*/
		if (serialFirst != other.serialFirst)
			return false;
		if (serialSecond != other.serialSecond)
			return false;
		if (serialThird != other.serialThird)
			return false;
		if (softwareVersion != other.softwareVersion)
			return false;
		if (softwareVersionSuffix != other.softwareVersionSuffix)
			return false;
		return true;
	}



}
