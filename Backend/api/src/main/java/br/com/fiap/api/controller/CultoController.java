package br.com.fiap.api.controller;

import br.com.fiap.api.model.PessoaModel;
import br.com.fiap.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/cultos")
public class CultoController {

    private final CultoService service;

    public CultoController(CultoService service) {
    	this.service = service;
	}

    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public CultoModel addCulto(@RequestBody CultoDTO dto) {
        return service.addCulto(dto);
    }
}
