package br.com.fiap.api.controller;

import br.com.fiap.api.dto.PessoaPartialUpdateDTO;
import br.com.fiap.api.model.PessoaModel;
import br.com.fiap.api.repository.PessoaRepository;
import br.com.fiap.api.utils.JsonNullableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PessoaController {

    @Autowired
    private PessoaModel pessoaModel;

    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @PostMapping("/pessoa")
    public ResponseEntity<PessoaModel> addPessoa(@RequestBody PessoaModel pessoa) {
        PessoaModel dbPessoa = pessoaRepository.findByCpf(pessoa.getCpf());
        if (dbPessoa == null) {
        	pessoa.setPassword(passwordEncoder.encode(pessoa.getPassword()));
            pessoaModel = pessoaRepository.save(pessoa);
            pessoaModel.setPassword(null);
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoaModel);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
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

    @PatchMapping("/pessoa/{cpf}")
    public ResponseEntity<Void> pathPessoa(@PathVariable String cpf, @RequestBody PessoaPartialUpdateDTO pessoaPartialUpdateDTO) {
        Optional<PessoaModel> dbPessoaModel = pessoaRepository.findById(cpf);
        if (!dbPessoaModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            PessoaModel pessoaModelToEdit = dbPessoaModel.get();
            JsonNullableUtils.changeIfPresent(pessoaPartialUpdateDTO.getNome(), pessoaModelToEdit::setNome);
            JsonNullableUtils.changeIfPresent(pessoaPartialUpdateDTO.getSobrenome(), pessoaModelToEdit::setSobrenome);
            JsonNullableUtils.changeIfPresent(pessoaPartialUpdateDTO.getDataNascimento(), pessoaModelToEdit::setDataNascimento);
            JsonNullableUtils.changeIfPresent(pessoaPartialUpdateDTO.getIdade(), pessoaModelToEdit::setIdade);
            JsonNullableUtils.changeIfPresent(pessoaPartialUpdateDTO.getUserName(), pessoaModelToEdit::setUserName);
            JsonNullableUtils.changeIfPresent(pessoaPartialUpdateDTO.getPassword(), pessoaModelToEdit::setPassword);

            pessoaRepository.save(pessoaModelToEdit);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }

    }

    @DeleteMapping("/pessoa/{cpf}")
    public ResponseEntity<Void> delPessoa(@PathVariable String cpf) {
        PessoaModel pessoa = pessoaRepository.findByCpf(cpf);
        if (pessoa != null) {
            pessoaRepository.deleteByCpf(cpf);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
