package com.hzmsc.scada.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.EventDao;
import com.hzmsc.scada.entity.Event;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventDao eventDao;

	public Event createEvent(Event event) {
		return eventDao.createEvent(event);
	}

	public void updateEvent(Event event) {
		eventDao.updateEvent(event);
		
	}

	public void deleteEvent(Event event) {
		eventDao.deleteEvent(event);
		
	}

	public Event findById(int eventId) {
		return eventDao.findById(eventId);
	}

	public List<Event> findAll() {
		return eventDao.findAll();
	}
	
	public List<Event> findBetweenTime(String beginTime, String endTime) {
		return eventDao.findBetweenTime(beginTime, endTime);
	}

	public List<Event> findByEquipmentId(int equipmentId) {
		return eventDao.findByEquipmentId(equipmentId);
	}

	public List<Event> findByEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime, Timestamp endTime) {
		return eventDao.findByEquipmentIdBetweenTime(equipmentId, beginTime, endTime);
	}

	public int countById(int eventId) {
		return eventDao.countById(eventId);
	}

	public int countByEquipmentId(int equipmentId) {
		return eventDao.countByEquipmentId(equipmentId);
	}

	@Override
	public List<Event> pageQuery(String beginTime, String endTime, int startIndex, int pageSize) {
		
		return eventDao.pageQuery(beginTime, endTime, startIndex, pageSize);
	}

	@Override
	public int countByTime(String beginTime, String endTime) {
		
		return eventDao.countByTime(beginTime, endTime);
	}
	
	public int countByTimeId(int equipmentId,String beginTime, String endTime) {
	
		return eventDao.countByTimeId(equipmentId, beginTime, endTime);
	}

	public List<Event> pageQueryById(int equipmentId,String beginTime, String endTime,int startIndex,int pageSize ) {

		return eventDao.pageQueryById(equipmentId, beginTime, endTime, startIndex, pageSize);
	}
}
