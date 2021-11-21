package com.app.heroku.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.heroku.entity.Employee;
import com.app.heroku.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //The function receives a GET request, processes it and gives back a list of Employee as a response.
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    //The function receives a GET request, processes it, and gives back a list of Employee as a response.
    @GetMapping({"/{id}"})
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }
    //The function receives a POST request, processes it, creates a new Employee and saves it to the database, and returns a resource link to the created employee.           
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee employee1 = employeeService.insert(employee);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("employee", "/api/v1/employee/" + employee1.getId().toString());
        return new ResponseEntity<>(employee1, httpHeaders, HttpStatus.CREATED);
    }
    //The function receives a PUT request, updates the Employee with the specified Id and returns the updated Employee
    @PutMapping({"/{id}"})
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }
    //The function receives a DELETE request, deletes the Employee with the specified Id.
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id) {
    	Employee employee1 = employeeService.getEmployeeById(id);
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(employee1,HttpStatus.NO_CONTENT);
    }
}
