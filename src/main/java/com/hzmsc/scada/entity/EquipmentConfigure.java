package com.hzmsc.scada.entity;

import java.sql.Timestamp;
import java.util.Calendar;

/* 设备工艺，读写数据部分,41个数据
 * holdingregisters 41000开始
 * 工艺计划时间	     1	横动初始行程	 0	随机幅度	     	0	软边随机	     0	齿轮箱型硬度系数	 0
 * 锭子转速	  6000	横动终止行程	 0	软边硬度系数（同步型）0	工作模式	     0		
 * 捻度			600	收边开关	     0	卷绕方式	    	0	尾纱偏移距离	 0		
 * 加捻方向	     0	软边幅度	   	100	S型卷绕幅度	  	100	尾纱长度	     0		
 * 工艺卷绕角	     0	软边次数	     0	S型卷绕半周横动次数	0	捻度修正系数	 0		
 * 纱线定长—低	     0	纱筒直径比	     0	S型卷绕软边硬度系数	0	同步型2软边幅度	 0		
 * 纱长定长—高	     0	出边速度系数	 0	任务完成后关机		0	同步型2软边次数	 0		
 * 纱线定长修正系数	 0	收边速度系数	 0	边缘卷绕延时长度		0	同步型2硬度系数	 0		
 * 纱线支数	     0	出边/收边宽度	 0	防叠2长度	    	0	齿轮箱型软边幅度	 0		
 * 整机锭数	     0	随机时间	     0	防叠2周期	     	0	齿轮箱型软边次数	 0		
 */
public class EquipmentConfigure {
	private int equipmentConfigureId;
	private int equipmentId;
	private Timestamp modifiedTime;

	private int planningTime;
	private int spindleSpeed;
	private int twist;
	private int twistingDirection;
	private int windingAngle;
	private int yarnLengthLow;
	private int yarnLengthHigh;
	private int yarnLengthCorrectionFactor;
	private int yarnCount;
	private int spindleAmount;

	private int initialTraverseTravel;
	private int terminatedTraverseTravel;
	private int retractSideSwitch;
	private int softSideRange;
	private int softSideNumber;
	private int yarnSpoolDiameterRatio;
	private int outSideSpeedFactor;
	private int retractSideSpeedFactor;
	private int outRetractSideWidth;
	private int radomTime;

	private int randomAmplitude;
	private int softSideHardnessFactorSynchronous;
	private int windingFormat;
	private int sTypeWindingRange;
	private int sTypeWindingHalfLoopTraverseNumber;
	private int sTypeWindingSoftSideHardnessFactor;
	private int shutdownWhenTaskCompleted;
	private int edgeWindingDelayTime;
	private int preventOverlapTwoLength;
	private int preventOverlapTwoCycle;

	private int softSideRadom;
	private int operatingMode;
	private int tailYarnOffsetDistance;
	private int tailYarnLength;
	private int twistCorrectionFactor;
	private int syncTypeTwoSoftSideRange;
	private int syncTypeTwoSoftSideNumber;
	private int syncTypeTwoHardnessFactor;
	private int gearboxTypeSoftSideRange;
	private int gearboxTypeSoftSideNumber;

	private int gearboxTypeHardnessFactor;

	public EquipmentConfigure() {
		super();
	}

