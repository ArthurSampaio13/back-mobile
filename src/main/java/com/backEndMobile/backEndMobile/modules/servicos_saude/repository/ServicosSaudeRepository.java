package com.backEndMobile.backEndMobile.modules.servicos_saude.repository;

import com.backEndMobile.backEndMobile.modules.servicos_saude.domain.ServicosSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicosSaudeRepository extends JpaRepository<ServicosSaude, Long> {
    void deleteByUnidadeSaudeId(Long id);

    List<ServicosSaude> findByUnidadeSaudeId(Long id);
}
