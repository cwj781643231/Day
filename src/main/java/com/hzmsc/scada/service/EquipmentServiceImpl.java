package com.hzmsc.scada.service;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.EquipmentDao;
import com.hzmsc.scada.entity.Employee;
import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.entity.EquipmentBasicInfo;
import com.hzmsc.scada.entity.EquipmentConfigure;
import com.hzmsc.scada.entity.EquipmentStatus;
import com.hzmsc.scada.lib.Exceptions.ModbusException;
import com.hzmsc.scada.lib.modbus.ModbusClientSlave;


@Service
public class EquipmentServiceImpl implements EquipmentService {
	
	private static Logger logger = LoggerFactory.getLogger(EquipmentService.class);

	final int cannotConnectGateway = -1;

	@Autowired
	private EquipmentDao equipmentDao;
/*	
	@Autowired
	private EquipmentStatusService equipmentStatusService;
	@Autowired
	private EquipmentConfigureService equipmentConfigureService;
	@Autowired
	private EquipmentBasicInfoService equipmentBasicInfoService;
	@Autowired
	private EquipmentOperationRateService equipmentOperationRateService;*/

	public int createEquipment(Equipment equipment) {
		System.out.println(equipment.getIpAddress()+"~~~~"+equipment.getUnitIdentifier());
		int i = equipmentDao.countOfEquipmentIpSlave(equipment.getIpAddress(), equipment.getUnitIdentifier());
		System.out.println(i);
		if (i == 0) {
			System.out.println("id:"+equipmentDao.createEquipment(equipment).getEquipmentId());
			return equipmentDao.createEquipment(equipment).getEquipmentId();
		} else {
			// 如果已经存在相同的idAddress和unitIdentifier的设备，则不新增，返回0
			return 0;
		}
	}

	public int updateEquipment(Equipment equipment) {
		int i = equipmentDao.countOfEquipmentIpSlave(equipment.getIpAddress(), equipment.getUnitIdentifier());
		if (i == 0) {
			equipmentDao.updateEquipment(equipment);
			return equipment.getEquipmentId();
		} else if (i == 1) {
			Equipment em = equipmentDao.findByIpSlave(equipment.getIpAddress(), equipment.getUnitIdentifier());
			if (em.getEquipmentId() == equipment.getEquipmentId()) {
				equipmentDao.updateEquipment(equipment);
				return equipment.getEquipmentId();
			} else {
				// 如果更新后存在相同的idAddress和unitIdentifier的其他设备，则不更新，返回0
				return 0;
			}
		} else {
			// i>1?表示有多条重复记录，提示系统逻辑有问题。
			return 11;
		}
	}

	public void deleteEquipment(Equipment equipment) {
		equipmentDao.deleteEquipment(equipment);

	}

	public Equipment findById(int equipmentId) {
		return equipmentDao.findById(equipmentId);
	}

	public Equipment findByIpSlave(String ipAddress, int unitIdentifier) {
		return equipmentDao.findByIpSlave(ipAddress, unitIdentifier);
	}

	public List<Equipment> findAll() {
		return equipmentDao.findAll();
	}

	public int countOfEquipmentId(int equipmentId) {
		return equipmentDao.countOfEquipmentId(equipmentId);
	}

	public int countOfEquipmentIpSlave(String ipAddress, int unitIdentifier) {
		return equipmentDao.countOfEquipmentIpSlave(ipAddress, unitIdentifier);
	}

	public HashMap<String, List<Equipment>> listToHashMapByIp(List<Equipment> equipments) {
		HashMap<String, List<Equipment>> ipEquipmentHashMap = new HashMap<String, List<Equipment>>();
		Iterator<Equipment> ie = equipments.iterator();
		while (ie.hasNext()) {
			Equipment equipment = ie.next();
			if (ipEquipmentHashMap.containsKey(equipment.getIpAddress())) {
				ipEquipmentHashMap.get(equipment.getIpAddress()).add(equipment);
			} else {
				List<Equipment> list = new ArrayList<Equipment>();
				list.add(equipment);
				ipEquipmentHashMap.put(equipment.getIpAddress(), list);
			}
			// System.out.println(equipment);
		}
		return ipEquipmentHashMap;
	}

	public List<Equipment> readEquipmentsFromDevice(ModbusClientSlave modbusClientSlave, List<Equipment> list) {
		List<Equipment> equipments = new ArrayList<Equipment>();
		try {
			
			modbusClientSlave.Connect();
			
			if (modbusClientSlave.isConnected()) {
				logger.debug("ModbusClient connected! Gateway[{}:{}]",modbusClientSlave.getipAddress(),modbusClientSlave.getPort());
			
				for (Equipment e : list) {//循环读取机器状态					
					
					Equipment equipment = readEquipmentFromDevice(modbusClientSlave, e);
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());

					//更新数据库为最新状态
					equipment.setModifiedTime(timestamp);
					this.updateEquipment(equipment);
					
					equipments.add(equipment);

				}
				
				try {
					modbusClientSlave.Disconnect();
				} catch (Exception e) {
					logger.debug("ModbusClient cannot Disconnect! Gateway[{}:{}]",modbusClientSlave.getipAddress(),modbusClientSlave.getPort());
				}
			}

		} catch (UnknownHostException uhe) {
			logger.debug("ModbusClient connectting UnknownHostException! Gateway[{}:{}]",modbusClientSlave.getipAddress(),modbusClientSlave.getPort());
			
		} catch (IOException ioe) {
			logger.debug("ModbusClient connectting IOException! Gateway[{}:{}]",modbusClientSlave.getipAddress(),modbusClientSlave.getPort());
			for (Equipment e : list) {
				
				Equipment equipment = e;
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				equipment.setModifiedTime(timestamp);
				EquipmentStatus equipmentStatus = equipment.getEquipmentStatus();
				equipmentStatus.setEquipmentStatus(cannotConnectGateway);
				equipmentStatus.setModifiedTime(equipment.getModifiedTime());
				equipment.setEquipmentStatus(equipmentStatus);//更新状态为“cannotConnectGateway”
				equipmentDao.updateEquipment(equipment);
				equipments.add(equipment);
				
			}
			// e.printStackTrace();
		} catch (ModbusException me) {
			logger.debug("ModbusClient connectting ModbusException! Gateway[{}:{}]",modbusClientSlave.getipAddress(),modbusClientSlave.getPort());
			me.printStackTrace();
		}
		


