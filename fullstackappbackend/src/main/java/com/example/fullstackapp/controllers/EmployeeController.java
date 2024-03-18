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
import com.example.fullstackapp.models.Employee;
import com.example.fullstackapp.repository.EmployeeRepository;
import com.example.fullstackapp.service.SequenceGeneratorService;

// aqui, é necessario indicar as annotations importantes para este controller
// 1ª dizer que esta aplicação é um app cross-domain
@CrossOrigin(origins = "http://localhost:4200")

// 2ª dizer que esta classe será, unicamente, um @RestController com a responsabilidade
// de "cuidar" das requisições http
@RestController

// 3ª dizer que é necessario fazer o mapeamento das requisições http
@RequestMapping("/api/v1")
public class EmployeeController {

	// aplicar as injeções de independencia
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private SequenceGeneratorService sequenceGenaratorService;

	/*
	 * ========================================================================
	 * 				CONSTRUÇÃO DAS REQUISIÇÕES HTTP
	 * ========================================================================
	 */

	// 1ª requisição http estabelecer um método para recuperar todos os registros da
	// base
	@GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		// estabelecer uma expressão de retorno
		return employeeRepository.findAll();
	}

	// 2ª requisição http estabelecer um método para recuperar um único registro da
	// base
	// devidamente identificada
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable(value = "id") Long employeeId)
			// será implementada uma referencia a uma estrutura de exceção
			// para, o caso ocorra, seja possive verificar qual a natureza do problema
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Colaborador não encontrado: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	// 3ª requisição http estabelecer um método para inserir/registrar um conjunto
	// de dados na base
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		employee.setId(sequenceGenaratorService.generateSequence(Employee.SEQUENCE_NAME));
		return employeeRepository.save(employee);
	}

	// 4ª requisição estabelecer o método para atualizar/alterar um registro da base
	// para que esteja devidamente identificado
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Colaborador não encontrado: " + employeeId));

		// manipular valores a partir das propriedades definidas no model
		employee.setName(employeeDetails.getName());
		employee.setDepartamento(employeeDetails.getDepartamento());
		employee.setDataDeContratacao(employeeDetails.getDataDeContratacao());
		employee.setEmail(employeeDetails.getEmail());
		employee.setPhone(employeeDetails.getPhone());
		final Employee updateEmployee = employeeRepository.save(employee);

		// estabelecer a expressão de retorno
		return ResponseEntity.ok(updateEmployee);
	}

	// 5ª requisição http estabelecer um metodo para excluir um registro da base
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Colaborador não encontrado: " + employeeId));

		// contruir o procedimento de exclusão do registro
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("excluido", Boolean.TRUE);

		return response;
	}
}
