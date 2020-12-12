package br.com.fiap.api.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Document(collection = "Pessoa")
public class PessoaModel {
    private String cpf;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private Integer faixaEtaria;

    public PessoaModel(String cpf, String nome, String sobrenome, Date dataNascimento, Integer faixaEtaria) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.faixaEtaria = faixaEtaria;
    }

    public PessoaModel() {

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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(Integer faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }
}
