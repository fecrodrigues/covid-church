package br.com.fiap.api.controller;

import br.com.fiap.api.dto.AgendamentoCountDTO;
import br.com.fiap.api.dto.AgendamentoDTO;
import br.com.fiap.api.dto.CultoDTO;
import br.com.fiap.api.model.AgendamentoModel;
import br.com.fiap.api.model.CultoModel;
import br.com.fiap.api.repository.AgendamentoRepository;
import br.com.fiap.api.repository.AgendamentoRepositoryDTO;
import br.com.fiap.api.repository.CultoRepository;
import br.com.fiap.api.repository.CultoRepositoryDTO;
import br.com.fiap.api.utils.FindAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class AgendamentoController {
    @Autowired
    private AgendamentoModel agendamentoModel;

    @Autowired
    private CultoModel cultoModel;

    @Autowired
    private CultoDTO cultoDTO;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AgendamentoRepositoryDTO agendamentoRepositoryDTO;

    @Autowired
    private CultoRepository cultoRepository;

    @Autowired
    private CultoRepositoryDTO cultoRepositoryDTO;

    @PostMapping("/agendamentos")
    public ResponseEntity<AgendamentoDTO> addAgendamento(@RequestBody AgendamentoModel agendamento){

        List<AgendamentoModel> dbAgendamento = agendamentoRepository.findByIdPessoa(agendamento.getIdPessoa());
        Boolean confere = new FindAgendamento().findAgendamento(agendamento.getIdCulto(), dbAgendamento);
        if (!confere) {
            Optional<CultoDTO> dbCulto = cultoRepositoryDTO.findById(agendamento.getIdCulto());
            System.out.println(dbCulto.get().getCapacidade().toString());
            AgendamentoDTO agendamentoDTO = new AgendamentoDTO(agendamento);
            agendamentoDTO.setDescricao(dbCulto.get().getDescricao());
            agendamentoDTO.setData(dbCulto.get().getData());
            agendamentoDTO = agendamentoRepositoryDTO.save(agendamentoDTO);

            CultoDTO testeCulto = cultoRepositoryDTO.findByIdAndIdInstituicao(agendamento.getIdCulto(), dbCulto.get().getIdInstituicao());
            testeCulto.setVagas(testeCulto.getVagas() -1 );
            cultoRepositoryDTO.save(testeCulto);

            return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/agendamentos")
    public List<AgendamentoModel> getAgendamentos(){return agendamentoRepository.findAll();}

    @GetMapping("/agendamentos/{idCulto}")
    public List<AgendamentoModel> getAgendamentoByIdCulto(@PathVariable String idCulto) {
        return agendamentoRepository.findByIdCulto(idCulto);
    }

    @GetMapping("/agendamentos/usuarios/{idUsuario}")
    public List<AgendamentoDTO> getAgendamentoByIdPessoa(@PathVariable String idUsuario) {
        return agendamentoRepositoryDTO.findByIdPessoa(idUsuario);
    }

    @GetMapping("/agendamentos/{idCulto}/counts")
    public ResponseEntity<AgendamentoCountDTO> getCount(@PathVariable String idCulto) {
        List<AgendamentoModel> dbAgendamentos = agendamentoRepository.findByIdCulto(idCulto);
        Integer count = dbAgendamentos.size();
        System.out.println(count);
        AgendamentoCountDTO countReturn = new AgendamentoCountDTO();
        countReturn.setCount(count);
        return ResponseEntity.status(HttpStatus.OK).body(countReturn);
    }

    @DeleteMapping("/agendamentos/cultos/{idCulto}/usuarios/{idUsuario}")
    public ResponseEntity<Void> delAgendamento(@PathVariable("idCulto") String idCulto, @PathVariable("idUsuario") String idUsuario) {
    AgendamentoModel dbAgendamento = agendamentoRepository.findByIdCultoAndIdPessoa(idCulto, idUsuario);

        if (dbAgendamento != null) {
            agendamentoRepository.deleteById(dbAgendamento.getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
