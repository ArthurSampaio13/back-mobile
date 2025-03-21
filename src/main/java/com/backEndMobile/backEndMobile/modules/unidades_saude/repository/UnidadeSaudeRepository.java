package com.backEndMobile.backEndMobile.modules.unidades_saude.repository;

import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.UnidadesSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeSaudeRepository extends JpaRepository<UnidadesSaude, Long> {


}
