package com.backEndMobile.backEndMobile.modules.unidades_saude.services.mapper;

import com.backEndMobile.backEndMobile.modules.medicamentos.DTO.MedicamentoResponse;
import com.backEndMobile.backEndMobile.modules.medicamentos.domain.Medicamento;
import com.backEndMobile.backEndMobile.modules.medicamentos.repository.MedicamentoRepository;
import com.backEndMobile.backEndMobile.modules.servicos_saude.DTO.ServicosSaudeResponse;
import com.backEndMobile.backEndMobile.modules.servicos_saude.domain.ServicosSaude;
import com.backEndMobile.backEndMobile.modules.servicos_saude.repository.ServicosSaudeRepository;
import com.backEndMobile.backEndMobile.modules.unidades_saude.DTO.UnidadeRequest;
import com.backEndMobile.backEndMobile.modules.unidades_saude.DTO.UnidadeResponse;
import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.UnidadesSaude;
import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.enums.TipoUnidade;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperUnidadeSaude {

    private final ServicosSaudeRepository servicosSaudeRepository;
    private final MedicamentoRepository medicamentoRepository;

    public MapperUnidadeSaude(ServicosSaudeRepository servicosSaudeRepository, MedicamentoRepository medicamentoRepository) {
        this.servicosSaudeRepository = servicosSaudeRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    public UnidadesSaude mapperRequstToDomain(UnidadeRequest unidadeRequest) {
        return UnidadesSaude.newUnidadeSaude(
                unidadeRequest.nome(),
                TipoUnidade.fromString(unidadeRequest.tipoUnidade()),
                unidadeRequest.horarioInicioAtendimento(),
                unidadeRequest.horarioFimAtendimento()
        );
    }

    public UnidadeResponse mapperDomainToResponse(UnidadesSaude unidade) {
        List<ServicosSaude> servicosSaude = servicosSaudeRepository.findByUnidadeSaudeId(unidade.getId());
        List<Medicamento> medicamentos = medicamentoRepository.findByUnidadesSaudeId(unidade.getId());

        List<MedicamentoResponse> medicamentosResponseList = medicamentos.stream()
                .map(medicamento -> new MedicamentoResponse(
                        medicamento.getId(),
                        medicamento.getNome(),
                        medicamento.getDescricao(),
                        medicamento.getQuantidade(),
                        medicamento.getTipoMedicamento().toString(),
                        medicamento.getUnidadesSaude().getId()
                ))
                .toList();

        List<ServicosSaudeResponse> servicosSaudeResponse = servicosSaude.stream()
                .map(servico -> new ServicosSaudeResponse(
                        servico.getId(),
                        servico.getNome(),
                        servico.getDescricao(),
                        servico.getHorarioInicio(),
                        servico.getHorarioFim(),
                        servico.getUnidadeSaude().getId()
                ))
                .toList();

        return new UnidadeResponse(
                unidade.getId(),
                unidade.getNome(),
                unidade.getTipoUnidade().toString(),
                unidade.getHorarioInicioAtendimento(),
                unidade.getHorarioFimAtendimento(),
                unidade.getCriadoEm().toString(),
                servicosSaudeResponse,
                medicamentosResponseList
        );
    }
}
