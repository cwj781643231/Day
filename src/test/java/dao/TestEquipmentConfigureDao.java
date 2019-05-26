package dao;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hzmsc.scada.dao.EquipmentConfigureDao;
import com.hzmsc.scada.entity.EquipmentConfigure;

public class TestEquipmentConfigureDao {

	public static void main(String[] args) {
		
		ApplicationContext context =
			    new ClassPathXmlApplicationContext("daoContext.xml","applicationContext.xml");
		EquipmentConfigureDao equipmentConfigureDao = context.getBean(EquipmentConfigureDao.class);
		
		EquipmentConfigure equipmentConfigure = new EquipmentConfigure();
		equipmentConfigure.setEquipmentId(2);
		equipmentConfigure.setModifiedTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
		
		equipmentConfigureDao.createEquipmentConfigure(equipmentConfigure);
		((ClassPathXmlApplicationContext) context).close();
		

	}
}
