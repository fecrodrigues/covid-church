package br.com.fiap.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    	
    	if( cultoService.findById(id).getListShortInfoPessoaModel() != null &&  
    		!cultoService.findById(id).getListShortInfoPessoaModel().isEmpty()) {    		
    		
    		cultoService.findById(id).getListShortInfoPessoaModel()
    			.forEach(shortModel -> pessoas.add(new ShortInfoPessoaDTO(shortModel)));

    		return pessoas;
    	}
    	
    	return new ArrayList<>();
    	
    }
    
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<ShortInfoPessoaDTO> add(@PathVariable String id, @RequestBody List<ShortInfoPessoaDTO> listDto) {
    	
    	List<ShortInfoPessoaModel> pessoasModel = new ArrayList<>();
    	listDto.forEach(dto -> pessoasModel.add(new ShortInfoPessoaModel(dto)));
    	
    	List<ShortInfoPessoaModel> response = cultoService.addPessoa(id, pessoasModel);

    	List<ShortInfoPessoaDTO> pessoasDTO = new ArrayList<>();
    	response.forEach(shortDTO -> pessoasDTO.add(new ShortInfoPessoaDTO(shortDTO)));
    	
        return pessoasDTO;
    }
    
    @DeleteMapping("/{idPessoa}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String id, @PathVariable String idPessoa) {
        CultoModel model = cultoService.findById(id);
    
        model.setListShortInfoPessoaModel(
        		model.getListShortInfoPessoaModel().stream()
        			.filter(shortPessoa -> !shortPessoa.getCpf().equals(idPessoa))
        			.collect(Collectors.toList())
        );
        
    	cultoService.add(model);
    }
	
}