	//从设备读取数据进行初始化
	public EquipmentConfigure(int[] registerValues) {
		super();
		if (registerValues.length == 41) {
			int baseAddress = 0;
			// this.equipmentConfigureId =
			this.modifiedTime = new Timestamp(Calendar.getInstance().getTime().getTime());

			this.planningTime = registerValues[baseAddress];
			this.spindleSpeed = registerValues[baseAddress + 1];
			this.twist = registerValues[baseAddress + 2];
			this.twistingDirection = registerValues[baseAddress + 3];
			this.windingAngle = registerValues[baseAddress + 4];
			this.yarnLengthLow = registerValues[baseAddress + 5];
			this.yarnLengthHigh = registerValues[baseAddress + 6];
			this.yarnLengthCorrectionFactor = registerValues[baseAddress + 7];
			this.yarnCount = registerValues[baseAddress + 8];
			this.spindleAmount = registerValues[baseAddress + 9];

			this.initialTraverseTravel = registerValues[baseAddress + 10];
			this.terminatedTraverseTravel = registerValues[baseAddress + 11];
			this.retractSideSwitch = registerValues[baseAddress + 12];
			this.softSideRange = registerValues[baseAddress + 13];
			this.softSideNumber = registerValues[baseAddress + 14];
			this.yarnSpoolDiameterRatio = registerValues[baseAddress + 15];
			this.outSideSpeedFactor = registerValues[baseAddress + 16];
			this.retractSideSpeedFactor = registerValues[baseAddress + 17];
			this.outRetractSideWidth = registerValues[baseAddress + 18];
			this.radomTime = registerValues[baseAddress + 19];

			this.randomAmplitude = registerValues[baseAddress + 20];
			this.softSideHardnessFactorSynchronous = registerValues[baseAddress + 21];
			this.windingFormat = registerValues[baseAddress + 22];
			this.sTypeWindingRange = registerValues[baseAddress + 23];
			this.sTypeWindingHalfLoopTraverseNumber = registerValues[baseAddress + 24];
			this.sTypeWindingSoftSideHardnessFactor = registerValues[baseAddress + 25];
			this.shutdownWhenTaskCompleted = registerValues[baseAddress + 26];
			this.edgeWindingDelayTime = registerValues[baseAddress + 27];
			this.preventOverlapTwoLength = registerValues[baseAddress + 28];
			this.preventOverlapTwoCycle = registerValues[baseAddress + 29];

			this.softSideRadom = registerValues[baseAddress + 30];
			this.operatingMode = registerValues[baseAddress + 31];
			this.tailYarnOffsetDistance = registerValues[baseAddress + 32];
			this.tailYarnLength = registerValues[baseAddress + 33];
			this.twistCorrectionFactor = registerValues[baseAddress + 34];
			this.syncTypeTwoSoftSideRange = registerValues[baseAddress + 35];
			this.syncTypeTwoSoftSideNumber = registerValues[baseAddress + 36];
			this.syncTypeTwoHardnessFactor = registerValues[baseAddress + 37];
			this.gearboxTypeSoftSideRange = registerValues[baseAddress + 38];
			this.gearboxTypeSoftSideNumber = registerValues[baseAddress + 39];

			this.gearboxTypeHardnessFactor = registerValues[baseAddress + 40];

		}
	}

	public int getEquipmentConfigureId() {
		return equipmentConfigureId;
	}

