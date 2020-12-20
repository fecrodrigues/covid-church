package br.com.fiap.api.utils;

import br.com.fiap.api.model.AgendamentoModel;

import java.util.List;

public class FindAgendamento {
    public Boolean findAgendamento(String idCulto, List<AgendamentoModel> agendamentos) {
        for (AgendamentoModel agendamentoModel : agendamentos) {
            if (agendamentoModel.getIdCulto().equals(idCulto)) {
                return true;
            }
        }
        return false;
    }
}
