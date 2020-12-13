package br.com.fiap.api.dto;

import br.com.fiap.api.model.ShortInfoInstituicaoModel;

public class ShortInfoInstituicaoDTO {
	
	private String id;
	private String nome;
	
	public ShortInfoInstituicaoDTO() {}
	
	public ShortInfoInstituicaoDTO(ShortInfoInstituicaoModel model) {
		this.id = model.getId().toString();
		this.nome = model.getNome();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
