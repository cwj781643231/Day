package com.hzmsc.scada.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DailySchedule {
	
	private int dailyScheduleId;
	private String dailyScheduleName;
	private Date scheduleDay;
	private int scheduleTypeId;
	private String scheduleTypeName;
	private Time beginTime;
	private Time endTime;
	private int employeeId;
	private String employeeName;
	private int equipmentId;
	private String equipmentName;
	private float production;
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private int createdUser;
	private int modifiedUser;
	
	//一台设备对应一个总产量
    private Object listequipsum;
    //一台设备对应多个总产量
    private Object listsumbyid;
    
    private PageBean pageBean;
	
	public DailySchedule() {
		super();
		// 
	}
	public int getDailyScheduleId() {
		return dailyScheduleId;
	}
	public void setDailyScheduleId(int dailyScheduleId) {
		this.dailyScheduleId = dailyScheduleId;
	}
	public String getDailyScheduleName() {
		return dailyScheduleName;
	}
	public void setDailyScheduleName(String dailyScheduleName) {
		this.dailyScheduleName = dailyScheduleName;
	}
	public Date getScheduleDay() {
		return scheduleDay;
	}
	public void setScheduleDay(Date scheduleDay) {
		this.scheduleDay = scheduleDay;
	}
	public int getScheduleTypeId() {
		return scheduleTypeId;
	}
	public void setScheduleTypeId(int scheduleTypeId) {
		this.scheduleTypeId = scheduleTypeId;
	}
	public String getScheduleTypeName() {
		return scheduleTypeName;
	}
	public void setScheduleTypeName(String scheduleTypeName) {
		this.scheduleTypeName = scheduleTypeName;
	}
	public Time getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Time beginTime) {
		this.beginTime = beginTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public float getProduction() {
		return production;
	}
	public void setProduction(float production) {
		this.production = production;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public int getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(int createdUser) {
		this.createdUser = createdUser;
	}
	public int getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(int modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	@Override
	public String toString() {
		return "DailySchedule [dailyScheduleId=" + dailyScheduleId + ", dailyScheduleName=" + dailyScheduleName
				+ ", scheduleday=" + scheduleDay + ", scheduleTypeId=" + scheduleTypeId + ", scheduleTypeName="
				+ scheduleTypeName + ", beginTime=" + beginTime + ", endTime=" + endTime + ", employeeId=" + employeeId
				+ ", employeeName=" + employeeName + ", equipmentId=" + equipmentId + ", equipmentName=" + equipmentName
				+ ", production=" + production + ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime
				+ ", createdUser=" + createdUser + ", modifiedUser=" + modifiedUser + ", pageBean=" + pageBean + "]";
	}
	
	public Timestamp getBeginTimestamp(){
		Timestamp returnTimestamp = new Timestamp(System.currentTimeMillis());
		DateTime beginDate = new DateTime(returnTimestamp);
		
		String dateString = beginDate.toString("yyyy-MM-dd");;
		String timeString = "00:00:00";
		
		if(this.getScheduleDay() != null){
			DateTime dt = new DateTime(this.getScheduleDay());
			dateString = dt.toString("yyyy-MM-dd");
			if(this.getBeginTime() != null){
				timeString = this.getBeginTime().toString();
			}
			
		}
		
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");    
	    
		//时间解析    
		DateTime dateTime = DateTime.parse(dateString + " " + timeString, format); 
		returnTimestamp.setTime(dateTime.getMillis());
		
		return returnTimestamp;
	}
	
	public Timestamp getEndTimestamp(){
		Timestamp returnTimestamp = new Timestamp(System.currentTimeMillis());
		DateTime retrunDate = new DateTime(returnTimestamp);
		
		String dateString = retrunDate.toString("yyyy-MM-dd");;
		String timeString = "00:00:00";
		
		if(this.getScheduleDay() != null){
			DateTime dt = new DateTime(this.getScheduleDay());
			dateString = dt.toString("yyyy-MM-dd");
			if(this.getEndTime() != null){
				timeString = this.getEndTime().toString();
			}
			
		}
		
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");    
	    
		//时间解析    
		DateTime dateTime = DateTime.parse(dateString + " " + timeString, format); 
		
		//如果结束时间比开始时间小，则结束时间日期+1，第二天
		if(this.getBeginTime().after(this.getEndTime()))
		{
			dateTime = dateTime.plusDays(1);
		}
		returnTimestamp.setTime(dateTime.getMillis());
		
		return returnTimestamp;
	}
	public Object getListequipsum() {
		return listequipsum;
	}
	public void setListequipsum(Object listequipsum) {
		this.listequipsum = listequipsum;
	}
	public Object getListsumbyid() {
		return listsumbyid;
	}
	public void setListsumbyid(Object listsumbyid) {
		this.listsumbyid = listsumbyid;
	}
	

    public DailySchedule(int dailyScheduleId, String equipmentName) {
	        this.dailyScheduleId = dailyScheduleId;
	        this.equipmentName = equipmentName;
    }
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
    
}
