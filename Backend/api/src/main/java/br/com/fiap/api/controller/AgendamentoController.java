package br.com.fiap.api.controller;

import br.com.fiap.api.model.AgendamentoModel;
import br.com.fiap.api.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgendamentoController {
    @Autowired
    private AgendamentoModel agendamentoModel;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @PostMapping("/agendamento")
    public ResponseEntity<AgendamentoModel> addAgendamento(@RequestBody AgendamentoModel agendamento){
        agendamentoModel = agendamentoRepository.save(agendamento);
        if (agendamentoModel != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoModel);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/agendamentos")
    public List<AgendamentoModel> getAgendamentos(){return agendamentoRepository.findAll();}

    //@GetMapping("/agendamento/{id}")
}
