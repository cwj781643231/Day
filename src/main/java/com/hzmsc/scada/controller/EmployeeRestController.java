package com.hzmsc.scada.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzmsc.scada.entity.Employee;
import com.hzmsc.scada.service.EmployeeService;

@RestController
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;
	

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public List<Employee> employeeAll(){
		return employeeService.findActiveAll(1);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.PUT)
	public List<Employee> employeeUpdate(@RequestBody Employee employee){
		//System.out.println("EquipmentRestController:/employee.post"+employee);
		employeeService.updateEmployee(employee);
		return employeeService.findActiveAll(1);
	}
	
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public List<Employee> employeeCreate(@RequestBody Employee employee){
		//System.out.println("EquipmentRestController:/employee.post"+employee);
		employeeService.createEmployee(employee);
		return employeeService.findActiveAll(1);
	}
	
	@RequestMapping(value = "/employeeDelete", method = RequestMethod.POST)
	public List<Employee> employeeDelete(@RequestBody Employee employee){
		//System.out.println("EquipmentRestController:/employee.post"+employee);
		employeeService.safeDeleteEmployee(employee);
		return employeeService.findActiveAll(1);
	}
	

}
