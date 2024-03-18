package com.example.fullstackapp.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// classe que para setar o ID ( que é gerado de forma automatica) e o nome
@Document(collection = "EmployeeDepartment") 
public class EmployeeDepartment {
	
	@Transient
	public static final String SEQUENCE_NAME = "users_department";

	@Id
	private long id;
	
	@NotBlank
	@Size(max=100) // quantidades de caracteres
	@Indexed(unique=true)
	private String name;
	
	// construtores
	public EmployeeDepartment() {}
	public EmployeeDepartment(long id, @NotBlank @Size(max = 100) String name) {
		super();
		this.id = id;
		this.name = name;
		// CONCLUIR A IMPLEMENTAÇÃO DOS DADOS,GETTERS,SETTERS E TOSTRING
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;

	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
}
