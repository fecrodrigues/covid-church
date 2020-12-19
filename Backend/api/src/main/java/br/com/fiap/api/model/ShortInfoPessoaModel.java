package br.com.fiap.api.model;

import br.com.fiap.api.dto.ShortInfoPessoaDTO;

public class ShortInfoPessoaModel {

	private String cpf;
	private String nome;
	
	public ShortInfoPessoaModel() {}
	
	public ShortInfoPessoaModel(ShortInfoPessoaDTO dto) {
		this.cpf = dto.getCpf();
		this.nome = dto.getNome();
	}

	
	public String getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}

	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
