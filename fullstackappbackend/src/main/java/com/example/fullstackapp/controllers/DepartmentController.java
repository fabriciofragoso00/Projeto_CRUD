package com.example.fullstackapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fullstackapp.exceptions.ResourceNotFoundException;
import com.example.fullstackapp.models.EmployeeDepartment;
import com.example.fullstackapp.repository.DepartmentRepository;
import com.example.fullstackapp.service.SequenceGeneratorService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController

@RequestMapping("/api/v1")
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private SequenceGeneratorService sequenceGenaratorService;

	@GetMapping("/departament")
	public List<EmployeeDepartment> getAllEmployeeDepartment() {
		return departmentRepository.findAll();
	}

	@GetMapping("/departament/{id}")
	public ResponseEntity<EmployeeDepartment> getEmployeeDepartment(@PathVariable(value = "id") Long employeeDepartmentId)
			throws ResourceNotFoundException {
		EmployeeDepartment employeeDepartment = departmentRepository.findById(employeeDepartmentId).orElseThrow(
				() -> new ResourceNotFoundException("Derpartamento não encontrado: " + employeeDepartmentId));
		return ResponseEntity.ok().body(employeeDepartment);
	}

	@PostMapping("/departament")
	public EmployeeDepartment createEmployeeDepartment(@Valid @RequestBody EmployeeDepartment employeeDepartment) {
		employeeDepartment.setId(sequenceGenaratorService.generateSequence(EmployeeDepartment.SEQUENCE_NAME));
		return departmentRepository.save(employeeDepartment);
	}

	@PutMapping("/departament/{id}")
	public ResponseEntity<EmployeeDepartment> updateEmployeeDepartment(@PathVariable(value = "id") Long employeeDepartmentId,
			@Valid @RequestBody EmployeeDepartment employeeDetails) throws ResourceNotFoundException {
		EmployeeDepartment employeeDepartment = departmentRepository.findById(employeeDepartmentId).orElseThrow(
				() -> new ResourceNotFoundException("Departamento não encontrado: " + employeeDepartmentId));

		employeeDepartment.setName(employeeDetails.getName());
		final EmployeeDepartment updateEmployee = departmentRepository.save(employeeDepartment);

		return ResponseEntity.ok(updateEmployee);
	}

	@DeleteMapping("/departament/{id}")
	public Map<String, Boolean> deleteEmployeeDepartment(@PathVariable(value = "id") Long employeeDepartmentId)
			throws ResourceNotFoundException {
		EmployeeDepartment employeeDepartment = departmentRepository.findById(employeeDepartmentId).orElseThrow(
				() -> new ResourceNotFoundException("Colaborador não encontrado: " + employeeDepartmentId));
		departmentRepository.delete(employeeDepartment);
		Map<String, Boolean> response = new HashMap<>();
		response.put("excluido", Boolean.TRUE);

		return response;
	}
}
