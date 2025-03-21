package com.backEndMobile.backEndMobile.modules.servicos_saude.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ServicosSaudeResponse(
        Long id,
        String nome,
        String descricao,
        @JsonProperty("horario_inicio")
        String horarioInicio,
        @JsonProperty("horario_fim")
        String horarioFim,
        @JsonProperty("unidade_saude_id")
        Long unidadeSaudeId
) {
}
