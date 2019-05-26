package com.hzmsc.scada.service.dic;

import com.hzmsc.scada.entity.dic.DeviceType;

public interface DeviceTypeService {
	public DeviceType findById(int typeId);
	public int countOfDeviceTypeId(int typeId);
}
