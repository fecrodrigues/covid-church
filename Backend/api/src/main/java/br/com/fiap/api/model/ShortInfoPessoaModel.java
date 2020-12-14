package br.com.fiap.api.model;

import org.bson.types.ObjectId;

import br.com.fiap.api.dto.ShortInfoPessoaDTO;


public class ShortInfoPessoaModel {

	private ObjectId cpf;
	private String nome;
	
	public ShortInfoPessoaModel() {}
	
	public ShortInfoPessoaModel(ShortInfoPessoaDTO dto) {
		this.cpf = dto.getCpf() == null ? null : new ObjectId(dto.getCpf());
		this.nome = dto.getNome();
	}

	
	public ObjectId getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}

	
	public void setCpf(ObjectId cpf) {
		this.cpf = cpf;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
