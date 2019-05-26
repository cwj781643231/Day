package com.hzmsc.scada.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Employee {
	
	private int employeeId;
	private String employeeCode;
	private String name;
	private String gender;
	
	private Date birthday;
	private Timestamp regtime;
	
	private String address;
	private String phone;
	private String email;
	private String idcard;
	
	private int position;
	private int workshop;
	
	private String username;
	private String password;
	
	private int isActive;
	private Timestamp disableTime;
	private Timestamp reactivateTime;
	
	public Employee() {
		super();
		this.isActive = 1;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Timestamp getRegtime() {
		return regtime;
	}

	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getWorkshop() {
		return workshop;
	}

	public void setWorkshop(int workshop) {
		this.workshop = workshop;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Timestamp getDisableTime() {
		return disableTime;
	}

	public void setDisableTime(Timestamp disableTime) {
		this.disableTime = disableTime;
	}

	public Timestamp getReactivateTime() {
		return reactivateTime;
	}

	public void setReactivateTime(Timestamp reactivateTime) {
		this.reactivateTime = reactivateTime;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeCode=" + employeeCode + ", name=" + name + ", gender="
				+ gender + ", birthday=" + birthday + ", regtime=" + regtime + ", address=" + address + ", phone="
				+ phone + ", email=" + email + ", idcard=" + idcard + ", position=" + position + ", workshop="
				+ workshop + ", username=" + username + ", password=" + password + ", isActived=" + isActive
				+ ", disableTime=" + disableTime + ", reactivateTime=" + reactivateTime + "]";
	}
	

}
