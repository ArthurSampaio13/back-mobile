package com.backEndMobile.backEndMobile.modules.medicamentos.services.validation;

import com.backEndMobile.backEndMobile.modules.medicamentos.DTO.MedicamentoRequest;
import com.backEndMobile.backEndMobile.modules.medicamentos.domain.enums.TipoMedicamentoEnum;
import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.enums.TipoUnidade;

public class ValidationMedicamento {

    public static void validateMedicamentoRequest(MedicamentoRequest medicamentoRequest) {
        validateNome(medicamentoRequest.nome());
        validateDescricao(medicamentoRequest.descricao());
        validateQuantidade(medicamentoRequest.quantidade());
        validateTipoMedicamento(medicamentoRequest.tipoMedicamento());
        validateTipoMedicamento(medicamentoRequest.tipoMedicamento());
    }

    private static void validateNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do medicamento não pode ser vazio");
        }
        if (nome.length() < 3) {
            throw new IllegalArgumentException("Nome do medicamento deve ter no mínimo 3 caracteres");
        }
    }

    private static void validateDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição do medicamento não pode ser vazia");
        }
        if (descricao.length() < 3) {
            throw new IllegalArgumentException("Descrição do medicamento deve ter no mínimo 3 caracteres");
        }
    }

    private static void validateQuantidade(Integer quantidade) {
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade do medicamento deve ser maior que 0");
        }
    }

    private static void validateTipoMedicamento(String tipoUnidade) {
        try {
            TipoMedicamentoEnum.fromString(tipoUnidade);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de medicamento inválido");
        }
    }
}
