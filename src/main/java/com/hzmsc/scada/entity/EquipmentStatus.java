package com.hzmsc.scada.entity;

import java.sql.Timestamp;
import java.util.Calendar;

/* 设备状态信息-只读参数部分,17个数据
 * holdingregisters 42000开始
 * 机器状态	    	4		加捻电机锭速(实测)	    0
 * 实测捻度	   	 	0		卷取罗拉转速(实测)	    0
 * 任务完成百分比	  	271		当前横动宽度	 		1520
 * 故障代码	    	0		加捻电机读取频率	    	0
 * 剩余运行小时数	   	36		卷绕电机读取频率	    	0
 * 剩余运行分钟数	   	26		加捻电机读取电流	    	0
 * 剩余运行秒数		   	11		卷绕电机读取电流	    	0
 * 纱线长度低半字		31792		
 * 纱线长度高半字	    2		
 * 纱筒重量	 		1000		
 */
public class EquipmentStatus {

	private int equipmentStatusId;
	private int equipmentId;
	private Timestamp modifiedTime;

	private int equipmentStatus;
	private int measuredTwist;
	private int taskCompletion;
	private int malfunction;
	private int remainmingHours;
	private int remainmingMinutes;
	private int remainmingSeconds;
	private int yarnLengthLowHalfWord;
	private int yarnLengthHighHalfWord;
	private int yarnSpoolWeight;

	private int measuredTwistingMotorSpindleSpeed;
	private int measuredCoilingRollerSpeed;
	private int currentTraversingWidth;
	private int twistingMotorReadFrequency;
	private int windingMotorReadFrequency;
	private int twistingMotorReadCurrent;
	private int windingMotorReadCurrent;

	public EquipmentStatus() {
		super();
	}

	// 从设备读取数据进行初始化
	public EquipmentStatus(int[] registerValues) {
		super();
		if (registerValues.length == 17) {
			int baseAddress = 0;
			// this.equipmentStatusId =
			this.modifiedTime = new Timestamp(Calendar.getInstance().getTime().getTime());

			this.equipmentStatus = registerValues[baseAddress];
			this.measuredTwist = registerValues[baseAddress + 1];
			this.taskCompletion = registerValues[baseAddress + 2];
			this.malfunction = registerValues[baseAddress + 3];
			this.remainmingHours = registerValues[baseAddress + 4];
			this.remainmingMinutes = registerValues[baseAddress + 5];
			this.remainmingSeconds = registerValues[baseAddress + 6];
			this.yarnLengthLowHalfWord = registerValues[baseAddress + 7];
			this.yarnLengthHighHalfWord = registerValues[baseAddress + 8];
			this.yarnSpoolWeight = registerValues[baseAddress + 9];

			this.measuredTwistingMotorSpindleSpeed = registerValues[baseAddress + 10];
			this.measuredCoilingRollerSpeed = registerValues[baseAddress + 11];
			this.currentTraversingWidth = registerValues[baseAddress + 12];
			this.twistingMotorReadFrequency = registerValues[baseAddress + 13];
			this.windingMotorReadFrequency = registerValues[baseAddress + 14];
			this.twistingMotorReadCurrent = registerValues[baseAddress + 15];
			this.windingMotorReadCurrent = registerValues[baseAddress + 16];

		}
	}

	public int getEquipmentStatusId() {
		return equipmentStatusId;
	}