		return equipments;
	}

	public Equipment readEquipmentFromDevice(ModbusClientSlave modbusClientSlave, Equipment equipment)
			throws UnknownHostException, SocketException, ModbusException, IOException {
		if (modbusClientSlave.isConnected()) {
			EquipmentBasicInfo equipmentBasicInfo = readEquipmentBasicInfoFromDevice(modbusClientSlave,
					(byte) equipment.getUnitIdentifier(), 40000, 18, equipment.getEquipmentId());
			EquipmentConfigure equipmentConfigure = readEquipmentConfigureFromDevice(modbusClientSlave,
					(byte) equipment.getUnitIdentifier(), 41000, 41, equipment.getEquipmentId());
			EquipmentStatus equipmentStatus = readEquipmentStatusFromDevice(modbusClientSlave,
					(byte) equipment.getUnitIdentifier(), 42000, 17, equipment.getEquipmentId());

			equipment.setEquipmentBasicInfo(equipmentBasicInfo);
			equipment.setEquipmentConfigure(equipmentConfigure);
			equipment.setEquipmentStatus(equipmentStatus);
		}
		return equipment;
	}

	public EquipmentBasicInfo readEquipmentBasicInfoFromDevice(ModbusClientSlave modbusClientSlave, Byte unitIdentifier,
			int startingAddress, int quantity, int equipmentId)
			throws UnknownHostException, SocketException, ModbusException, IOException {
		if (startingAddress == 40000 && quantity == 18 && modbusClientSlave.isConnected()) {
			int[] holdingRegisterValues = modbusClientSlave.ReadHoldingRegisters(startingAddress, quantity,
					unitIdentifier);
			EquipmentBasicInfo equipmentBasicInfo = new EquipmentBasicInfo(holdingRegisterValues);
			equipmentBasicInfo.setEquipmentId(equipmentId);

			return equipmentBasicInfo;
		} else {
			return null;
		}
	}

	public EquipmentConfigure readEquipmentConfigureFromDevice(ModbusClientSlave modbusClientSlave, Byte unitIdentifier,
			int startingAddress, int quantity, int equipmentId)
			throws UnknownHostException, SocketException, ModbusException, IOException {
		if (startingAddress == 41000 && quantity == 41 && modbusClientSlave.isConnected()) {
			int[] holdingRegisterValues = modbusClientSlave.ReadHoldingRegisters(startingAddress, quantity,
					unitIdentifier);
			EquipmentConfigure equipmentConfigure = new EquipmentConfigure(holdingRegisterValues);
			equipmentConfigure.setEquipmentId(equipmentId);

			return equipmentConfigure;
		} else {
			return null;
		}
	}

	public EquipmentStatus readEquipmentStatusFromDevice(ModbusClientSlave modbusClientSlave, Byte unitIdentifier,
			int startingAddress, int quantity, int equipmentId)
			throws UnknownHostException, SocketException, ModbusException, IOException {
		if (startingAddress == 42000 && quantity == 17 && modbusClientSlave.isConnected()) {
			int[] holdingRegisterValues = modbusClientSlave.ReadHoldingRegisters(startingAddress, quantity,
					unitIdentifier);
			EquipmentStatus equipmentStatus = new EquipmentStatus(holdingRegisterValues);
			equipmentStatus.setEquipmentId(equipmentId);

			return equipmentStatus;
		} else {
			return null;
		}
	}

	public void writeEquipmentsToDevice(ModbusClientSlave modbusClientSlave, List<Equipment> list) {
		// TODO Auto-generated method stub

	}

	public void writeEquipmentToDevice(ModbusClientSlave modbusClientSlave, Equipment equipment)
			throws UnknownHostException, SocketException, ModbusException, IOException {
		// TODO Auto-generated method stub

	}

	public void writeEquipmentBasicInfoToDevice(ModbusClientSlave modbusClientSlave, Byte unitIdentifier,
			int startingAddress, int quantity, EquipmentBasicInfo equipmentBasicInfo)
			throws UnknownHostException, SocketException, ModbusException, IOException {
		// TODO Auto-generated method stub

	}

	public void writeEquipmentConfigureToDevice(ModbusClientSlave modbusClientSlave, Byte unitIdentifier,
			int startingAddress, int quantity, EquipmentConfigure equipmentConfigure)
			throws UnknownHostException, SocketException, ModbusException, IOException {
		// TODO Auto-generated method stub

	}

	public void writeEquipmentStatusToDevice(ModbusClientSlave modbusClientSlave, Byte unitIdentifier,
			int startingAddress, int quantity, EquipmentStatus equipmentStatus)
			throws UnknownHostException, SocketException, ModbusException, IOException {
		// TODO Auto-generated method stub

	}
	
	public List<Equipment> findActiveAll(int isActive) {
		return equipmentDao.findActiveAll(isActive);
	}

}
