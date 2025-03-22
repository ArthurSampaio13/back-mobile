package com.backEndMobile.backEndMobile.modules.medicamentos.repository;

import com.backEndMobile.backEndMobile.modules.medicamentos.domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

}
