package com.hzmsc.scada.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.EquipmentOperationRateDao;
import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.entity.EquipmentOperationRate;

@Service
public class EquipmentOperationRateServiceImpl implements EquipmentOperationRateService {

	@Autowired
	private EquipmentOperationRateDao equipmentOperationRateDao;



	/**
	 * statusId;statusName;statusCN;statusEN
	 * -1;DEVICE_STATUS_CAN_NOT_CONNECT_TO_GATEWAY;无法连接网关;\N
	 * 0;DEVICE_STATUS_POWER_ON;上电停机;\N 
	 * 1;DEVICE_STATUS_STARTOVER;设备启动中;\N
	 * 2;DEVICE_STATUS_NORMAL_RUN;设备运行;\N
	 * 3;DEVICE_STATUS_NORMAL_RUN_2_STOP;设备停机中;\N
	 * 4;DEVICE_STATUS_NORMAL_STOP;设备停机;\N
	 * 5;DEVICE_STATUS_WORK_DOWN_AWAIT;设备待机;\N
	 * 
	 * OperationTime = 1 + 2;
	 * WaitTime = 5;
	 * OnTime = OperationTime + WaitTime;
	 * OffTime = -1 + 3 + 4;
	 * 加上上一次的时间，计算累计时间。
	 */
	public EquipmentOperationRate calculateOperationRate(EquipmentOperationRate lastEquipmentOperationRate, Equipment lastEquipment, Equipment currentEquipment) {
		EquipmentOperationRate equipmentOperationRate = new EquipmentOperationRate(lastEquipmentOperationRate);

		equipmentOperationRate.setModifiedTime(new Timestamp(System.currentTimeMillis()));
		
		
		long timePeroid = currentEquipment.getModifiedTime().getTime() - lastEquipment.getModifiedTime().getTime();
		int status = lastEquipment.getEquipmentStatus().getEquipmentStatus();
		
		if (status == 1 || status == 2) {
			equipmentOperationRate.setOperationTime(lastEquipmentOperationRate.getOperationTime() + timePeroid);

			equipmentOperationRate.setOnTime(lastEquipmentOperationRate.getOnTime() + timePeroid);

		} else if (status == 5) {
			equipmentOperationRate.setWaitTime(lastEquipmentOperationRate.getWaitTime() + timePeroid);

			equipmentOperationRate.setOnTime(lastEquipmentOperationRate.getOnTime() + timePeroid);

		} else {
			equipmentOperationRate.setOffTime(lastEquipmentOperationRate.getOffTime() + timePeroid);

		}
		return equipmentOperationRate;//保存到数据库，并返回
	}
	
	public EquipmentOperationRate getOperationRate(int equipmentId) {
		EquipmentOperationRate equipmentOperationRate = new EquipmentOperationRate();
		equipmentOperationRate.setEquipmentId(equipmentId);
		equipmentOperationRate.setOperationTime(this.getOperationTime(equipmentId));
		equipmentOperationRate.setOnTime(this.getOnTime(equipmentId));
		equipmentOperationRate.setOffTime(this.getOffTime(equipmentId));
		equipmentOperationRate.setWaitTime(this.getWaitTime(equipmentId));
		
		return equipmentOperationRate;
	}

	public EquipmentOperationRate getOperationRate(int equipmentId, Timestamp beginTime, Timestamp endTime) {
		EquipmentOperationRate equipmentOperationRate = new EquipmentOperationRate();
		equipmentOperationRate.setEquipmentId(equipmentId);
		equipmentOperationRate.setOperationTime(this.getOperationTime(equipmentId, beginTime, endTime));
		equipmentOperationRate.setOnTime(this.getOnTime(equipmentId, beginTime, endTime));
		equipmentOperationRate.setOffTime(this.getOffTime(equipmentId, beginTime, endTime));
		equipmentOperationRate.setWaitTime(this.getWaitTime(equipmentId, beginTime, endTime));
		
		return equipmentOperationRate;
	}


