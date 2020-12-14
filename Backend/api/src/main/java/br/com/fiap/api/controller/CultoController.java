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
import br.com.fiap.api.model.CultoModel;
import br.com.fiap.api.service.CultoService;

@RestController
@RequestMapping("/cultos")
public class CultoController {

    private final CultoService service;

    public CultoController(CultoService service) {
    	this.service = service;
	}

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<CultoDTO> findAll() {
    	List<CultoDTO> cultos = new ArrayList<>(); 
    	
    	service.findAll().forEach(model -> cultos.add(new CultoDTO(model)));
    	
        return cultos;
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CultoDTO findById(@PathVariable String id) {
        return new CultoDTO(
        		service.findById(id)
        );
    }
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CultoDTO add(@RequestBody CultoDTO dto) {
    	dto.setId(new ObjectId().toHexString());
    	
        return new CultoDTO(
        		service.add(new CultoModel(dto)
        ));
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CultoDTO update(@PathVariable String id, @RequestBody CultoDTO dto) {
        return new CultoDTO(
        		service.update(id,new CultoModel(dto))
        );
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String id) {
        service.remove(id);
    }
}
