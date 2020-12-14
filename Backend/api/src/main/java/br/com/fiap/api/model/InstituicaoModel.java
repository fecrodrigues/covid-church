package br.com.fiap.api.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import br.com.fiap.api.dto.EnderecoDTO;
import br.com.fiap.api.dto.InstituicaoDTO;
import br.com.fiap.api.dto.ShortInfoPessoaDTO;

@Repository
@Document(collection = "Instituicao")
public class InstituicaoModel {
	
	private ObjectId id;
	private ShortInfoPessoaModel shortInfoPessoa;
	private String nome;
	private EnderecoModel endereco;
	private Integer capacidade;
	
	public InstituicaoModel() {}
	
	public InstituicaoModel(InstituicaoDTO dto) {
		this.id = dto.getId() == null ? null : new ObjectId(dto.getId());
		
		ShortInfoPessoaDTO shortDTO = dto.getShortInfoPessoaDTO();
		this.shortInfoPessoa = shortDTO == null ? null :new ShortInfoPessoaModel(shortDTO);
		
		this.nome = dto.getNome();
		
		EnderecoDTO enderecoDTO = dto.getEnderecoDTO(); 
		this.endereco = enderecoDTO == null ? null :new EnderecoModel(enderecoDTO);
		
		this.capacidade = dto.getCapacidade();
	}

	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}

	
	public ShortInfoPessoaModel getShortInfoPessoa() {
		return shortInfoPessoa;
	}
	public void setShortInfoPessoa(ShortInfoPessoaModel shortInfoPessoa) {
		this.shortInfoPessoa = shortInfoPessoa;
	}

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public EnderecoModel getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}

	
	public Integer getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}
	
}
