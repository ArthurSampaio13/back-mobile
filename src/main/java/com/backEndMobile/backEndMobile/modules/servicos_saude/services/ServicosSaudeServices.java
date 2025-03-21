package com.backEndMobile.backEndMobile.modules.servicos_saude.services;

import com.backEndMobile.backEndMobile.modules.servicos_saude.DTO.ServicosSaudeRequest;
import com.backEndMobile.backEndMobile.modules.servicos_saude.DTO.ServicosSaudeResponse;
import com.backEndMobile.backEndMobile.modules.servicos_saude.domain.ServicosSaude;
import com.backEndMobile.backEndMobile.modules.servicos_saude.repository.ServicosSaudeRepository;
import com.backEndMobile.backEndMobile.modules.servicos_saude.services.validation.ValidationServicosSaude;
import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.UnidadesSaude;
import com.backEndMobile.backEndMobile.modules.unidades_saude.services.UnidadeServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicosSaudeServices {

    private final ServicosSaudeRepository servicosSaudeRepository;
    private final UnidadeServices unidadeServices;

    public ServicosSaudeServices(ServicosSaudeRepository servicosSaudeRepository, UnidadeServices unidadeServices) {
        this.servicosSaudeRepository = servicosSaudeRepository;
        this.unidadeServices = unidadeServices;
    }

    public ServicosSaudeResponse createServicosSaude(ServicosSaudeRequest servicosSaudeRequest) {
        ValidationServicosSaude.validateCreateServicosSaude(servicosSaudeRequest);
        validateUnidadeSaudeId(servicosSaudeRequest.unidadeSaudeId());
        ServicosSaude servicosSaude = mapperRequestToDomain(servicosSaudeRequest);
        servicosSaudeRepository.save(servicosSaude);
        return mapperDomainToResponse(servicosSaude);
    }

    public ServicosSaudeResponse getServicosSaude(Long id) {
        return servicosSaudeRepository.findById(id)
                .map(this::mapperDomainToResponse)
                .orElseThrow(() -> new RuntimeException("Serviço de saúde não encontrado"));
    }

    public List<ServicosSaudeResponse> getAllServicosSaude() {
        List<ServicosSaude> servicos = servicosSaudeRepository.findAll();
        return servicos.stream()
                .map(this::mapperDomainToResponse)
                .toList();
    }

    public void deleteServicosSaude(Long id) {
        servicosSaudeRepository.deleteById(id);
    }

    private ServicosSaude mapperRequestToDomain(ServicosSaudeRequest servicosSaudeRequest) {
        UnidadesSaude unidadeSaude  = unidadeServices.getUnidadeById(servicosSaudeRequest.unidadeSaudeId());
        return ServicosSaude.newServicosSaude(
                servicosSaudeRequest.nome(),
                servicosSaudeRequest.descricao(),
                servicosSaudeRequest.horarioInicio(),
                servicosSaudeRequest.horarioFim(),
                unidadeSaude);
    }

    private ServicosSaudeResponse mapperDomainToResponse(ServicosSaude servicosSaude) {
        return new ServicosSaudeResponse(
                servicosSaude.getId(),
                servicosSaude.getNome(),
                servicosSaude.getDescricao(),
                servicosSaude.getHorarioInicio(),
                servicosSaude.getHorarioFim(),
                servicosSaude.getUnidadeSaude().getId()
        );
    }

    private void validateUnidadeSaudeId(Long unidadeSaudeId) {
        if (!unidadeServices.validateUnidadeSaudeId(unidadeSaudeId)) {
            throw new RuntimeException("Unidade de saúde não encontrada");
        }
    }
}
