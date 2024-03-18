package com.example.fullstackapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import com.example.fullstackapp.models.DatabaseSequence;

@Service
public class SequenceGeneratorService {

	// definir os recursos que auxiliará com referencia de operação que serão
	// executadas com mongoDB
	private MongoOperations mongoOperations;
	
	// definir um construtor para esta classe
	// para que, dessa forma seja possível praticar a injeção de denpendencia
	// necessaria para as operações
	@Autowired
	public SequenceGeneratorService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
	
	// aqui, contruir um método com o objetivo de estabelecer a forma com a
	// estrutura sequencial se dará
	public long generateSequence(String seqName) {
		// propriedade definida com a classe DatabaseSequence
		DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").
				is(seqName)),
				new Update().inc("seq", 1),
				options().returnNew(true).upsert(true),
				DatabaseSequence.class);
		
		// definir a expressão de retorno do método
		return !Objects.isNull(counter) ? counter.getSeq(): 1;
	}
}
