package com.hzmsc.scada.dao;

import java.sql.Timestamp;
import java.util.List;

import com.hzmsc.scada.entity.Event;

public interface EventDao {
	
	public Event createEvent(Event event);
	public void updateEvent(Event event);
	public void deleteEvent(Event event);
	
	public Event findById(int eventId);
	public List<Event> findAll();
	public List<Event> findBetweenTime(String beginTime, String endTime);
	public List<Event> findByEquipmentId(int equipmentId);
	public List<Event> findByEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime, Timestamp endTime);
	
	public int countById(int eventId);
	public int countByEquipmentId(int equipmentId);

	
	/*
	 * 分页以及条件查询
	 * */
	public List<Event> pageQuery(String beginTime, String endTime,int startIndex,int pageSize );
	/*
	 *  某段时间内 的总数据量 有多少
	 * */
	public int countByTime(String beginTime, String endTime);
	
	public int countByTimeId(int equipmentId,String beginTime, String endTime);
	
	public List<Event> pageQueryById(int equipmentId,String beginTime, String endTime,int startIndex,int pageSize );
}
