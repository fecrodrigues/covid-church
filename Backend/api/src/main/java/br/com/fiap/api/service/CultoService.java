package br.com.fiap.api.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

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
        return repository.findById(new ObjectId(id)).get();
    }
    
    public CultoModel add(CultoModel model) {
        return repository.save(model);
    }
    
    public CultoModel update(CultoModel newModel) {
        return repository.save(newModel);
    }
    
    public void remove(String id) {
        repository.deleteById(new ObjectId(id));
    }
}
