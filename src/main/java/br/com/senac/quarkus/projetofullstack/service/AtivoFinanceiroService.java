package br.com.senac.quarkus.projetofullstack.service;

import br.com.senac.quarkus.projetofullstack.entity.AtivoFinanceiro;
import br.com.senac.quarkus.projetofullstack.repository.AtivoFinanceiroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class AtivoFinanceiroService {

    @Inject
    AtivoFinanceiroRepository ativoFinanceiroRepository;

    public List<AtivoFinanceiro> getAllAtivosFinanceiros() {
        return ativoFinanceiroRepository.getAllAtivosFinanceiros();
    }

    public AtivoFinanceiro getAtivoFinanceiroById(Integer id) {
        return ativoFinanceiroRepository.getAtivoFinanceiroById(id);
    }

    public void createAtivoFinanceiro(AtivoFinanceiro ativo) {
        ativoFinanceiroRepository.createAtivoFinanceiro(ativo);
    }

    public void updateAtivoFinanceiro(AtivoFinanceiro ativo) {
        ativoFinanceiroRepository.updateAtivoFinanceiro(ativo);
    }

    public void deleteAtivoFinanceiro(Integer id) {
        ativoFinanceiroRepository.deleteAtivoFinanceiro(id);
    }
}
