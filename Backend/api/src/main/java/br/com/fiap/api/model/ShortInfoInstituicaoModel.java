package br.com.fiap.api.model;

import org.bson.types.ObjectId;

import br.com.fiap.api.dto.ShortInfoInstituicaoDTO;


public class ShortInfoInstituicaoModel {

	private ObjectId id;
	private String nome;
	
	public ShortInfoInstituicaoModel() {}
	
	public ShortInfoInstituicaoModel(ShortInfoInstituicaoDTO dto) {
		this.id = new ObjectId(dto.getId());
		this.nome = dto.getNome();
	}
	
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
