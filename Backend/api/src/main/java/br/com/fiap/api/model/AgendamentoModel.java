package br.com.fiap.api.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Document(collection = "Agendamento")
public class AgendamentoModel {
    private ObjectId id;
    private String idCulto;
    private Date data;

    public AgendamentoModel(String idCulto, Date data){
        this.idCulto = idCulto;
        this.data = data;
    }

    public AgendamentoModel(){

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getIdCulto() {
        return idCulto;
    }

    public void setIdCulto(String idCulto) {
        this.idCulto = idCulto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
