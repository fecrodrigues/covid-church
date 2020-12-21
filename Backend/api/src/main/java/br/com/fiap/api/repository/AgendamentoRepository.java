package br.com.fiap.api.repository;

import br.com.fiap.api.model.AgendamentoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends MongoRepository<AgendamentoModel,String> {
    AgendamentoModel save(AgendamentoModel agendamentoModel);
    List<AgendamentoModel> findAll();
    List<AgendamentoModel> findByIdPessoa(String idPessoa);
    List<AgendamentoModel> findByIdCulto(String idCulto);
    AgendamentoModel findByIdCultoAndIdPessoa(String idCulto, String idPessoa);
}