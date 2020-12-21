package br.com.fiap.api.dto;

import org.openapitools.jackson.nullable.JsonNullable;

public class InstituicaoPartialUpdateDTO {

    private JsonNullable<String> idUsuario = JsonNullable.undefined();
    private JsonNullable<String> nome = JsonNullable.undefined();
    private JsonNullable<String> endereco = JsonNullable.undefined();
    private JsonNullable<Integer> capacidade = JsonNullable.undefined();

    protected InstituicaoPartialUpdateDTO () {}

    public JsonNullable<String> getIdUsuario() { return idUsuario; }

    public JsonNullable<String> getNome() { return nome; }

    public JsonNullable<String> getEndereco() { return endereco; }

    public JsonNullable<Integer> getCapacidade() { return capacidade; }

}
