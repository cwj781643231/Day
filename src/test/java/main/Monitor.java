package main;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.lib.modbus.ModbusClientSlave;
import com.hzmsc.scada.service.EquipmentService;

public class Monitor {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("daoContext.xml", "applicationContext.xml");
		// EquipmentDao equipmentDao = context.getBean(EquipmentDao.class);
		EquipmentService es = context.getBean(EquipmentService.class);

		List<Equipment> equipmentList = es.findAll();

		HashMap<String, List<Equipment>> ipEquipmentHashMap = new HashMap<String, List<Equipment>>();

		ipEquipmentHashMap = es.listToHashMapByIp(equipmentList);

		Set<String> ipEquipmentHashMapKeySet = ipEquipmentHashMap.keySet();
		int repeatTimes = 10;
		while (repeatTimes > 0) {
			for (String ipAddress : ipEquipmentHashMapKeySet) {
				ModbusClientSlave modbusClientSlave = new ModbusClientSlave(ipAddress, 502);
				List<Equipment> list = ipEquipmentHashMap.get(ipAddress);
				//System.out.println(list);
				List<Equipment> el = es.readEquipmentsFromDevice(modbusClientSlave, list);
				System.out.println(el);
				try{
					Thread.sleep(1000);  
				}
				catch (InterruptedException e){  
	                 e.printStackTrace();  
	                }  
				
			}

			repeatTimes--;
		}
		
		((ClassPathXmlApplicationContext) context).close();
	}
}
