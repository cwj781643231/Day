package com.hzmsc.scada.dao.dic;

import java.util.Date;
import java.util.List;

import com.hzmsc.scada.entity.dic.ScheduleType;

public interface ScheduleTypeDao {

	public ScheduleType findById(int scheduleTypeId);

	public int countOfScheduleId(int scheduleTypeId);

	public List<ScheduleType> findAll();
	public List<ScheduleType> findByScheduleTypeCode(String scheduleTypeCode);
	
	public ScheduleType createScheduleType(final ScheduleType scheduleType);
	public void updateScheduleType(ScheduleType scheduleType);
	public void deleteScheduleType(ScheduleType scheduleType);
	
	//判断开启的时间段
	public List<ScheduleType> findByScheduleTypestate();
	
	
	

}
