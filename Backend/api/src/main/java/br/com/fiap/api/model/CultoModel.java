package br.com.fiap.api.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Document(collection = "Culto")
public class CultoModel {

    private String id;
    private String idInstituicao;
    private String descricao;
    private Date data;
    private Integer duracao;
    private Integer capacidade;

    public CultoModel(String id, String idInstituicao, String descricao, Date data, Integer duracao, Integer capacidade, Integer vagas) {

        this.id = id;
        this.idInstituicao = idInstituicao;
        this.descricao = descricao;
        this.data = data;
        this.duracao = duracao;
        this.capacidade = capacidade;
    }

    public CultoModel() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(String idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
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

}
