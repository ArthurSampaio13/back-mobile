package com.backEndMobile.backEndMobile.modules.unidades_saude.domain;

import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.enums.TipoUnidade;
import jakarta.persistence.*;
import java.time.Instant;

@Entity(name = "Secretaries")
@Table(name = "unidades_saude")
public class UnidadesSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nome;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoUnidade tipoUnidade;

    @Column(name = "horario_inicio_atendimento")
    private String horarioInicioAtendimento;

    @Column(name = "horario_fim_atendimento")
    private String horarioFimAtendimento;

    @Column(name = "criado_em")
    private String criadoEm;

    @PrePersist
    protected void prePersist() {
        criadoEm = Instant.now().toString();
    }

    public UnidadesSaude() {
    }

    private UnidadesSaude(
            String nome,
            TipoUnidade tipoUnidade,
            String horarioInicioAtendimento,
            String horarioFimAtendimento
    ) {
        this.nome = nome;
        this.tipoUnidade = tipoUnidade;
        this.horarioInicioAtendimento = horarioInicioAtendimento;
        this.horarioFimAtendimento = horarioFimAtendimento;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoUnidade getTipoUnidade() {
        return tipoUnidade;
    }

    public String getHorarioInicioAtendimento() {
        return horarioInicioAtendimento;
    }

    public String getHorarioFimAtendimento() {
        return horarioFimAtendimento;
    }

    public String getCriadoEm() {
        return criadoEm;
    }

    public static UnidadesSaude newUnidadeSaude(
            String nome,
            TipoUnidade tipoUnidade,
            String horarioInicioAtendimento,
            String horarioFimAtendimento
    ) {
        return new UnidadesSaude(nome, tipoUnidade, horarioInicioAtendimento, horarioFimAtendimento);
    }
}
