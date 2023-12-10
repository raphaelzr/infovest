package br.com.senac.quarkus.projetofullstack.entity;

import jakarta.json.bind.annotation.JsonbProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_INVESTIDOR")
public class Investidor {

    @Id
    @JsonbProperty("ID_INVESTIDOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInvestidor;

    @JsonbProperty("STR_NOME")
    @Column(nullable = false)
    private String nome;

    @JsonbProperty("STR_ENDERECO")
    @Column(nullable = false)
    private String endereco;

    @JsonbProperty("STR_EMAIL")
    @Column(nullable = false)
    private String email;

    @JsonbProperty("STR_TELEFONE")
    @Column(nullable = false)
    private String telefone;

    @JsonbProperty("STR_CPF")
    @Column(nullable = false)
    private String cpf;

    @JsonbProperty("DT_NASCIMENTO")
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @JsonbProperty("DEC_SALDO_DISPONIVEL")
    @Column(nullable = false)
    private BigDecimal saldoDisponivel;

    public Integer getIdInvestidor() {
        return idInvestidor;
    }

    public void setIdInvestidor(Integer idInvestidor) {
        this.idInvestidor = idInvestidor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public BigDecimal getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setSaldoDisponivel(BigDecimal saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }
}