	public void setEquipmentStatusId(int equipmentStatusId) {
		this.equipmentStatusId = equipmentStatusId;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public int getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(int equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	public int getMeasuredTwist() {
		return measuredTwist;
	}

	public void setMeasuredTwist(int measuredTwist) {
		this.measuredTwist = measuredTwist;
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

	public int getRemainmingHours() {
		return remainmingHours;
	}

	public void setRemainmingHours(int remainmingHours) {
		this.remainmingHours = remainmingHours;
	}

	public int getRemainmingMinutes() {
		return remainmingMinutes;
	}

	public void setRemainmingMinutes(int remainmingMinutes) {
		this.remainmingMinutes = remainmingMinutes;
	}

	public int getRemainmingSeconds() {
		return remainmingSeconds;
	}

	public void setRemainmingSeconds(int remainmingSeconds) {
		this.remainmingSeconds = remainmingSeconds;
	}

	public int getYarnLengthLowHalfWord() {
		return yarnLengthLowHalfWord;
	}

	public void setYarnLengthLowHalfWord(int yarnLengthLowHalfWord) {
		this.yarnLengthLowHalfWord = yarnLengthLowHalfWord;
	}

	public int getYarnLengthHighHalfWord() {
		return yarnLengthHighHalfWord;
	}

	public void setYarnLengthHighHalfWord(int yarnLengthHighHalfWord) {
		this.yarnLengthHighHalfWord = yarnLengthHighHalfWord;
	}

	public int getMeasuredTwistingMotorSpindleSpeed() {
		return measuredTwistingMotorSpindleSpeed;
	}

	public void setMeasuredTwistingMotorSpindleSpeed(int measuredTwistingMotorSpindleSpeed) {
		this.measuredTwistingMotorSpindleSpeed = measuredTwistingMotorSpindleSpeed;
	}

	public int getMeasuredCoilingRollerSpeed() {
		return measuredCoilingRollerSpeed;
	}

	public void setMeasuredCoilingRollerSpeed(int measuredCoilingRollerSpeed) {
		this.measuredCoilingRollerSpeed = measuredCoilingRollerSpeed;
	}

	public int getCurrentTraversingWidth() {
		return currentTraversingWidth;
	}

	public void setCurrentTraversingWidth(int currentTraversingWidth) {
		this.currentTraversingWidth = currentTraversingWidth;
	}

	public int getTwistingMotorReadFrequency() {
		return twistingMotorReadFrequency;
	}

	public void setTwistingMotorReadFrequency(int twistingMotorReadFrequency) {
		this.twistingMotorReadFrequency = twistingMotorReadFrequency;
	}

	public int getWindingMotorReadFrequency() {
		return windingMotorReadFrequency;
	}

	public void setWindingMotorReadFrequency(int windingMotorReadFrequency) {
		this.windingMotorReadFrequency = windingMotorReadFrequency;
	}

	public int getTwistingMotorReadCurrent() {
		return twistingMotorReadCurrent;
	}

	public void setTwistingMotorReadCurrent(int twistingMotorReadCurrent) {
		this.twistingMotorReadCurrent = twistingMotorReadCurrent;
	}

	public int getWindingMotorReadCurrent() {
		return windingMotorReadCurrent;
	}

	public void setWindingMotorReadCurrent(int windingMotorReadCurrent) {
		this.windingMotorReadCurrent = windingMotorReadCurrent;
	}

	public int getYarnSpoolWeight() {
		return yarnSpoolWeight;
	}

	public void setYarnSpoolWeight(int yarnSpoolWeight) {
		this.yarnSpoolWeight = yarnSpoolWeight;
	}

	public Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "EquipmentStatus [equipmentStatusId=" + equipmentStatusId + ", equipmentId=" + equipmentId
				+ ", modifiedTime=" + modifiedTime + ", equipmentStatus=" + equipmentStatus + ", measuredTwist="
				+ measuredTwist + ", taskCompletion=" + taskCompletion + ", malfunction=" + malfunction
				+ ", remainmingHours=" + remainmingHours + ", remainmingMinutes=" + remainmingMinutes
				+ ", remainmingSeconds=" + remainmingSeconds + ", yarnLengthLowHalfWord=" + yarnLengthLowHalfWord
				+ ", yarnLengthHighHalfWord=" + yarnLengthHighHalfWord + ", yarnSpoolWeight=" + yarnSpoolWeight
				+ ", measuredTwistingMotorSpindleSpeed=" + measuredTwistingMotorSpindleSpeed
				+ ", measuredCoilingRollerSpeed=" + measuredCoilingRollerSpeed + ", currentTraversingWidth="
				+ currentTraversingWidth + ", twistingMotorReadFrequency=" + twistingMotorReadFrequency
				+ ", windingMotorReadFrequency=" + windingMotorReadFrequency + ", twistingMotorReadCurrent="
				+ twistingMotorReadCurrent + ", windingMotorReadCurrent=" + windingMotorReadCurrent + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentTraversingWidth;
		result = prime * result + equipmentId;
		result = prime * result + equipmentStatus;
		result = prime * result + malfunction;
		result = prime * result + measuredCoilingRollerSpeed;
		result = prime * result + measuredTwist;
		result = prime * result + measuredTwistingMotorSpindleSpeed;
		result = prime * result + remainmingHours;
		result = prime * result + remainmingMinutes;
		result = prime * result + remainmingSeconds;
		result = prime * result + taskCompletion;
		result = prime * result + twistingMotorReadCurrent;
		result = prime * result + twistingMotorReadFrequency;
		result = prime * result + windingMotorReadCurrent;
		result = prime * result + windingMotorReadFrequency;
		result = prime * result + yarnLengthHighHalfWord;
		result = prime * result + yarnLengthLowHalfWord;
		result = prime * result + yarnSpoolWeight;
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
		EquipmentStatus other = (EquipmentStatus) obj;
		if (currentTraversingWidth != other.currentTraversingWidth)
			return false;
		if (equipmentId != other.equipmentId)
			return false;
		if (equipmentStatus != other.equipmentStatus)
			return false;
		if (malfunction != other.malfunction)
			return false;
		if (measuredCoilingRollerSpeed != other.measuredCoilingRollerSpeed)
			return false;
		if (measuredTwist != other.measuredTwist)
			return false;
		if (measuredTwistingMotorSpindleSpeed != other.measuredTwistingMotorSpindleSpeed)
			return false;
		if (remainmingHours != other.remainmingHours)
			return false;
		if (remainmingMinutes != other.remainmingMinutes)
			return false;
		if (remainmingSeconds != other.remainmingSeconds)
			return false;
		if (taskCompletion != other.taskCompletion)
			return false;
		if (twistingMotorReadCurrent != other.twistingMotorReadCurrent)
			return false;
		if (twistingMotorReadFrequency != other.twistingMotorReadFrequency)
			return false;
		if (windingMotorReadCurrent != other.windingMotorReadCurrent)
			return false;
		if (windingMotorReadFrequency != other.windingMotorReadFrequency)
			return false;
		if (yarnLengthHighHalfWord != other.yarnLengthHighHalfWord)
			return false;
		if (yarnLengthLowHalfWord != other.yarnLengthLowHalfWord)
			return false;
		if (yarnSpoolWeight != other.yarnSpoolWeight)
			return false;
		return true;
	}



}
