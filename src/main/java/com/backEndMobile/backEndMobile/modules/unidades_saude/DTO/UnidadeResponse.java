package com.backEndMobile.backEndMobile.modules.unidades_saude.DTO;

import com.backEndMobile.backEndMobile.modules.medicamentos.DTO.MedicamentoResponse;
import com.backEndMobile.backEndMobile.modules.servicos_saude.DTO.ServicosSaudeResponse;

import java.util.List;

public record UnidadeResponse(
        String nome,
        String tipoUnidade,
        String horarioInicioAtendimento,
        String horarioFimAtendimento,
        String criadoEm,
        List<ServicosSaudeResponse> servicosSaudeList,
        List<MedicamentoResponse> medicamentoList
) {
}
