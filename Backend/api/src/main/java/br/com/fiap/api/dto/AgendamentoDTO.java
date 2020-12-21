package br.com.fiap.api.dto;

import br.com.fiap.api.model.AgendamentoModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Document(collection = "Agendamento")
public class AgendamentoDTO {

    @Id
    private String id;
    private String idCulto;
    private String idPessoa;
    private Date dataAgendamento;
    private String descricao;
    private Date data;

    public AgendamentoDTO() { }

    public AgendamentoDTO(AgendamentoModel model) {
        this.id = model.getId();
        this.idCulto = model.getIdCulto();
        this.idPessoa = model.getIdPessoa();
        this.dataAgendamento = model.getDataAgendamento();
        this.descricao = descricao;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCulto() {
        return idCulto;
    }

    public void setIdCulto(String idCulto) {
        this.idCulto = idCulto;
    }

    public String getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(String idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
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
}
