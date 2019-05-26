package com.hzmsc.scada.service.view;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.EquipmentViewDao;
import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.entity.EquipmentStatus;
import com.hzmsc.scada.entity.dic.DeviceStatus;
import com.hzmsc.scada.entity.dic.DeviceType;
import com.hzmsc.scada.entity.dic.ErrorCode;
import com.hzmsc.scada.entity.view.EquipmentView;
import com.hzmsc.scada.service.EquipmentService;
import com.hzmsc.scada.service.EquipmentStatusService;
import com.hzmsc.scada.service.dic.DeviceStatusService;
import com.hzmsc.scada.service.dic.DeviceTypeService;
import com.hzmsc.scada.service.dic.ErrorCodeService;

@Service
public class EquipmentViewServiceImpl implements EquipmentViewService {

	private static Logger logger = LoggerFactory.getLogger(EquipmentViewServiceImpl.class);

	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private DeviceTypeService deviceTypeService;
	@Autowired
	private DeviceStatusService deviceStatusService;
	@Autowired
	private EquipmentStatusService equipmentStatusService;
	@Autowired
	private ErrorCodeService errorCodeService;
	

	@Autowired
	private EquipmentViewDao equipmentViewDao;
	
	public EquipmentView findById(int equipmentId) {

		Equipment equipment = equipmentService.findById(equipmentId);
		DeviceType deviceType = deviceTypeService.findById(equipment.getEquipmentBasicInfo().getDeviceType());
		if (deviceType == null) {
			equipment.setEquipmentType("unknown type");
		} else {
			equipment.setEquipmentType(deviceType.getTypeCN());
		}
		DeviceStatus deviceStatus = deviceStatusService.findById(equipment.getEquipmentStatus().getEquipmentStatus());
		if (deviceStatus == null) {
			equipment.setStatus("unknown status");
		} else {
			equipment.setStatus(deviceStatus.getStatusCN());

		}

		EquipmentView equipmentView = new EquipmentView(equipment);

		return equipmentView;
	}

	public List<EquipmentView> findAll() {
		List<EquipmentView> listEquipmentView = new ArrayList<EquipmentView>();
		List<Equipment> listEquipment = equipmentService.findAll();
		for (Equipment equipment : listEquipment) {
			// System.out.println(equipment);
			//System.out.println("我要传入的："+equipment.getEquipmentBasicInfo().getDeviceType());
			DeviceType deviceType = deviceTypeService.findById(equipment.getEquipmentBasicInfo().getDeviceType());
			//System.out.println("deviceType:"+deviceType);
			if (deviceType == null) {
				equipment.setEquipmentType("unknown type");
			} else {
				//System.out.println("我到这来了");
                // System.out.println("转换前"+equipment.getEquipmentType());			
			//equipment.setEquipmentType(deviceType.getTypeCN());
				//System.out.println("转换后"+equipment.getEquipmentType());
			}
			DeviceStatus deviceStatus = deviceStatusService
					.findById(equipment.getEquipmentStatus().getEquipmentStatus());
			if (deviceStatus == null) {
				equipment.setStatus("unknown status");
			} else {
				equipment.setStatus(deviceStatus.getStatusCN());
			}

			listEquipmentView.add(new EquipmentView(equipment));
		}

		return listEquipmentView;
	}

	public List<EquipmentView> findBetweenTimeEvents(Timestamp beginTime, Timestamp endTime) {
		List<EquipmentView> listEquipmentView = new ArrayList<EquipmentView>();
		List<EquipmentStatus> esList = equipmentStatusService.findBetweenTime(beginTime, endTime);
		// logger.info("esList size:{}",esList.size());
		EquipmentStatus esold = new EquipmentStatus();
		if (esList.size() > 0) {
			esold = esList.get(0);
			Equipment equipmentold = equipmentService.findById(esold.getEquipmentId());
			EquipmentView equipmentViewold = new EquipmentView(equipmentold);
			// 找到错误代码的字典解释
			if (errorCodeService.countOfErrorCodeId(esold.getMalfunction()) == 1) {
				ErrorCode errorCode = errorCodeService.findById(esold.getMalfunction());
				equipmentViewold.setEvent(errorCode.getErrorCodeCN());
			} else {
				equipmentViewold.setEvent("unknow event type!");
			}
			listEquipmentView.add(equipmentViewold);
			//logger.info(equipmentViewold.toString());
		}

		for (EquipmentStatus es : esList) {

			// 如果两次连续事件是同一个事件，则忽略
			if (es.getMalfunction() == esold.getMalfunction()) {
				//logger.info("es.getMalfunction:{}", es.getMalfunction());
				//logger.info("esold.getMalfunction:{}", esold.getMalfunction());
			} else {
				Equipment equipment = equipmentService.findById(es.getEquipmentId());
				EquipmentView equipmentView = new EquipmentView(equipment);
				// 找到错误代码的字典解释
				if (errorCodeService.countOfErrorCodeId(es.getMalfunction()) == 1) {
					ErrorCode errorCode = errorCodeService.findById(es.getMalfunction());
					equipmentView.setEvent(errorCode.getErrorCodeCN());
				} else {
					equipmentView.setEvent("unknow event type!");
				}
				listEquipmentView.add(equipmentView);
				logger.debug(equipmentView.toString());
			}
			esold = es;
		}
		return listEquipmentView;
	}

	//设备增删改
	
	public int createEquipmentView(EquipmentView equipmentview) {
		
		int i = equipmentService.countOfEquipmentIpSlave(equipmentview.getIpAddress(), equipmentview.getUnitIdentifier());
		System.out.println(i);
		if (i == 0) {
			//System.out.println("id:"+equipmentViewDao.createEquipmentView(equipmentview).getEquipmentId());
			System.out.println("+++++++++++++++++++++++++++++++++++++++");
			System.out.println("equipmentview ："+equipmentview);
			return equipmentViewDao.createEquipmentView(equipmentview).getEquipmentId();
		} else {
			// 如果已经存在相同的idAddress和unitIdentifier的设备，则不新增，返回0
			return 0;
		}
	}

	public int updateEquipmentView(EquipmentView equipmentview) {
		
		int i = equipmentService.countOfEquipmentIpSlave(equipmentview.getIpAddress(), equipmentview.getUnitIdentifier());
		if (i == 0) {
			equipmentViewDao.updateEquipmentView(equipmentview);
			return equipmentview.getEquipmentId();
		} else if (i == 1) {
			Equipment em = equipmentService.findByIpSlave(equipmentview.getIpAddress(), equipmentview.getUnitIdentifier());
			if (em.getEquipmentId() == equipmentview.getEquipmentId()) {
				equipmentViewDao.updateEquipmentView(equipmentview);
				return equipmentview.getEquipmentId();
			} else {
				// 如果更新后存在相同的idAddress和unitIdentifier的其他设备，则不更新，返回0
				return 0;
			}
		} else {
			// i>1?表示有多条重复记录，提示系统逻辑有问题。
			return -1;
		}
	}

	public void deleteEquipmentView(EquipmentView equipmentview) {
	
		equipmentViewDao.deleteEquipmentView(equipmentview);
	}

}
