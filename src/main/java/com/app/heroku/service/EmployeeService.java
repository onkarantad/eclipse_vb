package com.app.heroku.service;

import java.util.List;

import com.app.heroku.entity.Employee;

public interface EmployeeService {

	List<Employee> getEmployees();

	Employee getEmployeeById(Long id);

	Employee insert(Employee employee);

	void updateEmployee(Long id, Employee employeetodo);

	void deleteEmployee(Long id);
}
