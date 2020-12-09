package br.com.fiap.api.controller;

import br.com.fiap.api.model.PessoaModel;
import br.com.fiap.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
}
