package dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hzmsc.scada.dao.EquipmentStatusDao;
import com.hzmsc.scada.entity.EquipmentStatus;

public class TestEquipmentStatusDao {

	public static void main(String[] args) {
		
		ApplicationContext context =
			    new ClassPathXmlApplicationContext("daoContext.xml","applicationContext.xml");
		EquipmentStatusDao equipmentStatusDao = context.getBean(EquipmentStatusDao.class);
		List<EquipmentStatus> list = equipmentStatusDao.findAll();
		for(EquipmentStatus i:list){
			System.out.println(i);
		}
		EquipmentStatus equipmentStatus = new EquipmentStatus();
		equipmentStatus.setEquipmentId(2);
		equipmentStatus.setModifiedTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
		
		equipmentStatusDao.createEquipmentStatus(equipmentStatus);
		((ClassPathXmlApplicationContext) context).close();
		

	}
}
