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

import br.com.fiap.api.dto.CultoDTO;
import br.com.fiap.api.dto.ShortInfoPessoaDTO;
import br.com.fiap.api.model.CultoModel;
import br.com.fiap.api.model.ShortInfoPessoaModel;
import br.com.fiap.api.service.CultoService;

@RestController
@RequestMapping("/cultos/{id}/pessoas")
public class AgendamentoController {

    private final CultoService cultoService;
    
    public AgendamentoController(CultoService cultoService) {
    	this.cultoService = cultoService;
    }
    
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ShortInfoPessoaDTO> findAll(@PathVariable String id) {
    	List<ShortInfoPessoaDTO> pessoas = new ArrayList<>(); 
    	
    	cultoService.findById(id).getListShortInfoPessoaModel()
    		.forEach(shortModel -> pessoas.add(new ShortInfoPessoaDTO(shortModel)));
    	
        return pessoas;
    }
    
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<ShortInfoPessoaDTO> add(@PathVariable String id, @RequestBody List<ShortInfoPessoaDTO> listDto) {
    	
    	List<ShortInfoPessoaModel> pessoas = new ArrayList<>();
    	listDto.forEach(dto -> new ShortInfoPessoaModel(dto));
    	
        return new CultoDTO(
        		);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String id) {
        cultoService.remove(id);
    }
	
}
