package br.com.fiap.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.fiap.api.model.EnderecoModel;

@JsonInclude(value = Include.NON_NULL)
public class EnderecoDTO {

	private String rua;
	private String numero;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;

	public EnderecoDTO() {}
	
	public EnderecoDTO(EnderecoModel model) {
		this.rua = model.getRua();
		this.numero = model.getNumero();
		this.bairro = model.getBairro();
		this.cep = model.getCep();
		this.cidade = model.getCidade();
		this.estado = model.getEstado();
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
