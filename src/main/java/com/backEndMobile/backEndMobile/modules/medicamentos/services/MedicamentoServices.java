package com.backEndMobile.backEndMobile.modules.medicamentos.services;

import com.backEndMobile.backEndMobile.exceptions.MedicamentoNotFound;
import com.backEndMobile.backEndMobile.exceptions.UnityHealthNotaFoundException;
import com.backEndMobile.backEndMobile.modules.medicamentos.DTO.MedicamentoRequest;
import com.backEndMobile.backEndMobile.modules.medicamentos.DTO.MedicamentoResponse;
import com.backEndMobile.backEndMobile.modules.medicamentos.domain.Medicamento;
import com.backEndMobile.backEndMobile.modules.medicamentos.domain.enums.TipoMedicamentoEnum;
import com.backEndMobile.backEndMobile.modules.medicamentos.repository.MedicamentoRepository;
import com.backEndMobile.backEndMobile.modules.medicamentos.services.mapper.MapperMedicamento;
import com.backEndMobile.backEndMobile.modules.medicamentos.services.validation.ValidationMedicamento;
import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.UnidadesSaude;
import com.backEndMobile.backEndMobile.modules.unidades_saude.repository.UnidadeSaudeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicamentoServices {

    private final MedicamentoRepository medicamentoRepository;
    private final UnidadeSaudeRepository unidadeSaudeRepository;
    private final MapperMedicamento mapperMedicamento;

    public MedicamentoServices(MedicamentoRepository medicamentoRepository, UnidadeSaudeRepository unidadeSaudeRepository, MapperMedicamento mapperMedicamento) {
        this.medicamentoRepository = medicamentoRepository;
        this.unidadeSaudeRepository = unidadeSaudeRepository;
        this.mapperMedicamento = mapperMedicamento;
    }

    public MedicamentoResponse createMedicamento(MedicamentoRequest medicamentoRequest) {
        ValidationMedicamento.validateMedicamentoRequest(medicamentoRequest);
        verifyUnidadeSaudeExists(medicamentoRequest.unidadesSaudeId());
        Medicamento medicamento = mapperMedicamento.mapperRequestToDomain(medicamentoRequest);
        medicamentoRepository.save(medicamento);
        return mapperMedicamento.mapperDomainToResponse(medicamento);
    }

    public List<MedicamentoResponse> createAllMedicamento(List<MedicamentoRequest> medicamentoRequest) {
        medicamentoRequest.forEach(ValidationMedicamento::validateMedicamentoRequest);
        medicamentoRequest.forEach(medicamentoRequest1 -> verifyUnidadeSaudeExists(medicamentoRequest1.unidadesSaudeId()));
        List<Medicamento> medicamentos = medicamentoRequest.stream()
                .map(mapperMedicamento::mapperRequestToDomain)
                .collect(Collectors.toList());
        medicamentoRepository.saveAll(medicamentos);
        return medicamentos.stream()
                .map(mapperMedicamento::mapperDomainToResponse)
                .collect(Collectors.toList());
    }

    public List<MedicamentoResponse> getAllMedicamento() {
        List<Medicamento> medicamentos = medicamentoRepository.findAll();
        return medicamentos.stream()
                .map(mapperMedicamento::mapperDomainToResponse)
                .collect(Collectors.toList());
    }

    public void deleteMedicamento(Long id) {
        medicamentoRepository.deleteById(id);
    }

    public MedicamentoRequest updateMedicamento(MedicamentoRequest medicamentoRequest, Long id) {
        ValidationMedicamento.validateMedicamentoRequest(medicamentoRequest);

        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new MedicamentoNotFound("Medicamento não encontrado"));

        UnidadesSaude unidadesSaude = unidadeSaudeRepository.findById(medicamentoRequest.unidadesSaudeId())
                .orElseThrow(() -> new UnityHealthNotaFoundException("Unidade de saúde não encontrada"));

        medicamento.setNome(medicamentoRequest.nome());
        medicamento.setDescricao(medicamentoRequest.descricao());
        medicamento.setQuantidade(medicamentoRequest.quantidade());
        medicamento.setTipoMedicamento(TipoMedicamentoEnum.fromString(medicamentoRequest.tipoMedicamento()));
        medicamento.setUnidadesSaude(unidadesSaude);
        medicamentoRepository.saveAndFlush(medicamento);
        return medicamentoRequest;
    }

    private void verifyUnidadeSaudeExists(Long id) {
        if (Boolean.FALSE.equals(unidadeSaudeRepository.existsById(id))) {
            throw new UnityHealthNotaFoundException("Unidade de saúde não encontrada");
        }
    }
}
