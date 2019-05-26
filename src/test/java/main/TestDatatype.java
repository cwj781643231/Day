package main;

import java.sql.Time;
import java.sql.Timestamp;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.hzmsc.scada.entity.DailySchedule;

public class TestDatatype {
	public static void main(String[] args) {
		
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		DateTime dt = new DateTime(timestamp);
		
		Time time1 = Time.valueOf("8:00:00");
		Time time2 = Time.valueOf("23:00:00");
		System.out.println(dt.toString("yyyy-MM-dd"));
		System.out.println(time1.before(time2));
		System.out.println(time2.toString());
		
		
		DailySchedule dailySchedule = new DailySchedule();
		System.out.println(dailySchedule.getScheduleDay());
		System.out.println(dailySchedule.getBeginTime());
		System.out.println(dailySchedule.getEndTime());
		
		
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");    
	    
		//时间解析    
		DateTime dateTime = DateTime.parse("2012-12-21 23:22:45", format); 
		
		System.out.println(dateTime);
		
		/*
		
		System.out.println(dt.plusSeconds(1).isAfterNow());
		System.out.println(dt.plusSeconds(1).isBeforeNow());
		System.out.println(dt.minusSeconds(1).isAfterNow());
		System.out.println(dt.minusSeconds(1).isBeforeNow());
		*/
//		System.out.println(dateTime.getYear());
//		System.out.println(dateTime.getMonthOfYear());
//		System.out.println(dateTime.getDayOfMonth());
//		System.out.println(dateTime.toLocalDate());
//		DateTime dateTime2 = new DateTime(dateTime.getYear(),
//				dateTime.getMonthOfYear(),
//				dateTime.getDayOfMonth(),
//				0,0,0);
//		System.out.println(timestamp);
//		timestamp.setTime(dateTime2.getMillis());
//		System.out.println(timestamp);
//		
//		System.out.println(dateTime.toLocalDate());
//		
		/*System.out.println(dateTime.getMinuteOfHour());
		System.out.println(dateTime.getSecondOfMinute()%10);*/
		
		
	}
}
