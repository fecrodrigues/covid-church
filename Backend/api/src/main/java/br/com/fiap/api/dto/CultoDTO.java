package br.com.fiap.api.dto;

import br.com.fiap.api.model.CultoModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Document(collection = "Culto")
public class CultoDTO {

    @Id
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

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }
}
