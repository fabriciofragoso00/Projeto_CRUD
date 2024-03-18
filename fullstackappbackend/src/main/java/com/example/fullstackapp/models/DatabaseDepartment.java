package com.example.fullstackapp.models;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_department")
public class DatabaseDepartment {

	@Id
	private String id; 
		
	private long seq;
	
	// getters e setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
}
