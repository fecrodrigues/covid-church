package br.com.fiap.api.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.api.model.CultoModel;
import br.com.fiap.api.repository.CultoRepository;

@Service
public class CultoService {

    private final CultoRepository repository;

    public CultoService(CultoRepository repository) {
    	this.repository = repository;
	}

    public List<CultoModel> findAll() {
        return repository.findAll();
    }
    
    public CultoModel findById(String id) {
    	
    		Optional<CultoModel> optModel = repository.findById(new ObjectId(id));
    		
    		if(optModel.isPresent()) {
    			return optModel.get();
    		}
    		
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Culto não encontrado");
    }
    
    public CultoModel add(CultoModel model) {
        return repository.save(model);
    }
    
    public CultoModel update(String id, CultoModel newModel) {
    	CultoModel oldModel = findById(id);
    	
    	if(oldModel == null) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Culto não encontrado");
    	}
    	
    	oldModel.setShortInfoInstituicao(newModel.getShortInfoInstituicao());
    	oldModel.setDescricao(newModel.getDescricao());
    	oldModel.setDuracao(newModel.getDuracao());
    	oldModel.setCapacidade(newModel.getCapacidade());
    	oldModel.setPeriodicidade(newModel.getPeriodicidade());
    	
    	return repository.save(oldModel);
    }
    
    public void remove(String id) {
        repository.deleteById(new ObjectId(id));
    }
}
