package com.backEndMobile.backEndMobile.modules.medicamentos.DTO;

public record MedicamentoResponse(
        Long id,
        String nome,
        String descricao,
        Integer quantidade,
        String tipoMedicamento,
        Long unidadesSaudeId
    ) {
}
