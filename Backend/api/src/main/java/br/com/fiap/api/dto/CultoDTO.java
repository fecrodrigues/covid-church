package br.com.fiap.api.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.api.enums.PeriodicidadeEnum;
import br.com.fiap.api.model.CultoModel;
import br.com.fiap.api.model.ShortInfoInstituicaoModel;

@JsonInclude(value = Include.NON_NULL)
public class CultoDTO {

	private String id;
	private ShortInfoInstituicaoDTO shortInfoInstituicaoDTO;
	private String descricao;
	private LocalDate data;
	private Integer duracao;
	private Integer capacidade;
	private PeriodicidadeEnum periodicidade;
	private List<ShortInfoPessoaDTO> listShortInfoPessoaDTO;

	public CultoDTO() {}
	
	public CultoDTO(CultoModel model) {
		this.id = model.getId().toString();
		
		ShortInfoInstituicaoModel shortModel = model.getShortInfoInstituicaoModel();
		this.shortInfoInstituicaoDTO = shortModel == null ? null :new ShortInfoInstituicaoDTO(shortModel);
		
		this.descricao = model.getDescricao();
		this.data = model.getData();
		this.duracao = model.getDuracao();
		this.capacidade = model.getCapacidade();
		this.periodicidade = model.getPeriodicidade();
		
		List<ShortInfoPessoaDTO> pessoasDTO = new ArrayList<>();
		
		if(model.getListShortInfoPessoaModel() != null && model.getListShortInfoPessoaModel().isEmpty()) {			
			model.getListShortInfoPessoaModel()
				.forEach(pessoaModel -> pessoasDTO.add(new ShortInfoPessoaDTO(pessoaModel)));
			
			this.listShortInfoPessoaDTO = pessoasDTO;
		}else {
			this.listShortInfoPessoaDTO = null;
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("instituicao")
	public ShortInfoInstituicaoDTO getShortInfoInstituicaoDTO() {
		return shortInfoInstituicaoDTO;
	}
	@JsonProperty("instituicao")
	public void setShortInfoInstituicaoDTO(ShortInfoInstituicaoDTO instituicao) {
		this.shortInfoInstituicaoDTO = instituicao;
	}

	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}

	
	public Integer getDuracao() {
		return duracao;
	}
	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	
	public Integer getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	
	public PeriodicidadeEnum getPeriodicidade() {
		return periodicidade;
	}
	public void setPeriodicidade(PeriodicidadeEnum periodicidade) {
		this.periodicidade = periodicidade;
	}

	
	@JsonProperty("pessoas")
	public List<ShortInfoPessoaDTO> getListShortInfoPessoaDTO() {
		return listShortInfoPessoaDTO;
	}
	@JsonProperty("pessoas")
	public void setListShortInfoPessoaDTO(List<ShortInfoPessoaDTO> listShortInfoPessoaDTO) {
		this.listShortInfoPessoaDTO = listShortInfoPessoaDTO;
	}
	
}
