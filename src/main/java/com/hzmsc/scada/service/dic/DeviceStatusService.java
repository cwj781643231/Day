package com.hzmsc.scada.service.dic;

import com.hzmsc.scada.entity.dic.DeviceStatus;

public interface DeviceStatusService {
	public DeviceStatus findById(int statusId);
	public int countOfDeviceStatusId(int statusId);
}
