package com.hzmsc.scada.dao.dic;

import com.hzmsc.scada.entity.dic.EventType;;

public interface EventTypeDao {
	
	public EventType findById(int eventTypeId);
	public EventType findByCode(String eventTypeCode);
	public int countById(int eventTypeId);
	public int countByCode(String eventTypeCode);

}
