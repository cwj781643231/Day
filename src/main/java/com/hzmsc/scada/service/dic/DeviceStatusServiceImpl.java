package com.hzmsc.scada.service.dic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.dic.DeviceStatusDao;
import com.hzmsc.scada.entity.dic.DeviceStatus;

@Service
public class DeviceStatusServiceImpl implements DeviceStatusService {
	
	@Autowired
	private DeviceStatusDao deviceStatusDao;

	public DeviceStatus findById(int statusId) {
		DeviceStatus deviceStatus = null;
		if(countOfDeviceStatusId(statusId)==1){
			deviceStatus = deviceStatusDao.findById(statusId);
		}
		return deviceStatus;
	}

	public int countOfDeviceStatusId(int statusId) {
		return deviceStatusDao.countOfDeviceStatusId(statusId);
	}

}
