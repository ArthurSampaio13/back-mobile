package com.backEndMobile.backEndMobile.modules.servicos_saude.domain;

import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.UnidadesSaude;
import jakarta.persistence.*;

import java.util.UUID;


@Entity(name = "ServicosSaude")
@Table(name = "servicos_saude")
public class ServicosSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "horario_inicio")
    private String horarioInicio;

    @Column(name = "horario_fim")
    private String horarioFim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "unidade_saude_id",
            referencedColumnName = "id",
            nullable = false
    )
    private UnidadesSaude unidadeSaude;

    public ServicosSaude() {}

    private ServicosSaude(
            String nome,
            String descricao,
            String horarioInicio,
            String horarioFim,
            UnidadesSaude unidadeSaude
    ) {
        this.nome = nome;
        this.descricao = descricao;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.unidadeSaude = unidadeSaude;
    }

    public static ServicosSaude newServicosSaude(
            String nome,
            String descricao,
            String horarioInicio,
            String horarioFim,
            UnidadesSaude unidadeSaude
    ) {
        return new ServicosSaude(nome, descricao, horarioInicio, horarioFim, unidadeSaude);
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public UnidadesSaude getUnidadeSaude() {
        return unidadeSaude;
    }
}
