package com.backEndMobile.backEndMobile.modules.medicamentos.domain;


import com.backEndMobile.backEndMobile.modules.medicamentos.domain.enums.TipoMedicamentoEnum;
import com.backEndMobile.backEndMobile.modules.unidades_saude.domain.UnidadesSaude;
import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "Medicamento")
@Table(name = "medicamentos")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "tipo_medicamento")
    @Enumerated(EnumType.STRING)
    private TipoMedicamentoEnum tipoMedicamento;

    @ManyToOne
    @JoinColumn(
            name = "unidade_saude_id",
            referencedColumnName = "id",
            nullable = false
    )
    private UnidadesSaude unidadesSaude;

    public Medicamento() {}

    private Medicamento(
            String nome,
            String descricao,
            Integer quantidade,
            TipoMedicamentoEnum tipoMedicamento,
            UnidadesSaude unidadesSaude
    ) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.tipoMedicamento = tipoMedicamento;
        this.unidadesSaude = unidadesSaude;
    }

    public static Medicamento newMedicamento(
            String nome,
            String descricao,
            Integer quantidade,
            TipoMedicamentoEnum tipoMedicamento,
            UnidadesSaude unidadesSaude
    ) {
        return new Medicamento(nome, descricao, quantidade, tipoMedicamento, unidadesSaude);
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public TipoMedicamentoEnum getTipoMedicamento() {
        return tipoMedicamento;
    }

    public UnidadesSaude getUnidadesSaude() {
        return unidadesSaude;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setTipoMedicamento(TipoMedicamentoEnum tipoMedicamento) {
        this.tipoMedicamento = tipoMedicamento;
    }

    public void setUnidadesSaude(UnidadesSaude unidadesSaude) {
        this.unidadesSaude = unidadesSaude;
    }
}
