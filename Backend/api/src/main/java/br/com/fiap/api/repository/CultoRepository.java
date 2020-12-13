package br.com.fiap.api.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.api.model.CultoModel;

@Repository
public interface CultoRepository extends MongoRepository<CultoModel, ObjectId>{

}
