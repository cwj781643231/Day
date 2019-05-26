package com.hzmsc.scada.service.dic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.dic.EventTypeDao;
import com.hzmsc.scada.entity.dic.EventType;

@Service
public class EventTypeServiceImpl implements EventTypeService{
	
	@Autowired
	private EventTypeDao eventTypeDao;

	public EventType findById(int eventTypeId) {
		return eventTypeDao.findById(eventTypeId);
	}

	public EventType findByCode(String eventTypeCode) {
		return eventTypeDao.findByCode(eventTypeCode);
	}

	public int countById(int eventTypeId) {
		return eventTypeDao.countById(eventTypeId);
	}

	public int countByCode(String eventTypeCode) {
		return eventTypeDao.countByCode(eventTypeCode);
	}

}
