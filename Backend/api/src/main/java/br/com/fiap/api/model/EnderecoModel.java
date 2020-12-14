package br.com.fiap.api.model;

import br.com.fiap.api.dto.EnderecoDTO;

public class EnderecoModel {
	
	private String rua;
	private String numero;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	
	public EnderecoModel() {}
	
	public EnderecoModel(EnderecoDTO dto) {
		this.rua = dto.getRua();
		this.numero = dto.getNumero();
		this.bairro = dto.getBairro();
		this.cep = dto.getCep();
		this.cidade = dto.getCidade();
		this.estado = dto.getEstado();
	}

	
	public String getRua() {
		return rua;
	}
	public String getNumero() {
		return numero;
	}

	
	public String getBairro() {
		return bairro;
	}
	public String getCep() {
		return cep;
	}

	
	public String getCidade() {
		return cidade;
	}
	public String getEstado() {
		return estado;
	}

	
	public void setRua(String rua) {
		this.rua = rua;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
