package br.com.fiap.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

@Repository
@Document(collection = "Instituicao")
public class InstituicaoModel {
    @Id
    private String id;
    private String idUsuario;
    private String nome;
    private String endereco;
    private Integer capacidade;

    public InstituicaoModel(String id, String idUsuario, String nome, String endereco, Integer capacidade) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.endereco = endereco;
        this.capacidade = capacidade;
    }

    public InstituicaoModel() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

}
