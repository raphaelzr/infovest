package br.com.senac.quarkus.projetofullstack.service;

import br.com.senac.quarkus.projetofullstack.entity.Transacao;
import br.com.senac.quarkus.projetofullstack.entity.TransacaoQuantidade;
import br.com.senac.quarkus.projetofullstack.repository.TransacaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class TransacaoService {

    @Inject
    TransacaoRepository transacaoRepository;

    public List<Transacao> getAllTransacoes() {
        return transacaoRepository.getAllTransacoes();
    }

    public Transacao getTransacaoById(Integer id) {
        return transacaoRepository.getTransacaoById(id);
    }

    public void createTransacao(Transacao transacao) {
        transacaoRepository.createTransacao(transacao);
    }

    public void updateTransacao(Transacao transacao) {
        transacaoRepository.updateTransacao(transacao);
    }

    public void deleteTransacao(Integer id) {
        transacaoRepository.deleteTransacao(id);
    }

    public List<Transacao> getTransacoesByCarteiraAndAtivo(Integer carteira, Integer ativo) {
        List<Transacao> transacoes = transacaoRepository.getAllTransacoesByCarteiraAndAtivo(carteira, ativo);

        return transacoes;
    }

    public List<Transacao> getTransacoesByCarteira(Integer carteira) {
        List<Transacao> transacoes = transacaoRepository.getAllTransacoesByCarteira(carteira);

        return transacoes;
    }

    public List<TransacaoQuantidade> getTransacoesQuantidadeByCarteira(Integer carteira) {
        List<Transacao> transacoes = transacaoRepository.getAllTransacoesByCarteira(carteira);

        Map<String, TransacaoQuantidade> consolidadoPorAtivo = new HashMap<>();

        for (Transacao transacao : transacoes) {
            String chaveAtivo = transacao.getIdAtivo().toString();

            TransacaoQuantidade transacaoQuantidade = consolidadoPorAtivo.get(chaveAtivo);
            if (transacaoQuantidade == null) {
                transacaoQuantidade = new TransacaoQuantidade();
                transacaoQuantidade.setAtivo(chaveAtivo);
                transacaoQuantidade.setCarteira(transacao.getIdCarteira().toString());
                transacaoQuantidade.setQuantidade(0);
            }

            if ("Compra".equals(transacao.getTipoTransacao())) {
                transacaoQuantidade.setQuantidade(transacaoQuantidade.getQuantidade() + transacao.getQuantidade());
            } else if ("Venda".equals(transacao.getTipoTransacao())) {
                transacaoQuantidade.setQuantidade(transacaoQuantidade.getQuantidade() - transacao.getQuantidade());
            }
            consolidadoPorAtivo.put(chaveAtivo, transacaoQuantidade);
        }

        List<TransacaoQuantidade> resultadoConsolidado = new ArrayList<>(consolidadoPorAtivo.values());

        return resultadoConsolidado;
    }
}
