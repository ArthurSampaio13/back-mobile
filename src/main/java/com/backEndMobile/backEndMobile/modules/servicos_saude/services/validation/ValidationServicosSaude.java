package com.backEndMobile.backEndMobile.modules.servicos_saude.services.validation;

import com.backEndMobile.backEndMobile.exceptions.BlankValueException;
import com.backEndMobile.backEndMobile.exceptions.InvalidTimeFormatException;
import com.backEndMobile.backEndMobile.exceptions.InvalidTimeRangeException;
import com.backEndMobile.backEndMobile.modules.servicos_saude.DTO.ServicosSaudeRequest;

public class ValidationServicosSaude {

    public static void validateCreateServicosSaude(ServicosSaudeRequest servicosSaudeRequest) {

        validateIsBlank(servicosSaudeRequest.nome(), "Nome do serviço de saúde não pode ser vazio");
        validateIsBlank(servicosSaudeRequest.descricao(), "Descrição do serviço de saúde não pode ser vazia");
        validateIsBlank(servicosSaudeRequest.horarioInicio(), "Horário de início do serviço de saúde não pode ser vazio");
        validateIsBlank(servicosSaudeRequest.horarioFim(), "Horário de fim do serviço de saúde não pode ser vazio");
        validateHorario(servicosSaudeRequest.horarioInicio(), servicosSaudeRequest.horarioFim());
        validateLengthMin(servicosSaudeRequest.nome());
        validateLengthMin(servicosSaudeRequest.descricao());
    }

    private static void validateIsBlank(String value, String message) {
        if (value.isBlank()) {
            throw new BlankValueException(message);
        }
    }

    private static void validateHorario(String horarioInicio, String horarioFim) {
        if (horarioInicio == null || horarioFim == null || horarioInicio.trim().isEmpty() || horarioFim.trim().isEmpty()) {
            throw new BlankValueException("Horário de início e fim de atendimento não podem ser vazios");
        }

        if (!horarioInicio.matches("\\d{2}:\\d{2}") || !horarioFim.matches("\\d{2}:\\d{2}")) {
            throw new InvalidTimeFormatException("Horário deve estar no formato HH:mm");
        }

        String[] partesInicio = horarioInicio.split(":");
        String[] partesFim = horarioFim.split(":");

        int horaInicio = Integer.parseInt(partesInicio[0]);
        int minutoInicio = Integer.parseInt(partesInicio[1]);

        int horaFim = Integer.parseInt(partesFim[0]);
        int minutoFim = Integer.parseInt(partesFim[1]);

        if (horaInicio < 0 || horaInicio >= 24 || horaFim < 0 || horaFim >= 24) {
            throw new InvalidTimeRangeException("A hora deve estar entre 00 e 23");
        }

        if (minutoInicio < 0 || minutoInicio >= 60 || minutoFim < 0 || minutoFim >= 60) {
            throw new InvalidTimeRangeException("Os minutos devem estar entre 00 e 59");
        }

        if (horarioInicio.equals(horarioFim)) {
            throw new InvalidTimeRangeException("Horário de início e fim de atendimento não podem ser iguais");
        }

        if (horarioInicio.compareTo(horarioFim) > 0) {
            throw new InvalidTimeRangeException("Horário de início de atendimento não pode ser maior que o horário de fim de atendimento");
        }
    }

    private static void validateLengthMin(String value) {
        if (value.length() < 3) {
            throw new IllegalArgumentException("Nome do serviço de saúde deve ter no mínimo 3 caracteres");
        }
    }

}
