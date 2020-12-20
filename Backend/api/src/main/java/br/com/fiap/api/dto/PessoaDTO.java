package br.com.fiap.api.dto;

import br.com.fiap.api.model.PessoaModel;

import java.util.Date;

public class PessoaDTO {

    private String cpf;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private Integer idade;
    private String userName;
    private String password;

    public PessoaDTO() {}

    public PessoaDTO(PessoaModel model) {
        this.cpf = model.getCpf();
        this.nome = model.getNome();
        this.sobrenome = model.getSobrenome();
        this.dataNascimento = model.getDataNascimento();
        this.idade = model.getIdade();
        this.userName = model.getUserName();
        this.password = model.getPassword();
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
