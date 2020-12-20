package br.com.fiap.api.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;

@Repository
@Document(collection = "Agendamento")
public class AgendamentoModel {
    private String id;
    private String idCulto;
    private String idPessoa;
    private Date data;
    private Date dataAgendamento;

    public AgendamentoModel(String id, String idCulto, String idPessoa, Date data, Date dataAgendamento){
        this.id = id;
        this.idCulto = idCulto;
        this.idPessoa = idPessoa;
        this.data = data;
        this.dataAgendamento = dataAgendamento;
    }

    public AgendamentoModel(){

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
}
