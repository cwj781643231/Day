package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hzmsc.scada.dao.EquipmentDao;
import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.service.EquipmentService;

public class TestEquipmentDao {
	
		public static void main(String[] args) {
			ApplicationContext context =
				    new ClassPathXmlApplicationContext("daoContext.xml","applicationContext.xml");
			EquipmentDao equipmentDao = context.getBean(EquipmentDao.class);
			
			//User user = userDaoImpl.findById(1L);
			//System.out.println(user);
			
			HashMap<String, List<Equipment>> ipEquipmentHashMap = new HashMap<String, List<Equipment>>();
			List<Equipment> equipmentList = equipmentDao.findAll();
			/*
			Iterator<Equipment> i = equipmentList.iterator();
			while(i.hasNext()){
				Equipment equipment = i.next();
				if(ipEquipmentHashMap.containsKey(equipment.getIpAddress())){
					ipEquipmentHashMap.get(equipment.getIpAddress()).add(equipment);
				}else{
					List<Equipment> list= new ArrayList<Equipment>();
					list.add(equipment);
					ipEquipmentHashMap.put(equipment.getIpAddress(), list);
				}
				System.out.println(equipment);			
			}
			*/
			EquipmentService es = context.getBean(EquipmentService.class);
			
			ipEquipmentHashMap = es.listToHashMapByIp(equipmentList);
			Set<String> ipEquipmentHashMapKeySet = ipEquipmentHashMap.keySet();
			for (String ipAddress : ipEquipmentHashMapKeySet){
				List<Equipment> list = ipEquipmentHashMap.get(ipAddress);
				for (Equipment em : list){
					System.out.println(ipAddress + ":" + em.toString());
				}
			}
			
			/*
			equipment.setModifiedTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
			
			equipment.setEquipmentName("倍捻机3");
			equipment.setEquipmentType("倍捻机");
			equipment.setUnitIdentifier(201);
			equipment.setWorkshop("车间3");
			
			//equipmentDao.updateEquipment(equipment);
			//equipmentDao.createEquipment(equipment);
			System.out.println(equipment);
			
			EquipmentService es = context.getBean(EquipmentService.class);
			int ci = es.createEquipment(equipment);
			System.out.println(ci);
			if(ci > 0){
				equipment.setEquipmentId(ci);
				System.out.println(equipment);
			}
			
			//equipment.setUnitIdentifier(203);
			int ui = es.updateEquipment(equipment);
			System.out.println(ui);
			
			*/
			
			((ClassPathXmlApplicationContext) context).close();
		}
}
