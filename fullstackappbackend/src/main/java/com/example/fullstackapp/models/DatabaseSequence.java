package com.example.fullstackapp.models;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// indicar a annotation que "dirá" qual é o "papel" que esta classe assumirá
@Document(collection = "database_sequence")
public class DatabaseSequence {
	// utilizar uma nova annotation para auxiliar nos registros que serão
	// armazenados na base de dados
	@Id
	private String id; // sequencia alfanumerica gerada pelo mongoDb - automaticamente
	// e será substituida em tese pela propriedade @Transient SEQUENCE_NAME
	
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
