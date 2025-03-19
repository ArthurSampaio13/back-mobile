package com.backEndMobile.backEndMobile.modules.unidades_saude.services;

import com.backEndMobile.backEndMobile.modules.unidades_saude.DTO.UnidadeRequest;
import com.backEndMobile.backEndMobile.modules.unidades_saude.DTO.UnidadeResponse;
import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.UnidadesSaude;
import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.enums.TipoUnidade;
import com.backEndMobile.backEndMobile.modules.unidades_saude.repository.UnidadeSaudeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeServices {
    private final UnidadeSaudeRepository unidadeSaudeRepository;
    public UnidadeServices(UnidadeSaudeRepository unidadeSaudeRepository) {
        this.unidadeSaudeRepository = unidadeSaudeRepository;
    }

    public UnidadeResponse createUnidade(UnidadeRequest unidadeRequest) {

        UnidadesSaude unidade = mapperRequestToDomain(unidadeRequest);
        unidadeSaudeRepository.save(unidade);
        return mapperDomainToResponse(unidade);
    }

    public UnidadeResponse getUnidade(Long id) {
        return unidadeSaudeRepository.findById(id)
                .map(this::mapperDomainToResponse)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
    }

    public List<UnidadeResponse> getAllUnidades() {
        List<UnidadesSaude> unidades = unidadeSaudeRepository.findAll();
        return unidades.stream()
                .map(this::mapperDomainToResponse)
                .toList();
    }

    public void deleteUnidade(Long id) {
        unidadeSaudeRepository.deleteById(id);
    }

    private UnidadesSaude mapperRequestToDomain(UnidadeRequest unidadeRequest) {
        return UnidadesSaude.newUnidadeSaude(
                unidadeRequest.nome(),
                TipoUnidade.fromString(unidadeRequest.tipoUnidade()),
                unidadeRequest.horarioInicioAtendimento(),
                unidadeRequest.horarioFimAtendimento()
        );
    }

    private UnidadeResponse mapperDomainToResponse(UnidadesSaude unidade) {
        return new UnidadeResponse(
                unidade.getId(),
                unidade.getNome(),
                unidade.getTipoUnidade().toString(),
                unidade.getHorarioInicioAtendimento(),
                unidade.getHorarioFimAtendimento(),
                unidade.getCriadoEm()
        );
    }
}
