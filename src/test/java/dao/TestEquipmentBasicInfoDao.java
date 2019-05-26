package dao;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hzmsc.scada.dao.EquipmentBasicInfoDao;
import com.hzmsc.scada.entity.EquipmentBasicInfo;

public class TestEquipmentBasicInfoDao {

	public static void main(String[] args) {
		
		ApplicationContext context =
			    new ClassPathXmlApplicationContext("daoContext.xml","applicationContext.xml");
		EquipmentBasicInfoDao equipmentBasicInfoDao = context.getBean(EquipmentBasicInfoDao.class);
		
		EquipmentBasicInfo equipmentBasicInfo = new EquipmentBasicInfo();
		equipmentBasicInfo.setEquipmentId(2);
		equipmentBasicInfo.setModifiedTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
		
		equipmentBasicInfoDao.createEquipmentBasicInfo(equipmentBasicInfo);
		((ClassPathXmlApplicationContext) context).close();
		

	}
}
