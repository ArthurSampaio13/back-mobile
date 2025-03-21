package com.backEndMobile.backEndMobile.modules.unidades_saude.domain;

import com.backEndMobile.backEndMobile.modules.servicos_saude.domain.ServicosSaude;
import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.enums.TipoUnidade;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity(name = "Secretaries")
@Table(name = "unidades_saude")
public class UnidadesSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
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

    @OneToMany(mappedBy = "unidadeSaude", fetch = FetchType.LAZY)
    private List<ServicosSaude> servicosSaudeList;

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

    private UnidadesSaude(
            Long id,
            String nome,
            TipoUnidade tipoUnidade,
            String horarioInicioAtendimento,
            String horarioFimAtendimento,
            String criadoEm,
            List<ServicosSaude> servicosSaudeList
    ) {
        this.id = id;
        this.nome = nome;
        this.tipoUnidade = tipoUnidade;
        this.horarioInicioAtendimento = horarioInicioAtendimento;
        this.horarioFimAtendimento = horarioFimAtendimento;
        this.criadoEm = criadoEm;
        this.servicosSaudeList = servicosSaudeList;
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

    public static UnidadesSaude newUnidadeSaude(
            Long id,
            String nome,
            TipoUnidade tipoUnidade,
            String horarioInicioAtendimento,
            String horarioFimAtendimento,
            String criadoEm,
            List<ServicosSaude> servicosSaudeList
    ) {
        return new UnidadesSaude(id, nome, tipoUnidade, horarioInicioAtendimento, horarioFimAtendimento, criadoEm, servicosSaudeList);
    }
}
