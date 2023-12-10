package br.com.senac.quarkus.projetofullstack.entity;

import jakarta.json.bind.annotation.JsonbProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_PRECO_DIARIO_ATIVO")
public class PrecoDiarioAtivo {

    @Id
    @JsonbProperty("ID_PRECO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPreco;

    @JsonbProperty("DT_ATIVO")
    @Column(nullable = false)
    private LocalDate data;

    @JsonbProperty("DEC_VALOR")
    @Column(nullable = false)
    private BigDecimal valor;

    @JsonbProperty("ID_ATIVO")
    @Column(nullable = false)
    private Integer idAtivo;

    public Integer getIdPreco() {
        return idPreco;
    }

    public void setIdPreco(Integer idPreco) {
        this.idPreco = idPreco;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getIdAtivo() {
        return idAtivo;
    }

    public void setIdAtivo(Integer idAtivo) {
        this.idAtivo = idAtivo;
    }
}
