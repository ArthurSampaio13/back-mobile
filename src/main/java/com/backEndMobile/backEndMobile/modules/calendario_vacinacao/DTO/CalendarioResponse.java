package com.backEndMobile.backEndMobile.modules.calendario_vacinacao.DTO;

public record CalendarioResponse(
        Long id,
        String vacina,
        String descricao,
        String dataInicio,
        String dataFim,
        String status,
        Long unidadesSaudeId
) {
}
