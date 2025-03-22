package com.backEndMobile.backEndMobile.modules.unidades_saude.services;

import com.backEndMobile.backEndMobile.exceptions.UnityHealthNotaFoundException;
import com.backEndMobile.backEndMobile.modules.servicos_saude.repository.ServicosSaudeRepository;
import com.backEndMobile.backEndMobile.modules.unidades_saude.DTO.UnidadeRequest;
import com.backEndMobile.backEndMobile.modules.unidades_saude.DTO.UnidadeResponse;
import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.UnidadesSaude;
import com.backEndMobile.backEndMobile.modules.unidades_saude.repository.UnidadeSaudeRepository;
import com.backEndMobile.backEndMobile.modules.unidades_saude.services.mapper.MapperUnidadeSaude;
import com.backEndMobile.backEndMobile.modules.unidades_saude.services.validation.ValidationUnidade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnidadeServices {
    private final UnidadeSaudeRepository unidadeSaudeRepository;
    private final ServicosSaudeRepository servicosSaudeRepository;
    private final MapperUnidadeSaude mapperUnidadeSaude;

    public UnidadeServices(UnidadeSaudeRepository unidadeSaudeRepository,
                           ServicosSaudeRepository servicosSaudeRepository,
                           MapperUnidadeSaude mapperUnidadeSaude
    ) {
        this.unidadeSaudeRepository = unidadeSaudeRepository;
        this.servicosSaudeRepository = servicosSaudeRepository;
        this.mapperUnidadeSaude = mapperUnidadeSaude;
    }

    public UnidadeResponse createUnidade(UnidadeRequest unidadeRequest) {
        ValidationUnidade.validateCreateUnidade(unidadeRequest);
        UnidadesSaude unidade = mapperUnidadeSaude.mapperRequstToDomain(unidadeRequest);
        unidadeSaudeRepository.save(unidade);
        return mapperUnidadeSaude.mapperDomainToResponse(unidade);
    }

    public UnidadeResponse getUnidade(Long id) {
        return unidadeSaudeRepository.findById(id)
                .map(mapperUnidadeSaude::mapperDomainToResponse)
                .orElseThrow(() -> new UnityHealthNotaFoundException("Unidade não encontrada"));
    }

    public UnidadesSaude getUnidadeById(Long id) {
        return unidadeSaudeRepository.findById(id)
                .orElseThrow(() -> new UnityHealthNotaFoundException("Unidade não encontrada"));
    }

    public Boolean validateUnidadeSaudeId(Long id) {
        return unidadeSaudeRepository.existsById(id);
    }

    public List<UnidadeResponse> getAllUnidades() {
        List<UnidadesSaude> unidades = unidadeSaudeRepository.findAll();
        return unidades.stream()
                .map(mapperUnidadeSaude::mapperDomainToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUnidade(Long id) {
        servicosSaudeRepository.deleteByUnidadeSaudeId(id);
        unidadeSaudeRepository.deleteById(id);
    }
}
