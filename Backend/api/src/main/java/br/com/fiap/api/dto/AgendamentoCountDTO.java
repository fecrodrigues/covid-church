package br.com.fiap.api.dto;

public class AgendamentoCountDTO {

    private Integer count;

    public AgendamentoCountDTO() {};

    public AgendamentoCountDTO(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
