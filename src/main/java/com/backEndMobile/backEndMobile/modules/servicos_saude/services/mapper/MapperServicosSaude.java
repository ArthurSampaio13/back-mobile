package com.backEndMobile.backEndMobile.modules.servicos_saude.services.mapper;

import com.backEndMobile.backEndMobile.modules.servicos_saude.DTO.ServicosSaudeRequest;
import com.backEndMobile.backEndMobile.modules.servicos_saude.DTO.ServicosSaudeResponse;
import com.backEndMobile.backEndMobile.modules.servicos_saude.domain.ServicosSaude;
import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.UnidadesSaude;
import com.backEndMobile.backEndMobile.modules.unidades_saude.services.UnidadeServices;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MapperServicosSaude {

    private final UnidadeServices unidadeServices;

    public MapperServicosSaude(UnidadeServices unidadeServices) {
        this.unidadeServices = unidadeServices;
    }

    public ServicosSaude mapperRequestToDomain(ServicosSaudeRequest servicosSaudeRequest) {
        UnidadesSaude unidadeSaude  = unidadeServices.getUnidadeById(UUID.fromString(servicosSaudeRequest.unidadeSaudeId()));

        return ServicosSaude.newServicosSaude(
                servicosSaudeRequest.nome(),
                servicosSaudeRequest.descricao(),
                servicosSaudeRequest.horarioInicio(),
                servicosSaudeRequest.horarioFim(),
                unidadeSaude);
    }

    public ServicosSaudeResponse mapperDomainToResponse(ServicosSaude servicosSaude) {
        return new ServicosSaudeResponse(
                servicosSaude.getNome(),
                servicosSaude.getDescricao(),
                servicosSaude.getHorarioInicio(),
                servicosSaude.getHorarioFim()
        );
    }
}
