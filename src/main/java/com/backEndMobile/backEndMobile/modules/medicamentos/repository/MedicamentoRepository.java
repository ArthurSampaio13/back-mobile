package com.backEndMobile.backEndMobile.modules.medicamentos.repository;

import com.backEndMobile.backEndMobile.modules.medicamentos.domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, UUID> {
    List<Medicamento> findByUnidadesSaudeId(UUID id);
}
