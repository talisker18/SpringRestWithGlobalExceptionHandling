package com.henz.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.henz.custom_exception.EmployeeNotFoundException;
import com.henz.custom_exception.EmptyInputException;
import com.henz.entity.Employee;
import com.henz.repos.EmployeeRepo;

@Service
public class EmployeeService implements EmployeeServiceInterface{
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public Employee addEmployee(Employee e) {
		
		//never put validation with throwing exception inside a try block!!!
		
		if(e.getName().isEmpty() || e.getName().length() == 0) {
			throw new EmptyInputException("601","Please send proper name, its blank");
		}
		
		Employee savedEmp = null;
		
		try {
			
			savedEmp = this.employeeRepo.save(e); //if IllegalArgumentException or Exception occurs, catch it in next catch blocks
			
			
		}catch (IllegalArgumentException exception) {
			throw new EmptyInputException("602","given employee is null: "+exception.getMessage());
			
		}catch(Exception exception) {
			throw new EmptyInputException("603","Something went wrong in service layer while saving employee: "+exception.getMessage());
		}
		
		return savedEmp;
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		List<Employee> empList = this.employeeRepo.findAll();
		
		return empList; //return list if no exception occurred
		
	}

	@Override
	public Employee getEmployeeById(Long empId) {
		
		Employee emp = this.employeeRepo.findById(empId).get(); //throws NoSuchElementException, caught in MyControllerAdvice
		
		return emp;
	}

	@Override
	public void deleteEmployeeById(Long empId) {
		
		try {
			this.employeeRepo.deleteById(empId);
		}catch (EmptyResultDataAccessException exception) {
			throw new EmployeeNotFoundException("604", "Employee to delete with id: "+empId+" not found!");
		}
	}
	
	

}
