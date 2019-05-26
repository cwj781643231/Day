package com.hzmsc.scada.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.DailyScheduleDao;
import com.hzmsc.scada.entity.DailySchedule;

@Service
public class DailyScheduleServiceImpl implements DailyScheduleService {
	
	@Autowired
	private DailyScheduleDao dailyScheduleDao;

	public DailySchedule findById(int dailyScheduleId) {
		return dailyScheduleDao.findById(dailyScheduleId);
	}

	public int countOfId(int dailyScheduleId) {
		return dailyScheduleDao.countOfId(dailyScheduleId);
	}

	public List<DailySchedule> findAll() {
		return dailyScheduleDao.findAll();
	}
	
	public List<DailySchedule> findByDay(String scheduleDay) {
		return dailyScheduleDao.findByDay(scheduleDay);
	}
	
	public List<DailySchedule> findBetweenDays(String beginDay, String endDay){
		return dailyScheduleDao.findBetweenDays(beginDay, endDay);
	}
	

	public DailySchedule createDailySchedule(DailySchedule dailySchedule) {
		dailySchedule.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		return dailyScheduleDao.createDailySchedule(dailySchedule);
	}

	public void updateDailySchedule(DailySchedule dailySchedule) {
		dailySchedule.setModifiedTime(new Timestamp(System.currentTimeMillis()));
		dailyScheduleDao.updateDailySchedule(dailySchedule);

	}

	public void deleteDailySchedule(DailySchedule dailySchedule) {
		dailyScheduleDao.deleteDailySchedule(dailySchedule);

	}
	
	public void deletePeriodSchedule(String beginDate, String endDate){
		dailyScheduleDao.deletePeriodSchedule(beginDate, endDate);
	}

	/*//查询出每个设备的总产量
	public List<DailySchedule> sumproduction(String beginDay, String endDay){
		return dailyScheduleDao.sumproduction(beginDay, endDay);
	}
	//根据设备ID 查询出该设备每天的总产量
	public List<DailySchedule> sumequipbyid(String beginDay, String endDay,int equipmentId){
		return dailyScheduleDao.sumequipbyid(beginDay, endDay, equipmentId);
	}
	//设备对应的产量
	public List<Object> listint(String beginDay, String endDay){
		return dailyScheduleDao.listint(beginDay, endDay);
	}
	public List<Object> listsumequipbyid(String beginDay, String endDay, int equipmentId){
		return dailyScheduleDao.listsumequipbyid(beginDay, endDay,equipmentId);
	}*/
	/*------------------------员工--------------------------*/
	public List<DailySchedule> sumemployee(String beginDay, String endDay){
		return dailyScheduleDao.sumemployee(beginDay, endDay);
	}
	
	//根据设备ID 查询出该设备每天的总产量
	/*
	 * 现复制到  productionDao  里面
	 * */
	public List<DailySchedule> sumempbyid(String beginDay, String endDay,int employeeId){
		return dailyScheduleDao.sumempbyid(beginDay, endDay, employeeId);
	}
	
	//设备对应的产量
	public List<Object> sumempsta(String beginDay, String endDay){
		return dailyScheduleDao.sumempsta(beginDay, endDay);
	}
	public List<Object> listsumempbyid(String beginDay, String endDay, int employeeId){
		return dailyScheduleDao.listsumempbyid(beginDay, endDay,employeeId);
	}
	
	@Override
	public int countByTime(String beginTime, String endTime) {
		
		return dailyScheduleDao.countByTime(beginTime, endTime);
	}
	
	public int countByTimeId(int equipmentId,String beginTime, String endTime) {
	
		return dailyScheduleDao.countByTimeId(equipmentId, beginTime, endTime);
	}
	
	@Override
	public List<DailySchedule> getfindBetweenDays(String beginTime, String endTime,int startIndex,int pageSize) {
		
		return dailyScheduleDao.getfindBetweenDays(beginTime, endTime, startIndex, pageSize);
	}
	
	public List<DailySchedule> getfindBetweenDaysById(int equipmentId,String beginTime, String endTime,int startIndex,int pageSize) {
	
		return dailyScheduleDao.getfindBetweenDaysById(equipmentId, beginTime, endTime, startIndex, pageSize);
	}
}
