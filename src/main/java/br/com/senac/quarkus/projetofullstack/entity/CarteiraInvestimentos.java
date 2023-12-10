package br.com.senac.quarkus.projetofullstack.entity;

import jakarta.json.bind.annotation.JsonbProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_CARTEIRA_INVESTIMENTOS")
public class CarteiraInvestimentos {

    @Id
    @JsonbProperty("ID_CARTEIRA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarteira;

    @JsonbProperty("STR_NOME_CARTEIRA")
    @Column(nullable = false)
    private String nomeCarteira;

    @JsonbProperty("DT_CARTEIRA")
    @Column(nullable = false)
    private LocalDate dataCriacao;

    @JsonbProperty("DEC_DESEMPENHO_TOTAL")
    @Column(nullable = false)
    private BigDecimal desempenhoTotal;

    @JsonbProperty("DEC_VALOR_TOTAL_INVESTIDO")
    @Column(nullable = false)
    private BigDecimal valorTotalInvestido;

    @JsonbProperty("DEC_VALOR_TOTAL_ATUAL")
    @Column(nullable = false)
    private BigDecimal valorTotalAtual;

    @JsonbProperty("DEC_RENTABILIDADE")
    @Column(nullable = false)
    private BigDecimal rentabilidade;

    @JsonbProperty("ID_INVESTIDOR")
    @Column(nullable = false)
    private Integer idInvestidor;

    public Integer getIdCarteira() {
        return idCarteira;
    }

    public void setIdCarteira(Integer idCarteira) {
        this.idCarteira = idCarteira;
    }

    public String getNomeCarteira() {
        return nomeCarteira;
    }

    public void setNomeCarteira(String nomeCarteira) {
        this.nomeCarteira = nomeCarteira;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public BigDecimal getDesempenhoTotal() {
        return desempenhoTotal;
    }

    public void setDesempenhoTotal(BigDecimal desempenhoTotal) {
        this.desempenhoTotal = desempenhoTotal;
    }

    public BigDecimal getValorTotalInvestido() {
        return valorTotalInvestido;
    }

    public void setValorTotalInvestido(BigDecimal valorTotalInvestido) {
        this.valorTotalInvestido = valorTotalInvestido;
    }

    public BigDecimal getValorTotalAtual() {
        return valorTotalAtual;
    }

    public void setValorTotalAtual(BigDecimal valorTotalAtual) {
        this.valorTotalAtual = valorTotalAtual;
    }

    public BigDecimal getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(BigDecimal rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public Integer getIdInvestidor() {
        return idInvestidor;
    }

    public void setIdInvestidor(Integer idInvestidor) {
        this.idInvestidor = idInvestidor;
    }
}
