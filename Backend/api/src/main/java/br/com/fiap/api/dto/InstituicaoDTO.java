package br.com.fiap.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.api.model.EnderecoModel;
import br.com.fiap.api.model.InstituicaoModel;
import br.com.fiap.api.model.ShortInfoPessoaModel;

@JsonInclude(value = Include.NON_NULL)
public class InstituicaoDTO {

	private String id;
	private ShortInfoPessoaDTO shortInfoPessoaDTO;
	private String nome;
	private EnderecoDTO enderecoDTO;
	private Integer capacidade;

	public InstituicaoDTO() {}
	
	public InstituicaoDTO(InstituicaoModel model) {
		this.id = model.getId().toString();
		
		ShortInfoPessoaModel shortModel = model.getShortInfoPessoa();
		this.shortInfoPessoaDTO = shortModel == null ? null :new ShortInfoPessoaDTO(shortModel);
		
		this.nome = model.getNome();
		
		EnderecoModel enderecoModel = model.getEndereco(); 
		this.enderecoDTO = enderecoModel == null ? null :new EnderecoDTO(enderecoModel);
		
		this.capacidade = model.getCapacidade();
	}

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	@JsonProperty("pessoa")
	public ShortInfoPessoaDTO getShortInfoPessoaDTO() {
		return shortInfoPessoaDTO;
	}
	@JsonProperty("pessoa")
	public void setShortInfoPessoaDTO(ShortInfoPessoaDTO shortInfoPessoaDTO) {
		this.shortInfoPessoaDTO = shortInfoPessoaDTO;
	}

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@JsonProperty("endereco")
	public EnderecoDTO getEnderecoDTO() {
		return enderecoDTO;
	}
	@JsonProperty("endereco")
	public void setEnderecoDTO(EnderecoDTO endereco) {
		this.enderecoDTO = endereco;
	}

	
	public Integer getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}
		
}
