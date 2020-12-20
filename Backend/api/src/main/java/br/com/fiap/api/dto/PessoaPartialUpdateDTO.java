package br.com.fiap.api.dto;

import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Date;

public class PessoaPartialUpdateDTO {

    private JsonNullable<String> nome = JsonNullable.undefined();
    private JsonNullable<String> sobrenome = JsonNullable.undefined();
    private JsonNullable<Date> dataNascimento = JsonNullable.undefined();
    private JsonNullable<Integer> idade = JsonNullable.undefined();
    private JsonNullable<String> userName = JsonNullable.undefined();
    private JsonNullable<String> password = JsonNullable.undefined();

    protected PessoaPartialUpdateDTO() { }

    public JsonNullable<String> getNome() {
        return nome;
    }

    public JsonNullable<String> getSobrenome() {
        return sobrenome;
    }

    public JsonNullable<Date> getDataNascimento() {
        return dataNascimento;
    }

    public JsonNullable<Integer> getIdade() {
        return idade;
    }

    public JsonNullable<String> getUserName() {
        return userName;
    }

    public JsonNullable<String> getPassword() {
        return password;
    }

}
