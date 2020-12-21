package br.com.fiap.api.controller;

import br.com.fiap.api.dto.InstituicaoPartialUpdateDTO;
import br.com.fiap.api.model.InstituicaoModel;
import br.com.fiap.api.model.PessoaModel;
import br.com.fiap.api.repository.InstituicaoRepository;
import br.com.fiap.api.repository.PessoaRepository;
import br.com.fiap.api.utils.JsonNullableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class InstituicaoController {

    @Autowired
    private InstituicaoModel instituicaoModel;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping("/instituicao")
    public ResponseEntity<InstituicaoModel> addInstituicao(@RequestBody InstituicaoModel instituicao) {
        PessoaModel pessoa = pessoaRepository.findByCpf(instituicao.getIdUsuario());
        if (pessoa != null && instituicaoModel != null) {
            instituicaoModel = instituicaoRepository.save(instituicao);
            return ResponseEntity.status(HttpStatus.CREATED).body(instituicaoModel);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/instituicoes")
    public List<InstituicaoModel> getInstituicoes(){ return instituicaoRepository.findAll(); }

    @GetMapping("/instituicoes/usuario/{idUsuario}")
    public List<InstituicaoModel> getInstituicaoByIdUsuario(@PathVariable String idUsuario) {
        return instituicaoRepository.findByIdUsuario(idUsuario);
    }

    @PatchMapping("/instituicao/{id}")
    public ResponseEntity<Void> pathInstituicao(@PathVariable String id, @RequestBody InstituicaoPartialUpdateDTO instituicaoPartialUpdateDTO) {
        Optional<InstituicaoModel> dbInstituicaoModel = instituicaoRepository.findById(id);
        if (!dbInstituicaoModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            InstituicaoModel instituicaoModelToEdit = dbInstituicaoModel.get();
            JsonNullableUtils.changeIfPresent(instituicaoPartialUpdateDTO.getIdUsuario(), instituicaoModelToEdit::setIdUsuario);
            JsonNullableUtils.changeIfPresent(instituicaoPartialUpdateDTO.getNome(), instituicaoModelToEdit::setNome);
            JsonNullableUtils.changeIfPresent(instituicaoPartialUpdateDTO.getEndereco(), instituicaoModelToEdit::setEndereco);
            JsonNullableUtils.changeIfPresent(instituicaoPartialUpdateDTO.getCapacidade(), instituicaoModelToEdit::setCapacidade);

            instituicaoRepository.save(instituicaoModelToEdit);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @DeleteMapping("/instituicao/{id}")
    public ResponseEntity<Void> delInstituicao(@PathVariable String id){
        Optional<InstituicaoModel> instituicao = instituicaoRepository.findById(id);
        if (instituicao.isPresent()) {
            instituicaoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/instituicoes/usuario/{idUsuario}")
    public ResponseEntity<Void> delInstituicoes(@PathVariable String idUsuario){
        PessoaModel pessoa = pessoaRepository.findByCpf(idUsuario);
        if (pessoa != null) {
            instituicaoRepository.deleteByIdUsuario(idUsuario);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
