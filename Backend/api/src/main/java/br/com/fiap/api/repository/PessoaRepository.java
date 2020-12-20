package br.com.fiap.api.repository;

import br.com.fiap.api.model.PessoaModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends MongoRepository<PessoaModel,String> {

}
