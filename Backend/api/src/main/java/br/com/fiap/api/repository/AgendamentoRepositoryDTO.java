package br.com.fiap.api.repository;

import br.com.fiap.api.dto.AgendamentoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AgendamentoRepositoryDTO extends MongoRepository<AgendamentoDTO,String> {
    AgendamentoDTO save(AgendamentoDTO agendamentoDTO);
    List<AgendamentoDTO> findByIdPessoa(String idUsuario);
}
