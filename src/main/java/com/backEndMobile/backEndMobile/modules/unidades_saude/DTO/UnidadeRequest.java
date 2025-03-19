package com.backEndMobile.backEndMobile.modules.unidades_saude.DTO;

public record UnidadeRequest(
        String nome,
        String tipoUnidade,
        String horarioInicioAtendimento,
        String horarioFimAtendimento
) {
}
