package br.com.fiap.api.dto;

import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Date;

public class CultoPartialUpdateDTO {

    private JsonNullable<String> idInstituicao = JsonNullable.undefined();
    private JsonNullable<String> descricao = JsonNullable.undefined();
    private JsonNullable<Date> data = JsonNullable.undefined();
    private JsonNullable<Integer> duracao = JsonNullable.undefined();
    private JsonNullable<Integer> capacidade = JsonNullable.undefined();

    protected CultoPartialUpdateDTO() { }

    public JsonNullable<String> getIdInstituicao() { return idInstituicao; }

    public JsonNullable<String> getDescricao() { return descricao; }

    public JsonNullable<Date> getData() { return data; }

    public JsonNullable<Integer> getDuracao() { return duracao; }

    public JsonNullable<Integer> getCapacidade() { return  capacidade; }


}
