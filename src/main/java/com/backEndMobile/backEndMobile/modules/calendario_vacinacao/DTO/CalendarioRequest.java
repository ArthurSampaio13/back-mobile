package com.backEndMobile.backEndMobile.modules.calendario_vacinacao.DTO;

public record CalendarioRequest(
        String vacina,
        String descricao,
        String dataInicio,
        String dataFim,
        String status,
        Long unidadesSaudeId
) {
}
