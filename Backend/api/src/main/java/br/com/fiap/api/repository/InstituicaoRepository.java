package br.com.fiap.api.repository;

import br.com.fiap.api.model.InstituicaoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstituicaoRepository extends MongoRepository<InstituicaoModel,String> {
    InstituicaoModel save(InstituicaoModel instituicaoModel);
    List<InstituicaoModel> findAll();
    List<InstituicaoModel> findByIdUsuario(String idUsuario);
    void deleteById(String id);
    List<InstituicaoModel> deleteByIdUsuario(String idUsuario);
}
