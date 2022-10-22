package com.henz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.henz.entity.Employee;
import com.henz.service.EmployeeServiceInterface;

/*
 * do exception handling on all controller methods!
 * 
 * */

@RestController
@RequestMapping("/code")
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceInterface employeeServiceInterface;
	
	@PostMapping("/save")
	public ResponseEntity<?> addEmployee(@RequestBody Employee e){
		Employee savedEmp = employeeServiceInterface.addEmployee(e);
		return new ResponseEntity<Employee>(savedEmp, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		List<Employee> eList = employeeServiceInterface.getAllEmployees();
		return new ResponseEntity<List<Employee>>(eList, HttpStatus.OK);
	}
	
	@GetMapping("/emp/{empid}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("empid") Long empId){
		Employee emp = this.employeeServiceInterface.getEmployeeById(empId);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/emp/{empid}")
	public ResponseEntity<Void> deleteEmployeById(@PathVariable("empid") Long empId){
		this.employeeServiceInterface.deleteEmployeeById(empId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee e){
		Employee savedEmp = employeeServiceInterface.addEmployee(e);
		return new ResponseEntity<>(savedEmp, HttpStatus.CREATED);
	}
}
