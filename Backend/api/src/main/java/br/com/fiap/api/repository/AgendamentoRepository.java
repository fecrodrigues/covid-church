package br.com.fiap.api.repository;

import br.com.fiap.api.model.AgendamentoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends MongoRepository<AgendamentoModel,String> {
    List<AgendamentoModel> findAll();
}