package com.hzmsc.scada.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.hzmsc.scada.entity.DailySchedule;

public interface DailyScheduleDao {
	
	public DailySchedule findById(int dailyScheduleId);

	public int countOfId(int dailyScheduleId);

	public List<DailySchedule> findAll();
	public List<DailySchedule> findByDay(String scheduleDay);
	public List<DailySchedule> findBetweenDays(String beginDay, String endDay);
	
	public DailySchedule createDailySchedule(final DailySchedule dailySchedule);
	public void updateDailySchedule(DailySchedule dailySchedule);
	public void deleteDailySchedule(DailySchedule dailySchedule);
	public void deletePeriodSchedule(String beginDate, String endDate);

	
	/*//查询出每个设备的总产量
	public List<DailySchedule> sumproduction(String beginDay, String endDay);
	//设备对应的产量
	public List<Object> listint(String beginDay, String endDay);
	//根据设备ID 查询设备  每天的总产量
	public List<DailySchedule> sumequipbyid(String beginDay, String endDay,int equipmentId);
	
	public List<Object> listsumequipbyid(String beginDay, String endDay, int equipmentId);
	
	public int allproduction(String beginDay, String endDay);*/
	
	//---------------- 员工  -----------------------
	//查询出每个设备的总产量
	public List<DailySchedule> sumemployee(String beginDay, String endDay);
	//设备对应的产量
	public List<Object> sumempsta(String beginDay, String endDay);
	//根据设备ID 查询设备  每天的总产量
	public List<DailySchedule> sumempbyid(String beginDay, String endDay,int employeeId);
		
	public List<Object> listsumempbyid(String beginDay, String endDay, int employeeId);

	//根据设备Id 和 时间  查询该设备隶属于哪一个时间段
	public List<DailySchedule> schedulingTime(String scheduleDay,int equipmentId);

	//----------分页
	public int countByTime(String beginTime, String endTime);
		
	public int countByTimeId(int equipmentId,String beginTime, String endTime);
	
	public List<DailySchedule> getfindBetweenDays(String beginDay, String endDay,int startIndex,int pageSize);
	
	public List<DailySchedule> getfindBetweenDaysById(int equipmentId, String beginDay, String endDay,int startIndex,int pageSize);
}
