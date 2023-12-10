package br.com.senac.quarkus.projetofullstack.entity;

import jakarta.json.bind.annotation.JsonbProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_ATIVO_FINANCEIRO")
public class AtivoFinanceiro {

    @Id
    @JsonbProperty("ID_ATIVO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAtivo;

    @JsonbProperty("STR_NOME_ATIVO")
    @Column(nullable = false)
    private String nomeAtivo;

    @JsonbProperty("STR_TIPO_ATIVO")
    @Column(nullable = false)
    private String tipoAtivo;

    @JsonbProperty("STR_DESCRICAO")
    @Column(nullable = false)
    private String descricao;

    @JsonbProperty("DEC_VALOR_ATUAL")
    @Column(nullable = false)
    private BigDecimal valorAtual;

    @JsonbProperty("DEC_VALOR_MERCADO")
    @Column(nullable = false)
    private BigDecimal valorMercado;

    @JsonbProperty("DEC_RENDIMENTO")
    @Column(nullable = false)
    private BigDecimal rendimento;

    @JsonbProperty("STR_RISCO")
    @Column(nullable = false)
    private String risco;

    @JsonbProperty("STR_SETOR")
    @Column(nullable = false)
    private String setor;

    public Integer getIdAtivo() {
        return idAtivo;
    }

    public void setIdAtivo(Integer idAtivo) {
        this.idAtivo = idAtivo;
    }

    public String getNomeAtivo() {
        return nomeAtivo;
    }

    public void setNomeAtivo(String nomeAtivo) {
        this.nomeAtivo = nomeAtivo;
    }

    public String getTipoAtivo() {
        return tipoAtivo;
    }

    public void setTipoAtivo(String tipoAtivo) {
        this.tipoAtivo = tipoAtivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(BigDecimal valorAtual) {
        this.valorAtual = valorAtual;
    }

    public BigDecimal getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(BigDecimal valorMercado) {
        this.valorMercado = valorMercado;
    }

    public BigDecimal getRendimento() {
        return rendimento;
    }

    public void setRendimento(BigDecimal rendimento) {
        this.rendimento = rendimento;
    }

    public String getRisco() {
        return risco;
    }

    public void setRisco(String risco) {
        this.risco = risco;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}
