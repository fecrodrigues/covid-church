package br.com.fiap.api.dto;

import br.com.fiap.api.model.CultoModel;

import java.util.Date;

public class CultoDTO {

    private String id;
    private String idInstituicao;
    private String descricao;
    private Date data;
    private Integer duracao;
    private Integer capacidade;
    private Integer vagas;

    public CultoDTO() {}

    public CultoDTO(CultoModel model) {
        this.id = model.getId();
        this.idInstituicao = model.getIdInstituicao();
        this.descricao = model.getDescricao();
        this.data = model.getData();
        this.duracao = model.getDuracao();
        this.capacidade = model.getCapacidade();
        this.vagas = vagas;
    }



}
