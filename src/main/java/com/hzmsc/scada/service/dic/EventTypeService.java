package com.hzmsc.scada.service.dic;

import com.hzmsc.scada.entity.dic.EventType;

public interface EventTypeService {
	
	public EventType findById(int eventTypeId);
	public EventType findByCode(String eventTypeCode);
	public int countById(int eventTypeId);
	public int countByCode(String eventTypeCode);

}
