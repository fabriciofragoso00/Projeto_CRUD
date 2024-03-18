package com.example.fullstackapp.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// esta é a classe que serve ao projeto como o model
@Document(collection = "Employee")
public class Employee {

	// uso de uma nova annotation - que deve facilitar a operação com os dados
	// armazenados na collection
	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";

	// definindo os atributos
	@Id
	private long id;

	// definir as propriedades de nome, email, phone
	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	private String name;
	private String departamento;
	private String dataDeContratacao;
	private String email;
	private String phone;

	public Employee(long id, @NotBlank @Size(max = 100) String name, String departamento, String dataDeContratacao,
			String email, String phone) {

		super();
		this.id = id;
		this.name = name;
		this.departamento = departamento;
		this.dataDeContratacao = dataDeContratacao;
		this.email = email;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", departamento=" + departamento + ", dataDeContratacao="
				+ dataDeContratacao + ", email=" + email + ", phone=" + phone + "]";
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

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDataDeContratacao() {
		return dataDeContratacao;
	}

	public void setDataDeContratacao(String dataDeContratacao) {
		this.dataDeContratacao = dataDeContratacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
