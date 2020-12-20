package br.com.fiap.api.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import br.com.fiap.api.dto.PessoaDTO;
import br.com.fiap.api.enums.FaixaEtariaEnum;

@Repository
@Document(collection = "Pessoa")
public class PessoaModel {
	
	@Id
    private String cpf;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private FaixaEtariaEnum faixaEtaria;
    private List<ShortInfoCultoModel> listShortInfoCultoModel;

    
    public PessoaModel() {}
    
    public PessoaModel(PessoaDTO dto) {
        this.cpf = dto.getCpf();
        this.nome = dto.getNome();
        this.sobrenome = dto.getSobrenome();
        this.dataNascimento = dto.getDataNascimento();
        this.faixaEtaria = dto.getFaixaEtaria();
        
		List<ShortInfoCultoModel> cultosModel = new ArrayList<>();
		
		if(dto.getListShortInfoCultoDTO() != null && dto.getListShortInfoCultoDTO().isEmpty()) {
			dto.getListShortInfoCultoDTO()
				.forEach(cultoDTO -> cultosModel.add(new ShortInfoCultoModel(cultoDTO)));
			
			this.listShortInfoCultoModel = cultosModel;
		}else {
			this.listShortInfoCultoModel = null;
		}
        
    }

    

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    
    public FaixaEtariaEnum getFaixaEtaria() {
        return faixaEtaria;
    }
    public void setFaixaEtaria(FaixaEtariaEnum faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    
	public List<ShortInfoCultoModel> getListShortInfoCultoModel() {
		return listShortInfoCultoModel;
	}
	public void setListShortInfoCultoModel(List<ShortInfoCultoModel> listShortInfoCultoModel) {
		this.listShortInfoCultoModel = listShortInfoCultoModel;
	}
	
}
