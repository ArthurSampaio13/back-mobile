package com.backEndMobile.backEndMobile.modules.medicamentos.DTO;

public record MedicamentoResponse(
        String nome,
        String descricao,
        Integer quantidade,
        String tipoMedicamento
    ) {
}
