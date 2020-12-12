package br.com.fiap.api.controller;

import br.com.fiap.api.model.PessoaModel;
import br.com.fiap.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class PessoaController {

    @Autowired
    private PessoaModel pessoaModel;

    @Autowired
    private PessoaRepository pessoaRepository;

    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @PostMapping("/pessoa")
    public ResponseEntity<PessoaModel> addPessoa(@RequestBody PessoaModel pessoa) {
        pessoaModel = pessoaRepository.save(pessoa);
        if (pessoaModel != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoaModel);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/pessoas")
    public List<PessoaModel> getPessoas(){ return pessoaRepository.findAll(); }

    @GetMapping("/pessoa/{cpf}")
    public ResponseEntity<PessoaModel> getByCpf(@PathVariable String cpf) {
        pessoaModel = (PessoaModel) pessoaRepository.findByCpf(cpf);
        if (pessoaModel == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(pessoaModel);
    }
/*    @PutMapping("/pessoa")
    public PessoaModel putPessoa(@RequestBody PessoaModel pessoaModel) {return pessoaRepository.updateByCpf(pessoaModel.getCpf());}*/
}
