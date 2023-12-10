package br.com.senac.quarkus.projetofullstack.entity;

import jakarta.json.bind.annotation.JsonbProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_TRANSACAO")
public class Transacao {

    @Id
    @JsonbProperty("ID_TRANSACAO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransacao;

    @JsonbProperty("STR_TIPO_TRANSACAO")
    @Column(nullable = false)
    private String tipoTransacao;

    @JsonbProperty("INT_QUANTIDADE")
    @Column(nullable = false)
    private Integer quantidade;

    @JsonbProperty("DT_HR_TRANSACAO")
    @Column(nullable = false)
    private LocalDateTime dataHora;

    @JsonbProperty("DEC_VALOR_TOTAL_TRANSACAO")
    @Column(nullable = false)
    private BigDecimal valorTotalTransacao;

    @JsonbProperty("STR_STATUS_TRANSACAO")
    @Column(nullable = false)
    private String statusTransacao;

    @JsonbProperty("ID_CARTEIRA")
    @Column(nullable = false)
    private Integer idCarteira;

    @JsonbProperty("ID_ATIVO")
    @Column(nullable = false)
    private Integer idAtivo;

    @JsonbProperty("ID_PRECO")
    @Column(nullable = false)
    private Integer idPreco;

    public Integer getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Integer idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public BigDecimal getValorTotalTransacao() {
        return valorTotalTransacao;
    }

    public void setValorTotalTransacao(BigDecimal valorTotalTransacao) {
        this.valorTotalTransacao = valorTotalTransacao;
    }

    public String getStatusTransacao() {
        return statusTransacao;
    }

    public void setStatusTransacao(String statusTransacao) {
        this.statusTransacao = statusTransacao;
    }

    public Integer getIdCarteira() {
        return idCarteira;
    }

    public void setIdCarteira(Integer idCarteira) {
        this.idCarteira = idCarteira;
    }

    public Integer getIdAtivo() {
        return idAtivo;
    }

    public void setIdAtivo(Integer idAtivo) {
        this.idAtivo = idAtivo;
    }

    public Integer getIdPreco() {
        return idPreco;
    }

    public void setIdPreco(Integer idPreco) {
        this.idPreco = idPreco;
    }
}
