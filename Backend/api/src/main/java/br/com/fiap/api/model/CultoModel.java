package br.com.fiap.api.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import br.com.fiap.api.dto.CultoDTO;
import br.com.fiap.api.dto.ShortInfoInstituicaoDTO;
import br.com.fiap.api.enums.PeriodicidadeEnum;

@Repository
@Document(collection = "Culto")
public class CultoModel {
	
	private ObjectId id;
	private ShortInfoInstituicaoModel shortInfoInstituicaoModel;
	private String descricao;
	private LocalDate data;
	private Integer duracao;
	private Integer capacidade;
	private PeriodicidadeEnum periodicidade;
	private List<ShortInfoPessoaModel> listShortInfoPessoaModel;
	
	public CultoModel() {}
	
	public CultoModel(CultoDTO dto) {
		this.id = dto.getId() == null ? null : new ObjectId(dto.getId());
		
		ShortInfoInstituicaoDTO shortDTO = dto.getShortInfoInstituicaoDTO();
		this.shortInfoInstituicaoModel = shortDTO == null ? null :new ShortInfoInstituicaoModel(shortDTO);
		
		this.descricao = dto.getDescricao();
		this.data = dto.getData();
		this.duracao = dto.getDuracao();
		this.capacidade = dto.getCapacidade();
		this.periodicidade = dto.getPeriodicidade();
		
		List<ShortInfoPessoaModel> pessoasModel = new ArrayList<>();
		dto.getListShortInfoPessoaDTO().forEach(pessoaDTO -> pessoasModel.add(new ShortInfoPessoaModel(pessoaDTO)));
		this.listShortInfoPessoaModel = pessoasModel;
	}
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}

	
	public ShortInfoInstituicaoModel getShortInfoInstituicaoModel() {
		return shortInfoInstituicaoModel;
	}
	public void setShortInfoInstituicaoModel(ShortInfoInstituicaoModel shortInfoInstituicao) {
		this.shortInfoInstituicaoModel = shortInfoInstituicao;
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

	
	public List<ShortInfoPessoaModel> getListShortInfoPessoaModel() {
		return listShortInfoPessoaModel;
	}
	public void setListShortInfoPessoaModel(List<ShortInfoPessoaModel> listShortInfoPessoaModel) {
		this.listShortInfoPessoaModel = listShortInfoPessoaModel;
	}
}
