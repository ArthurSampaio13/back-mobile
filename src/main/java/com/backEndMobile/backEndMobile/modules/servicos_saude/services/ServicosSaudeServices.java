package com.backEndMobile.backEndMobile.modules.servicos_saude.services;

import com.backEndMobile.backEndMobile.exceptions.ServicesHealthNotFound;
import com.backEndMobile.backEndMobile.exceptions.UnityHealthNotaFoundException;
import com.backEndMobile.backEndMobile.modules.servicos_saude.DTO.ServicosSaudeRequest;
import com.backEndMobile.backEndMobile.modules.servicos_saude.DTO.ServicosSaudeResponse;
import com.backEndMobile.backEndMobile.modules.servicos_saude.domain.ServicosSaude;
import com.backEndMobile.backEndMobile.modules.servicos_saude.repository.ServicosSaudeRepository;
import com.backEndMobile.backEndMobile.modules.servicos_saude.services.mapper.MapperServicosSaude;
import com.backEndMobile.backEndMobile.modules.servicos_saude.services.validation.ValidationServicosSaude;
import com.backEndMobile.backEndMobile.modules.unidades_saude.services.UnidadeServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServicosSaudeServices {

    private final ServicosSaudeRepository servicosSaudeRepository;
    private final UnidadeServices unidadeServices;
    private final MapperServicosSaude mapperServicosSaude;

    public ServicosSaudeServices(ServicosSaudeRepository servicosSaudeRepository, UnidadeServices unidadeServices, MapperServicosSaude mapperServicosSaude) {
        this.servicosSaudeRepository = servicosSaudeRepository;
        this.unidadeServices = unidadeServices;
        this.mapperServicosSaude = mapperServicosSaude;
    }

    public ServicosSaudeResponse createServicosSaude(ServicosSaudeRequest servicosSaudeRequest) {
        ValidationServicosSaude.validateCreateServicosSaude(servicosSaudeRequest);
        verifyServicosSaudeId(UUID.fromString(servicosSaudeRequest.unidadeSaudeId()));
        ServicosSaude servicosSaude = mapperServicosSaude.mapperRequestToDomain(servicosSaudeRequest);
        servicosSaudeRepository.save(servicosSaude);
        return mapperServicosSaude.mapperDomainToResponse(servicosSaude);
    }

    public ServicosSaudeResponse getServicosSaude(UUID id) {
        return servicosSaudeRepository.findById(id)
                .map(mapperServicosSaude::mapperDomainToResponse)
                .orElseThrow(() -> new ServicesHealthNotFound("Serviço de saúde não encontrado"));
    }

    public List<ServicosSaudeResponse> getAllServicosSaude() {
        List<ServicosSaude> servicos = servicosSaudeRepository.findAll();
        return servicos.stream()
                .map(mapperServicosSaude::mapperDomainToResponse)
                .collect(Collectors.toList());
    }

    public void deleteServicosSaude(UUID id) {
        servicosSaudeRepository.deleteById(id);
    }

    private void verifyServicosSaudeId(UUID id) {
        if (Boolean.FALSE.equals(unidadeServices.validateUnidadeSaudeId(id))) {
            throw new UnityHealthNotaFoundException("Unidade de saúde não encontrada");
        }
    }
}
