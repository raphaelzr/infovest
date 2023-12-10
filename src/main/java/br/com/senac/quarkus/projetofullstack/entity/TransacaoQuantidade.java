package br.com.senac.quarkus.projetofullstack.entity;

import jakarta.json.bind.annotation.JsonbProperty;

public class TransacaoQuantidade {

    @JsonbProperty("ATIVO")
    private String ativo;

    @JsonbProperty("CARTEIRA")
    private String carteira;

    @JsonbProperty("QUANTIDADE")
    private int quantidade;

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getCarteira() {
        return carteira;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
