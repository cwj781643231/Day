package com.hzmsc.scada.service;

import java.util.List;

import com.hzmsc.scada.entity.Employee;

public interface EmployeeService {
	
	public Employee createEmployee(Employee employee);
	
	public void updateEmployee(Employee employee);
	public void deleteEmployee(Employee employee);
	public void safeDeleteEmployee(Employee employee);
	
	
	public int countById(int employeeId);
	public int countByActiveId(int employeeId, int isActive);
	
	public Employee findById(int employeeId);
	public Employee findByActiveId(int employeeId, int isActive);
	
	public List<Employee> findAll();
	public List<Employee> findActiveAll(int isActive);

}
