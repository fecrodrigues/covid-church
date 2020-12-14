package br.com.fiap.api.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import br.com.fiap.api.model.InstituicaoModel;
import br.com.fiap.api.repository.InstituicaoRepository;

@Service
public class InstituicaoService {

    private final InstituicaoRepository repository;

    public InstituicaoService(InstituicaoRepository repository) {
    	this.repository = repository;
	}

    public List<InstituicaoModel> findAll() {
        return repository.findAll();
    }
    
    public InstituicaoModel findById(String id) {
    	
    		Optional<InstituicaoModel> optModel = repository.findById(new ObjectId(id));
    		
    		if(optModel.isPresent()) {
    			return optModel.get();
    		}
    		
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Culto não encontrado");
    }
    
    public InstituicaoModel add(InstituicaoModel model) {
        return repository.save(model);
    }
    
    public InstituicaoModel update(String id, InstituicaoModel newModel) {
    	InstituicaoModel oldModel = findById(id);
    	
    	if(oldModel == null) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Culto não encontrado");
    	}
    	
    	oldModel.setNome(newModel.getNome());
    	oldModel.setShortInfoPessoa(newModel.getShortInfoPessoa());
    	oldModel.setEndereco(newModel.getEndereco());
    	oldModel.setCapacidade(newModel.getCapacidade());
    	
    	return repository.save(oldModel);
    }
    
    public void remove(String id) {
        repository.deleteById(new ObjectId(id));
    }
}
