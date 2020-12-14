package br.com.fiap.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.api.dto.InstituicaoDTO;
import br.com.fiap.api.model.InstituicaoModel;
import br.com.fiap.api.service.InstituicaoService;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoController {

    private final InstituicaoService service;

    public InstituicaoController(InstituicaoService service) {
    	this.service = service;
	}

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<InstituicaoDTO> findAll() {
    	List<InstituicaoDTO> instituicoes = new ArrayList<>(); 
    	
    	service.findAll().forEach(model -> instituicoes.add(new InstituicaoDTO(model)));
    	
        return instituicoes;
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public InstituicaoDTO findById(@PathVariable String id) {
        return new InstituicaoDTO(
        		service.findById(id)
        );
    }
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public InstituicaoDTO add(@RequestBody InstituicaoDTO dto) {
    	dto.setId(new ObjectId().toHexString());
    	
        return new InstituicaoDTO(
        		service.add(new InstituicaoModel(dto)
        ));
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public InstituicaoDTO update(@PathVariable String id, @RequestBody InstituicaoDTO dto) {
        return new InstituicaoDTO(
        		service.update(id,new InstituicaoModel(dto))
        );
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String id) {
        service.remove(id);
    }
}
