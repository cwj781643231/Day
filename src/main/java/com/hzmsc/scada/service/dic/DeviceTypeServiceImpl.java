package com.hzmsc.scada.service.dic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.dic.DeviceTypeDao;
import com.hzmsc.scada.entity.dic.DeviceType;
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {

	@Autowired
	private DeviceTypeDao deviceTypeDao;
	
	public DeviceType findById(int typeId) {
		DeviceType deviceType = null;
		if(countOfDeviceTypeId(typeId) == 1){
			deviceType = deviceTypeDao.findById(typeId);
		}
		return deviceType;
	}

	public int countOfDeviceTypeId(int typeId) {
		return deviceTypeDao.countOfDeviceTypeId(typeId);
	}

}
