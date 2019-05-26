package com.hzmsc.scada.service.dic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.dic.ScheduleTypeDao;
import com.hzmsc.scada.entity.dic.ScheduleType;

@Service
public class ScheduleTypeServiceImpl implements ScheduleTypeService {
	
	@Autowired
	private ScheduleTypeDao scheduleTypeDao;

	public ScheduleType findById(int scheduleTypeId) {
		return scheduleTypeDao.findById(scheduleTypeId);
	}

	public int countOfScheduleId(int scheduleTypeId) {
		return scheduleTypeDao.countOfScheduleId(scheduleTypeId);
	}

	public List<ScheduleType> findAll() {
		return scheduleTypeDao.findAll();
	}
	
	public List<ScheduleType> findByScheduleTypeCode(String scheduleTypeCode) {
		return scheduleTypeDao.findByScheduleTypeCode(scheduleTypeCode);
	}

	public ScheduleType createScheduleType(ScheduleType scheduleType) {
		
		return scheduleTypeDao.createScheduleType(scheduleType);
	}

	public void updateScheduleType(ScheduleType scheduleType) {
		scheduleTypeDao.updateScheduleType(scheduleType);

	}

	public void deleteScheduleType(ScheduleType scheduleType) {
		scheduleTypeDao.deleteScheduleType(scheduleType);

	}

	public List<ScheduleType> findByScheduleTypestate() {
		
		return scheduleTypeDao.findByScheduleTypestate();
	}

	public int findByScheduleTypeTime(Date date) {
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		  String strDate = sdf.format(date); 
		  
		  int s = 0;
		  
		    // 截取当前时间时分秒  
		    int strDateH = Integer.parseInt(strDate.substring(11, 13));  
		    int strDateM = Integer.parseInt(strDate.substring(14, 16));  
		    int strDateS = Integer.parseInt(strDate.substring(17, 19));  
		   List<ScheduleType> listSchedule= findByScheduleTypestate();
		   for (Iterator iterator = listSchedule.iterator(); iterator.hasNext();) {
			ScheduleType scheduleType = (ScheduleType) iterator.next();
			
			// 截取开始时间时分秒  
		    int strDateBeginH = Integer.parseInt(scheduleType.getBeginTime().toString().substring(0, 2));  
		    int strDateBeginM = Integer.parseInt(scheduleType.getBeginTime().toString().substring(3, 5));  
		    int strDateBeginS = Integer.parseInt(scheduleType.getBeginTime().toString().substring(6, 8));  
		    // 截取结束时间时分秒  
		    int strDateEndH = Integer.parseInt(scheduleType.getEndTime().toString().substring(0, 2));  
		    int strDateEndM = Integer.parseInt(scheduleType.getEndTime().toString().substring(3, 5));  
		    int strDateEndS = Integer.parseInt(scheduleType.getEndTime().toString().substring(6, 8));  
			
		    if(strDateEndH == 00 ){
		    	
		    	strDateEndH = 24;
		    }
		    
		    if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {  
		        // 当前时间小时数在开始时间和结束时间小时数之间  
		        if (strDateH > strDateBeginH && strDateH < strDateEndH) {  
		        	return scheduleType.getScheduleTypeId();
		            // 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间  
		        } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM  
		                && strDateM <= strDateEndM) {  
		        	return scheduleType.getScheduleTypeId();
		            // 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间  
		        } else if (strDateH == strDateBeginH && strDateM == strDateBeginM  
		                && strDateS >= strDateBeginS && strDateS <= strDateEndS) {  
		        	return scheduleType.getScheduleTypeId();
		        }  
		        // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数  
		        else if (strDateH >= strDateBeginH && strDateH == strDateEndH  
		                && strDateM <= strDateEndM) {  
		        	return scheduleType.getScheduleTypeId();
		            // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数  
		        } else if (strDateH >= strDateBeginH && strDateH == strDateEndH  
		                && strDateM == strDateEndM && strDateS <= strDateEndS) {  
		        	return scheduleType.getScheduleTypeId();
		        } else {  
		        	s = 0;
		        }  
		    } else {  
		    	s = 0;
		    }  
		}
		return s;
	}
}
