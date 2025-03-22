package com.backEndMobile.backEndMobile.modules.unidades_saude.services.validation;

import com.backEndMobile.backEndMobile.exceptions.BlankValueException;
import com.backEndMobile.backEndMobile.exceptions.InvalidTimeRangeException;
import com.backEndMobile.backEndMobile.modules.unidades_saude.DTO.UnidadeRequest;
import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.enums.TipoUnidade;

public class ValidationUnidade {

    public static void validateCreateUnidade(UnidadeRequest unidadeRequest) {
        validateBlank(unidadeRequest.nome(), "Nome da unidade não pode ser vazio");
        validateBlank(unidadeRequest.tipoUnidade(), "Tipo da unidade não pode ser vazio");
        validateBlank(unidadeRequest.horarioInicioAtendimento(), "Horário de início de atendimento não pode ser vazio");
        validateBlank(unidadeRequest.horarioFimAtendimento(), "Horário de fim de atendimento não pode ser vazio");
        validateHorario(unidadeRequest.horarioInicioAtendimento(), unidadeRequest.horarioFimAtendimento());
        validateTipoUnidade(unidadeRequest.tipoUnidade());
    }

    private static void validateBlank(String value, String message) {
        if (value.isBlank()) {
            throw new BlankValueException(message);
        }
    }

    private static void validateTipoUnidade(String tipoUnidade) {
        try {
            TipoUnidade.fromString(tipoUnidade);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de unidade inválido");
        }
    }

    private static void validateHorario(String horarioInicio, String horarioFim) {
        if (horarioInicio == null || horarioFim == null || horarioInicio.trim().isEmpty() || horarioFim.trim().isEmpty()) {
            throw new BlankValueException("Horário de início e fim de atendimento não podem ser vazios");
        }
        if (horarioInicio.equals(horarioFim)) {
            throw new InvalidTimeRangeException("Horário de início e fim de atendimento não podem ser iguais");
        }
        if (horarioInicio.compareTo(horarioFim) > 0) {
            throw new InvalidTimeRangeException("Horário de início de atendimento não pode ser maior que o horário de fim de atendimento");
        }
    }
}
