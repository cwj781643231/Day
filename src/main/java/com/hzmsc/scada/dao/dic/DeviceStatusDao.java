package com.hzmsc.scada.dao.dic;

import com.hzmsc.scada.entity.dic.DeviceStatus;

public interface DeviceStatusDao {
	
	public DeviceStatus findById(int statusId);
	public int countOfDeviceStatusId(int statusId);

}
