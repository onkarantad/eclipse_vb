package com.app.heroku.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.heroku.entity.Employee;
import com.app.heroku.repository.EmployeeRepository;
import com.app.heroku.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getEmployees() {
		List<Employee> Employees = new ArrayList<>();
        employeeRepository.findAll().forEach(Employees::add);
        return Employees;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).get();
	}

	@Override
	public Employee insert(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void updateEmployee(Long id, Employee employee) {
		Employee employeeFromDb = employeeRepository.findById(id).get();
        System.out.println(employeeFromDb.toString());
        employeeFromDb.setName(employee.getName());	
        System.out.println("Updated : "+employeeFromDb.toString());
        employeeRepository.save(employeeFromDb);
	}

	@Override
	public void deleteEmployee(Long id) {
		Employee employeeFromDb = employeeRepository.findById(id).get();
        System.out.println("Deleted : "+employeeFromDb.toString());
		employeeRepository.deleteById(id);	
	}
	
	
}
