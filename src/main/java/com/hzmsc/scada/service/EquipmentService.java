package com.hzmsc.scada.service;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.entity.EquipmentBasicInfo;
import com.hzmsc.scada.entity.EquipmentConfigure;
import com.hzmsc.scada.entity.EquipmentStatus;
import com.hzmsc.scada.lib.Exceptions.ModbusException;
import com.hzmsc.scada.lib.modbus.ModbusClientSlave;

public interface EquipmentService {

	// 返回新增设备编号，如果已存在重复设备（ipAddress和unitIdentifier），则返回0
	public int createEquipment(Equipment equipment);

	/**
	 * 返回更新设备编号，如果跟已有设备重名（ipAddress和unitIdentifier相同，但equipmentId不同），则返回0
	 * */
	public int updateEquipment(Equipment equipment);

	public void deleteEquipment(Equipment equipment);

	public Equipment findById(int equipmentId);

	public List<Equipment> findActiveAll(int isActive);
	
	public Equipment findByIpSlave(String ipAddress, int unitIdentifier);

	public int countOfEquipmentId(int equipmentId);

	public int countOfEquipmentIpSlave(String ipAddress, int unitIdentifier);

	public List<Equipment> findAll();

	public HashMap<String, List<Equipment>> listToHashMapByIp(List<Equipment> equipments);

	public List<Equipment> readEquipmentsFromDevice(ModbusClientSlave modbusClientSlave, List<Equipment> list);

	public Equipment readEquipmentFromDevice(ModbusClientSlave modbusClientSlave, Equipment equipment)
			throws UnknownHostException, SocketException, ModbusException, IOException;

	public EquipmentBasicInfo readEquipmentBasicInfoFromDevice(ModbusClientSlave modbusClientSlave, Byte unitIdentifier,
			int startingAddress, int quantity, int equipmentId)
			throws UnknownHostException, SocketException, ModbusException, IOException;

	public EquipmentConfigure readEquipmentConfigureFromDevice(ModbusClientSlave modbusClientSlave, Byte unitIdentifier,
			int startingAddress, int quantity, int equipmentId)
			throws UnknownHostException, SocketException, ModbusException, IOException;

	public EquipmentStatus readEquipmentStatusFromDevice(ModbusClientSlave modbusClientSlave, Byte unitIdentifier,
			int startingAddress, int quantity, int equipmentId)
			throws UnknownHostException, SocketException, ModbusException, IOException;

	public void writeEquipmentsToDevice(ModbusClientSlave modbusClientSlave, List<Equipment> list);

	public void writeEquipmentToDevice(ModbusClientSlave modbusClientSlave, Equipment equipment)
			throws UnknownHostException, SocketException, ModbusException, IOException;

	public void writeEquipmentBasicInfoToDevice(ModbusClientSlave modbusClientSlave, Byte unitIdentifier,
			int startingAddress, int quantity, EquipmentBasicInfo equipmentBasicInfo)
			throws UnknownHostException, SocketException, ModbusException, IOException;

	public void writeEquipmentConfigureToDevice(ModbusClientSlave modbusClientSlave, Byte unitIdentifier,
			int startingAddress, int quantity, EquipmentConfigure equipmentConfigure)
			throws UnknownHostException, SocketException, ModbusException, IOException;

	public void writeEquipmentStatusToDevice(ModbusClientSlave modbusClientSlave, Byte unitIdentifier,
			int startingAddress, int quantity, EquipmentStatus equipmentStatus)
			throws UnknownHostException, SocketException, ModbusException, IOException;

}
