package br.com.fiap.api.repository;

import br.com.fiap.api.model.CultoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface CultoRepository extends MongoRepository<CultoModel,String> {
    CultoModel save(CultoModel cultoModel);
//    List<CultoModel> findByIdInstituicaoAndData(String idInstituicao);
    CultoModel findByIdInstituicaoAndData(String idInstituicao, Date data);
}
