package com.hzmsc.scada.dao.dic;

import com.hzmsc.scada.entity.dic.DeviceType;

public interface DeviceTypeDao {

	public DeviceType findById(int typeId);
	public int countOfDeviceTypeId(int typeId);
	
}
