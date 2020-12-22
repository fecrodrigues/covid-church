package br.com.fiap.api.repository;

import br.com.fiap.api.dto.CultoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CultoRepositoryDTO extends MongoRepository<CultoDTO,String> {
    CultoDTO save(CultoDTO cultoDTO);
    CultoDTO findByIdAndIdInstituicao(String idCulto, String idInstituicao);
    List<CultoDTO> findByIdInstituicao(String idInstituicao);
}
