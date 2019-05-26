package com.hzmsc.scada.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.EmployeeDao;
import com.hzmsc.scada.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;

	public Employee createEmployee(Employee employee) {
		return employeeDao.createEmployee(employee);
	}

	public void updateEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);
	}

	public void deleteEmployee(Employee employee) {
		employeeDao.deleteEmployee(employee);

	}
	
	public void safeDeleteEmployee(Employee employee) {
		employeeDao.safeDeleteEmployee(employee);

	}

	public int countById(int employeeId) {
		return employeeDao.countById(employeeId);
	}

	public int countByActiveId(int employeeId, int isActive) {
		return employeeDao.countByActiveId(employeeId, isActive);
	}

	public Employee findById(int employeeId) {
		return employeeDao.findById(employeeId);
	}

	public Employee findByActiveId(int employeeId, int isActive) {
		return employeeDao.findByActiveId(employeeId, isActive);
	}

	public List<Employee> findAll() {
		return employeeDao.findAll();
	}

	public List<Employee> findActiveAll(int isActive) {
		return employeeDao.findActiveAll(isActive);
	}

}