	public void setEquipmentConfigureId(int equipmentConfigureId) {
		this.equipmentConfigureId = equipmentConfigureId;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public int getPlanningTime() {
		return planningTime;
	}

	public void setPlanningTime(int planningTime) {
		this.planningTime = planningTime;
	}

	public int getSpindleSpeed() {
		return spindleSpeed;
	}

	public void setSpindleSpeed(int spindleSpeed) {
		this.spindleSpeed = spindleSpeed;
	}

	public int getTwist() {
		return twist;
	}

	public void setTwist(int twist) {
		this.twist = twist;
	}

	public int getTwistingDirection() {
		return twistingDirection;
	}

	public void setTwistingDirection(int twistingDirection) {
		this.twistingDirection = twistingDirection;
	}

	public int getWindingAngle() {
		return windingAngle;
	}

	public void setWindingAngle(int windingAngle) {
		this.windingAngle = windingAngle;
	}

	public int getYarnLengthLow() {
		return yarnLengthLow;
	}

	public void setYarnLengthLow(int yarnLengthLow) {
		this.yarnLengthLow = yarnLengthLow;
	}

	public int getYarnLengthHigh() {
		return yarnLengthHigh;
	}

	public void setYarnLengthHigh(int yarnLengthHigh) {
		this.yarnLengthHigh = yarnLengthHigh;
	}

	public int getYarnLengthCorrectionFactor() {
		return yarnLengthCorrectionFactor;
	}

	public void setYarnLengthCorrectionFactor(int yarnLengthCorrectionFactor) {
		this.yarnLengthCorrectionFactor = yarnLengthCorrectionFactor;
	}

	public int getYarnCount() {
		return yarnCount;
	}

	public void setYarnCount(int yarnCount) {
		this.yarnCount = yarnCount;
	}

	public int getSpindleAmount() {
		return spindleAmount;
	}

	public void setSpindleAmount(int spindleAmount) {
		this.spindleAmount = spindleAmount;
	}

	public int getInitialTraverseTravel() {
		return initialTraverseTravel;
	}

	public void setInitialTraverseTravel(int initialTraverseTravel) {
		this.initialTraverseTravel = initialTraverseTravel;
	}

	public int getTerminatedTraverseTravel() {
		return terminatedTraverseTravel;
	}

	public void setTerminatedTraverseTravel(int terminatedTraverseTravel) {
		this.terminatedTraverseTravel = terminatedTraverseTravel;
	}

	public int getRetractSideSwitch() {
		return retractSideSwitch;
	}

	public void setRetractSideSwitch(int retractSideSwitch) {
		this.retractSideSwitch = retractSideSwitch;
	}

	public int getSoftSideRange() {
		return softSideRange;
	}

	public void setSoftSideRange(int softSideRange) {
		this.softSideRange = softSideRange;
	}

	public int getSoftSideNumber() {
		return softSideNumber;
	}

	public void setSoftSideNumber(int softSideNumber) {
		this.softSideNumber = softSideNumber;
	}

	public int getYarnSpoolDiameterRatio() {
		return yarnSpoolDiameterRatio;
	}

	public void setYarnSpoolDiameterRatio(int yarnSpoolDiameterRatio) {
		this.yarnSpoolDiameterRatio = yarnSpoolDiameterRatio;
	}

	public int getOutSideSpeedFactor() {
		return outSideSpeedFactor;
	}

	public void setOutSideSpeedFactor(int outSideSpeedFactor) {
		this.outSideSpeedFactor = outSideSpeedFactor;
	}

	public int getRetractSideSpeedFactor() {
		return retractSideSpeedFactor;
	}

	public void setRetractSideSpeedFactor(int retractSideSpeedFactor) {
		this.retractSideSpeedFactor = retractSideSpeedFactor;
	}

	public int getOutRetractSideWidth() {
		return outRetractSideWidth;
	}

	public void setOutRetractSideWidth(int outRetractSideWidth) {
		this.outRetractSideWidth = outRetractSideWidth;
	}

	public int getRadomTime() {
		return radomTime;
	}

	public void setRadomTime(int radomTime) {
		this.radomTime = radomTime;
	}

	public int getRandomAmplitude() {
		return randomAmplitude;
	}

	public void setRandomAmplitude(int randomAmplitude) {
		this.randomAmplitude = randomAmplitude;
	}

	public int getSoftSideHardnessFactorSynchronous() {
		return softSideHardnessFactorSynchronous;
	}

	public void setSoftSideHardnessFactorSynchronous(int softSideHardnessFactorSynchronous) {
		this.softSideHardnessFactorSynchronous = softSideHardnessFactorSynchronous;
	}

	public int getWindingFormat() {
		return windingFormat;
	}

	public void setWindingFormat(int windingFormat) {
		this.windingFormat = windingFormat;
	}

	public int getsTypeWindingRange() {
		return sTypeWindingRange;
	}

	public void setsTypeWindingRange(int sTypeWindingRange) {
		this.sTypeWindingRange = sTypeWindingRange;
	}

	public int getsTypeWindingHalfLoopTraverseNumber() {
		return sTypeWindingHalfLoopTraverseNumber;
	}

	public void setsTypeWindingHalfLoopTraverseNumber(int sTypeWindingHalfLoopTraverseNumber) {
		this.sTypeWindingHalfLoopTraverseNumber = sTypeWindingHalfLoopTraverseNumber;
	}

	public int getsTypeWindingSoftSideHardnessFactor() {
		return sTypeWindingSoftSideHardnessFactor;
	}

	public void setsTypeWindingSoftSideHardnessFactor(int sTypeWindingSoftSideHardnessFactor) {
		this.sTypeWindingSoftSideHardnessFactor = sTypeWindingSoftSideHardnessFactor;
	}

	public int getShutdownWhenTaskCompleted() {
		return shutdownWhenTaskCompleted;
	}

	public void setShutdownWhenTaskCompleted(int shutdownWhenTaskCompleted) {
		this.shutdownWhenTaskCompleted = shutdownWhenTaskCompleted;
	}

	public int getEdgeWindingDelayTime() {
		return edgeWindingDelayTime;
	}

	public void setEdgeWindingDelayTime(int edgeWindingDelayTime) {
		this.edgeWindingDelayTime = edgeWindingDelayTime;
	}

	public int getPreventOverlapTwoLength() {
		return preventOverlapTwoLength;
	}

	public void setPreventOverlapTwoLength(int preventOverlapTwoLength) {
		this.preventOverlapTwoLength = preventOverlapTwoLength;
	}

	public int getPreventOverlapTwoCycle() {
		return preventOverlapTwoCycle;
	}

	public void setPreventOverlapTwoCycle(int preventOverlapTwoCycle) {
		this.preventOverlapTwoCycle = preventOverlapTwoCycle;
	}

	public int getSoftSideRadom() {
		return softSideRadom;
	}

	public void setSoftSideRadom(int softSideRadom) {
		this.softSideRadom = softSideRadom;
	}

	public int getOperatingMode() {
		return operatingMode;
	}

	public void setOperatingMode(int operatingMode) {
		this.operatingMode = operatingMode;
	}

	public int getTailYarnOffsetDistance() {
		return tailYarnOffsetDistance;
	}

	public void setTailYarnOffsetDistance(int tailYarnOffsetDistance) {
		this.tailYarnOffsetDistance = tailYarnOffsetDistance;
	}

	public int getTailYarnLength() {
		return tailYarnLength;
	}

	public void setTailYarnLength(int tailYarnLength) {
		this.tailYarnLength = tailYarnLength;
	}

	public int getTwistCorrectionFactor() {
		return twistCorrectionFactor;
	}

	public void setTwistCorrectionFactor(int twistCorrectionFactor) {
		this.twistCorrectionFactor = twistCorrectionFactor;
	}

	public int getSyncTypeTwoSoftSideRange() {
		return syncTypeTwoSoftSideRange;
	}

	public void setSyncTypeTwoSoftSideRange(int syncTypeTwoSoftSideRange) {
		this.syncTypeTwoSoftSideRange = syncTypeTwoSoftSideRange;
	}

	public int getSyncTypeTwoSoftSideNumber() {
		return syncTypeTwoSoftSideNumber;
	}

	public void setSyncTypeTwoSoftSideNumber(int syncTypeTwoSoftSideNumber) {
		this.syncTypeTwoSoftSideNumber = syncTypeTwoSoftSideNumber;
	}

	public int getSyncTypeTwoHardnessFactor() {
		return syncTypeTwoHardnessFactor;
	}

	public void setSyncTypeTwoHardnessFactor(int syncTypeTwoHardnessFactor) {
		this.syncTypeTwoHardnessFactor = syncTypeTwoHardnessFactor;
	}

	public int getGearboxTypeSoftSideRange() {
		return gearboxTypeSoftSideRange;
	}

	public void setGearboxTypeSoftSideRange(int gearboxTypeSoftSideRange) {
		this.gearboxTypeSoftSideRange = gearboxTypeSoftSideRange;
	}

	public int getGearboxTypeSoftSideNumber() {
		return gearboxTypeSoftSideNumber;
	}

	public void setGearboxTypeSoftSideNumber(int gearboxTypeSoftSideNumber) {
		this.gearboxTypeSoftSideNumber = gearboxTypeSoftSideNumber;
	}

	public int getGearboxTypeHardnessFactor() {
		return gearboxTypeHardnessFactor;
	}

	public void setGearboxTypeHardnessFactor(int gearboxTypeHardnessFactor) {
		this.gearboxTypeHardnessFactor = gearboxTypeHardnessFactor;
	}

	public Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "EquipmentConfigure [equipmentConfigureId=" + equipmentConfigureId + ", equipmentId=" + equipmentId
				+ ", modifiedTime=" + modifiedTime + ", planningTime=" + planningTime + ", spindleSpeed=" + spindleSpeed
				+ ", twist=" + twist + ", twistingDirection=" + twistingDirection + ", windingAngle=" + windingAngle
				+ ", yarnLengthLow=" + yarnLengthLow + ", yarnLengthHigh=" + yarnLengthHigh
				+ ", yarnLengthCorrectionFactor=" + yarnLengthCorrectionFactor + ", yarnCount=" + yarnCount
				+ ", spindleAmount=" + spindleAmount + ", initialTraverseTravel=" + initialTraverseTravel
				+ ", terminatedTraverseTravel=" + terminatedTraverseTravel + ", retractSideSwitch=" + retractSideSwitch
				+ ", softSideRange=" + softSideRange + ", softSideNumber=" + softSideNumber
				+ ", yarnSpoolDiameterRatio=" + yarnSpoolDiameterRatio + ", outSideSpeedFactor=" + outSideSpeedFactor
				+ ", retractSideSpeedFactor=" + retractSideSpeedFactor + ", outRetractSideWidth=" + outRetractSideWidth
				+ ", radomTime=" + radomTime + ", randomAmplitude=" + randomAmplitude
				+ ", softSideHardnessFactorSynchronous=" + softSideHardnessFactorSynchronous + ", windingFormat="
				+ windingFormat + ", sTypeWindingRange=" + sTypeWindingRange + ", sTypeWindingHalfLoopTraverseNumber="
				+ sTypeWindingHalfLoopTraverseNumber + ", sTypeWindingSoftSideHardnessFactor="
				+ sTypeWindingSoftSideHardnessFactor + ", shutdownWhenTaskCompleted=" + shutdownWhenTaskCompleted
				+ ", edgeWindingDelayTime=" + edgeWindingDelayTime + ", preventOverlapTwoLength="
				+ preventOverlapTwoLength + ", preventOverlapTwoCycle=" + preventOverlapTwoCycle + ", softSideRadom="
				+ softSideRadom + ", operatingMode=" + operatingMode + ", tailYarnOffsetDistance="
				+ tailYarnOffsetDistance + ", tailYarnLength=" + tailYarnLength + ", twistCorrectionFactor="
				+ twistCorrectionFactor + ", syncTypeTwoSoftSideRange=" + syncTypeTwoSoftSideRange
				+ ", syncTypeTwoSoftSideNumber=" + syncTypeTwoSoftSideNumber + ", syncTypeTwoHardnessFactor="
				+ syncTypeTwoHardnessFactor + ", gearboxTypeSoftSideRange=" + gearboxTypeSoftSideRange
				+ ", gearboxTypeSoftSideNumber=" + gearboxTypeSoftSideNumber + ", gearboxTypeHardnessFactor="
				+ gearboxTypeHardnessFactor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + edgeWindingDelayTime;
		result = prime * result + equipmentId;
		result = prime * result + gearboxTypeHardnessFactor;
		result = prime * result + gearboxTypeSoftSideNumber;
		result = prime * result + gearboxTypeSoftSideRange;
		result = prime * result + initialTraverseTravel;
		result = prime * result + operatingMode;
		result = prime * result + outRetractSideWidth;
		result = prime * result + outSideSpeedFactor;
		result = prime * result + planningTime;
		result = prime * result + preventOverlapTwoCycle;
		result = prime * result + preventOverlapTwoLength;
		result = prime * result + radomTime;
		result = prime * result + randomAmplitude;
		result = prime * result + retractSideSpeedFactor;
		result = prime * result + retractSideSwitch;
		result = prime * result + sTypeWindingHalfLoopTraverseNumber;
		result = prime * result + sTypeWindingRange;
		result = prime * result + sTypeWindingSoftSideHardnessFactor;
		result = prime * result + shutdownWhenTaskCompleted;
		result = prime * result + softSideHardnessFactorSynchronous;
		result = prime * result + softSideNumber;
		result = prime * result + softSideRadom;
		result = prime * result + softSideRange;
		result = prime * result + spindleAmount;
		result = prime * result + spindleSpeed;
		result = prime * result + syncTypeTwoHardnessFactor;
		result = prime * result + syncTypeTwoSoftSideNumber;
		result = prime * result + syncTypeTwoSoftSideRange;
		result = prime * result + tailYarnLength;
		result = prime * result + tailYarnOffsetDistance;
		result = prime * result + terminatedTraverseTravel;
		result = prime * result + twist;
		result = prime * result + twistCorrectionFactor;
		result = prime * result + twistingDirection;
		result = prime * result + windingAngle;
		result = prime * result + windingFormat;
		result = prime * result + yarnCount;
		result = prime * result + yarnLengthCorrectionFactor;
		result = prime * result + yarnLengthHigh;
		result = prime * result + yarnLengthLow;
		result = prime * result + yarnSpoolDiameterRatio;
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
		EquipmentConfigure other = (EquipmentConfigure) obj;
		if (edgeWindingDelayTime != other.edgeWindingDelayTime)
			return false;
		if (equipmentId != other.equipmentId)
			return false;
		if (gearboxTypeHardnessFactor != other.gearboxTypeHardnessFactor)
			return false;
		if (gearboxTypeSoftSideNumber != other.gearboxTypeSoftSideNumber)
			return false;
		if (gearboxTypeSoftSideRange != other.gearboxTypeSoftSideRange)
			return false;
		if (initialTraverseTravel != other.initialTraverseTravel)
			return false;
		if (operatingMode != other.operatingMode)
			return false;
		if (outRetractSideWidth != other.outRetractSideWidth)
			return false;
		if (outSideSpeedFactor != other.outSideSpeedFactor)
			return false;
		if (planningTime != other.planningTime)
			return false;
		if (preventOverlapTwoCycle != other.preventOverlapTwoCycle)
			return false;
		if (preventOverlapTwoLength != other.preventOverlapTwoLength)
			return false;
		if (radomTime != other.radomTime)
			return false;
		if (randomAmplitude != other.randomAmplitude)
			return false;
		if (retractSideSpeedFactor != other.retractSideSpeedFactor)
			return false;
		if (retractSideSwitch != other.retractSideSwitch)
			return false;
		if (sTypeWindingHalfLoopTraverseNumber != other.sTypeWindingHalfLoopTraverseNumber)
			return false;
		if (sTypeWindingRange != other.sTypeWindingRange)
			return false;
		if (sTypeWindingSoftSideHardnessFactor != other.sTypeWindingSoftSideHardnessFactor)
			return false;
		if (shutdownWhenTaskCompleted != other.shutdownWhenTaskCompleted)
			return false;
		if (softSideHardnessFactorSynchronous != other.softSideHardnessFactorSynchronous)
			return false;
		if (softSideNumber != other.softSideNumber)
			return false;
		if (softSideRadom != other.softSideRadom)
			return false;
		if (softSideRange != other.softSideRange)
			return false;
		if (spindleAmount != other.spindleAmount)
			return false;
		if (spindleSpeed != other.spindleSpeed)
			return false;
		if (syncTypeTwoHardnessFactor != other.syncTypeTwoHardnessFactor)
			return false;
		if (syncTypeTwoSoftSideNumber != other.syncTypeTwoSoftSideNumber)
			return false;
		if (syncTypeTwoSoftSideRange != other.syncTypeTwoSoftSideRange)
			return false;
		if (tailYarnLength != other.tailYarnLength)
			return false;
		if (tailYarnOffsetDistance != other.tailYarnOffsetDistance)
			return false;
		if (terminatedTraverseTravel != other.terminatedTraverseTravel)
			return false;
		if (twist != other.twist)
			return false;
		if (twistCorrectionFactor != other.twistCorrectionFactor)
			return false;
		if (twistingDirection != other.twistingDirection)
			return false;
		if (windingAngle != other.windingAngle)
			return false;
		if (windingFormat != other.windingFormat)
			return false;
		if (yarnCount != other.yarnCount)
			return false;
		if (yarnLengthCorrectionFactor != other.yarnLengthCorrectionFactor)
			return false;
		if (yarnLengthHigh != other.yarnLengthHigh)
			return false;
		if (yarnLengthLow != other.yarnLengthLow)
			return false;
		if (yarnSpoolDiameterRatio != other.yarnSpoolDiameterRatio)
			return false;
		return true;
	}

	

}
