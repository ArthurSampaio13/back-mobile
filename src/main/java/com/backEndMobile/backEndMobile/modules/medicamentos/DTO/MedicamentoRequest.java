package com.backEndMobile.backEndMobile.modules.medicamentos.DTO;

public record MedicamentoRequest(
        String nome,
        String descricao,
        Integer quantidade,
        String tipoMedicamento,
        Long unidadesSaudeId
    ) {
}
