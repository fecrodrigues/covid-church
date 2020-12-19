package br.com.fiap.api.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.api.model.CultoModel;
import br.com.fiap.api.model.PessoaModel;
import br.com.fiap.api.model.ShortInfoPessoaModel;
import br.com.fiap.api.repository.PessoaRepository;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
    	this.repository = repository;
	}

    public List<PessoaModel> findAll() {
        return repository.findAll();
    }
    
    public PessoaModel findById(String id) {
    	
		Optional<PessoaModel> optModel = repository.findById(id);
		
		if(optModel.isPresent()) {
			return optModel.get();
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
    }
    
    public PessoaModel add(PessoaModel model) {
        return repository.save(model);
    }
    
    public List<ShortInfoCultoModel> addCulto(String idPessoa, List<ShortInfoCultoModel> models) {
    	PessoaModel pessoa = findById(idPessoa);
    	
    	if(pessoa.getListShortInfoCultoModel() != null ) {
    		pessoa.getListShortInfoCultoModel().addAll(models);
    	}else {
    		pessoa.setListShortInfoCultoModel(models);
    	}
    	
        return repository.save(pessoa)
        		.getListShortInfoCultoModel();
    }    
    
    public CultoModel update(String id, CultoModel newModel) {
    	CultoModel oldModel = findById(id);
    	
    	if(oldModel == null) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Culto não encontrado");
    	}
    	
    	oldModel.setShortInfoInstituicaoModel(newModel.getShortInfoInstituicaoModel());
    	oldModel.setDescricao(newModel.getDescricao());
    	oldModel.setData(newModel.getData());
    	oldModel.setDuracao(newModel.getDuracao());
    	oldModel.setCapacidade(newModel.getCapacidade());
    	oldModel.setPeriodicidade(newModel.getPeriodicidade());
    	
    	return repository.save(oldModel);
    }
    
    public void remove(String id) {
        repository.deleteById(new ObjectId(id));
    }
    
    public void removePessoa(String idCulto, ShortInfoPessoaModel shortPessoa) {
    	CultoModel culto = findById(idCulto);
    	culto.getListShortInfoPessoaModel()
    		.remove(shortPessoa);
    }
}
