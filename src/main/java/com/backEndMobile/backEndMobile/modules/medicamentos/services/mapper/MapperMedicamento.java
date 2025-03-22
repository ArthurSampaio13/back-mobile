package com.backEndMobile.backEndMobile.modules.medicamentos.services.mapper;

import com.backEndMobile.backEndMobile.modules.medicamentos.DTO.MedicamentoRequest;
import com.backEndMobile.backEndMobile.modules.medicamentos.DTO.MedicamentoResponse;
import com.backEndMobile.backEndMobile.modules.medicamentos.domain.Medicamento;
import com.backEndMobile.backEndMobile.modules.medicamentos.domain.enums.TipoMedicamentoEnum;
import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.UnidadesSaude;
import com.backEndMobile.backEndMobile.modules.unidades_saude.services.UnidadeServices;
import org.springframework.stereotype.Component;

@Component
public class MapperMedicamento {

    private final UnidadeServices unidadeServices;

    public MapperMedicamento(UnidadeServices unidadeServices) {
        this.unidadeServices = unidadeServices;
    }


    public Medicamento mapperRequestToDomain(MedicamentoRequest medicamentoRequest) {
        UnidadesSaude unidade = unidadeServices.getUnidadeById(medicamentoRequest.unidadesSaudeId());

        return Medicamento.newMedicamento(
                medicamentoRequest.nome(),
                medicamentoRequest.descricao(),
                medicamentoRequest.quantidade(),
                TipoMedicamentoEnum.fromString(medicamentoRequest.tipoMedicamento()),
                unidade
        );
    }

    public MedicamentoResponse mapperDomainToResponse(Medicamento medicamento) {
        return new MedicamentoResponse(
                medicamento.getId(),
                medicamento.getNome(),
                medicamento.getDescricao(),
                medicamento.getQuantidade(),
                medicamento.getTipoMedicamento().toString(),
                medicamento.getUnidadesSaude().getId()
        );
    }
}
