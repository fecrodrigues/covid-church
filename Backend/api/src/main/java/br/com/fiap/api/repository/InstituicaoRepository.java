package br.com.fiap.api.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.api.model.InstituicaoModel;

@Repository
public interface InstituicaoRepository extends MongoRepository<InstituicaoModel, ObjectId>{

}
