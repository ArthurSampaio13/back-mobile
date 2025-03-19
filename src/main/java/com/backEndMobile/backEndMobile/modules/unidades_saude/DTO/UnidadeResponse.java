package com.backEndMobile.backEndMobile.modules.unidades_saude.DTO;

public record UnidadeResponse(
        Long id,
        String nome,
        String tipoUnidade,
        String horarioInicioAtendimento,
        String horarioFimAtendimento,
        String criadoEm
) {
}