	public long getOperationTime(int equipmentId) {
		long operationTime = 0;

		// 有记录
		if (this.countOfFindByEquipmentId(equipmentId) > 0) {

			EquipmentOperationRate equipmentOperationRateMax = this.findByEquipmentIdLatestBeforeTime(equipmentId);
			EquipmentOperationRate equipmentOperationRateMin = this.findByEquipmentIdFirstAfterTime(equipmentId);

			operationTime = equipmentOperationRateMax.getOperationTime() - equipmentOperationRateMin.getOperationTime();
		}

		return operationTime;
	}

	public long getOperationTime(int equipmentId, Timestamp beginTime, Timestamp endTime) {
		long operationTime = 0;

		// 有记录
		if (this.countOfFindByEquipmentIdBetweenTime(equipmentId, beginTime, endTime) > 0) {

			EquipmentOperationRate equipmentOperationRateMax = this.findByEquipmentIdLatestBeforeTime(equipmentId, endTime);
			EquipmentOperationRate equipmentOperationRateMin = this.findByEquipmentIdFirstAfterTime(equipmentId, beginTime);

			operationTime = equipmentOperationRateMax.getOperationTime() - equipmentOperationRateMin.getOperationTime();
		}

		return operationTime;
	}

	public long getOnTime(int equipmentId) {
		long onTime = 0;
		// 有记录
		if (this.countOfFindByEquipmentId(equipmentId) > 0) {

			EquipmentOperationRate equipmentOperationRateMax = this.findByEquipmentIdLatestBeforeTime(equipmentId);
			EquipmentOperationRate equipmentOperationRateMin = this.findByEquipmentIdFirstAfterTime(equipmentId);

			onTime = equipmentOperationRateMax.getOnTime() - equipmentOperationRateMin.getOnTime();
		}
		return onTime;
	}

	public long getOnTime(int equipmentId, Timestamp beginTime, Timestamp endTime) {
		long onTime = 0;
		// 有记录
		if (this.countOfFindByEquipmentIdBetweenTime(equipmentId, beginTime, endTime) > 0) {

			EquipmentOperationRate equipmentOperationRateMax = this.findByEquipmentIdLatestBeforeTime(equipmentId, endTime);
			EquipmentOperationRate equipmentOperationRateMin = this.findByEquipmentIdFirstAfterTime(equipmentId, beginTime);

			onTime = equipmentOperationRateMax.getOnTime() - equipmentOperationRateMin.getOnTime();
		}
		return onTime;
	}

	public long getOffTime(int equipmentId) {

		long offTime = 0;
		// 有记录
		if (this.countOfFindByEquipmentId(equipmentId) > 0) {

			EquipmentOperationRate equipmentOperationRateMax = this.findByEquipmentIdLatestBeforeTime(equipmentId);
			EquipmentOperationRate equipmentOperationRateMin = this.findByEquipmentIdFirstAfterTime(equipmentId);

			offTime = equipmentOperationRateMax.getOffTime() - equipmentOperationRateMin.getOffTime();
		}
		return offTime;
	}

	public long getOffTime(int equipmentId, Timestamp beginTime, Timestamp endTime) {

		long offTime = 0;
		// 有记录
		if (this.countOfFindByEquipmentIdBetweenTime(equipmentId, beginTime, endTime) > 0) {

			EquipmentOperationRate equipmentOperationRateMax = this.findByEquipmentIdLatestBeforeTime(equipmentId, endTime);
			EquipmentOperationRate equipmentOperationRateMin = this.findByEquipmentIdFirstAfterTime(equipmentId, beginTime);

			offTime = equipmentOperationRateMax.getOffTime() - equipmentOperationRateMin.getOffTime();
		}
		return offTime;
	}

