package com.backEndMobile.backEndMobile.modules.medicamentos.DTO;

import java.util.UUID;

public record MedicamentoRequest(
        String nome,
        String descricao,
        Integer quantidade,
        String tipoMedicamento,
        UUID unidadesSaudeId
    ) {
}
