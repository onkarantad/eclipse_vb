package com.app.heroku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.heroku.entity.Employee;
import com.app.heroku.repository.EmployeeRepository;

@Component
public class EmployeeLoader implements CommandLineRunner {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		loadEmpoyee();
	}

	public void loadEmpoyee() {
		if (employeeRepository.count() == 0) {
			employeeRepository.save(Employee.builder().name("aa").build());
			employeeRepository.save(Employee.builder().name("bb").build());
			System.out.println("Sample Employees Loaded");
		}
	}

}