	public long getWaitTime(int equipmentId) {

		long waitTime = 0;
		// 有记录
		if (this.countOfFindByEquipmentId(equipmentId) > 0) {

			EquipmentOperationRate equipmentOperationRateMax = this.findByEquipmentIdLatestBeforeTime(equipmentId);
			EquipmentOperationRate equipmentOperationRateMin = this.findByEquipmentIdFirstAfterTime(equipmentId);

			waitTime = equipmentOperationRateMax.getWaitTime() - equipmentOperationRateMin.getWaitTime();
		}
		return waitTime;
	}

	public long getWaitTime(int equipmentId, Timestamp beginTime, Timestamp endTime) {

		long waitTime = 0;
		// 有记录
		if (this.countOfFindByEquipmentIdBetweenTime(equipmentId, beginTime, endTime) > 0) {

			EquipmentOperationRate equipmentOperationRateMax = this.findByEquipmentIdLatestBeforeTime(equipmentId, endTime);
			EquipmentOperationRate equipmentOperationRateMin = this.findByEquipmentIdFirstAfterTime(equipmentId, beginTime);

			waitTime = equipmentOperationRateMax.getWaitTime() - equipmentOperationRateMin.getWaitTime();
		}
		return waitTime;
	}

	public EquipmentOperationRate createEquipmentOperationRate(EquipmentOperationRate equipmentOperationRate) {
		return equipmentOperationRateDao.createEquipmentOperationRate(equipmentOperationRate);
	}

	public void deleteEquipmentOperationRate(EquipmentOperationRate equipmentOperationRate) {
		equipmentOperationRateDao.deleteEquipmentOperationRate(equipmentOperationRate);
	}

	public void updateEquipmentOperationRate(EquipmentOperationRate equipmentStauts) {
		equipmentOperationRateDao.updateEquipmentOperationRate(equipmentStauts);
	}

	public EquipmentOperationRate findById(int equipmentOperationRateId) {
		return equipmentOperationRateDao.findById(equipmentOperationRateId);
	}

	public List<EquipmentOperationRate> findAll() {
		return equipmentOperationRateDao.findAll();
	}

	public List<EquipmentOperationRate> findByEquipmentId(int equipmentId) {
		return equipmentOperationRateDao.findByEquipmentId(equipmentId);
	}

	public EquipmentOperationRate findByEquipmentIdFirstAfterTime(int equipmentId) {
		return equipmentOperationRateDao.findByEquipmentIdFirstAfterTime(equipmentId);
	}

	public EquipmentOperationRate findByEquipmentIdLatestBeforeTime(int equipmentId) {
		return equipmentOperationRateDao.findByEquipmentIdLatestBeforeTime(equipmentId);
	}

	public List<EquipmentOperationRate> findByEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime,
			Timestamp endTime) {
		return equipmentOperationRateDao.findByEquipmentIdBetweenTime(equipmentId, beginTime, endTime);
	}

	public EquipmentOperationRate findByEquipmentIdFirstAfterTime(int equipmentId, Timestamp beginTime) {
		return equipmentOperationRateDao.findByEquipmentIdFirstAfterTime(equipmentId, beginTime);
	}

	public EquipmentOperationRate findByEquipmentIdLatestBeforeTime(int equipmentId, Timestamp endTime) {
		return equipmentOperationRateDao.findByEquipmentIdLatestBeforeTime(equipmentId, endTime);
	}

	public int countOfEquipmentOperationRateId(int equipmentOperationRateId) {
		return equipmentOperationRateDao.countOfEquipmentOperationRateId(equipmentOperationRateId);
	}

	public int countOfFindByEquipmentId(int equipmentId) {
		return equipmentOperationRateDao.countOfFindByEquipmentId(equipmentId);
	}

	public int countOfFindByEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime, Timestamp endTime) {
		return equipmentOperationRateDao.countOfFindByEquipmentIdBetweenTime(equipmentId, beginTime, endTime);
	}


	
}
