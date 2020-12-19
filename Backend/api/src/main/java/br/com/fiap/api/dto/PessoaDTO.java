package br.com.fiap.api.dto;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import br.com.fiap.api.enums.FaixaEtariaEnum;
import br.com.fiap.api.model.PessoaModel;

@JsonInclude(value = Include.NON_NULL)
@JsonNaming(value = SnakeCaseStrategy.class)
public class PessoaDTO {

	private String cpf;
	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;
	private FaixaEtariaEnum faixaEtaria;
    private List<ShortInfoCultoDTO> listShortInfoCultoDTO;

	public PessoaDTO() {}
	
	public PessoaDTO(PessoaModel model) {
		this.cpf = model.getCpf();
		this.nome = model.getNome();
		this.sobrenome = model.getSobrenome();
		this.dataNascimento = model.getDataNascimento();
		this.faixaEtaria = model.getFaixaEtaria();
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

	
	@JsonProperty("cultos")
	public List<ShortInfoCultoDTO> getListShortInfoCultoDTO() {
		return listShortInfoCultoDTO;
	}
	@JsonProperty("cultos")
	public void setListShortInfoCultoDTO(List<ShortInfoCultoDTO> listShortInfoCultoDTO) {
		this.listShortInfoCultoDTO = listShortInfoCultoDTO;
	}
	
}
