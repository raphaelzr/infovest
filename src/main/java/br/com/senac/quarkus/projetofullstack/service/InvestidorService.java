package br.com.senac.quarkus.projetofullstack.service;

import br.com.senac.quarkus.projetofullstack.entity.Investidor;
import br.com.senac.quarkus.projetofullstack.repository.InvestidorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class InvestidorService {

    @Inject
    InvestidorRepository investidorRepository;

    public List<Investidor> getAllInvestidores() {
        return investidorRepository.getAllInvestidores();
    }

    public Investidor getInvestidorById(Integer id) {
        return investidorRepository.getInvestidorById(id);
    }

    public void createInvestidor(Investidor investidor) {
        investidorRepository.createInvestidor(investidor);
    }

    public void updateInvestidor(Investidor investidor) {
        investidorRepository.updateInvestidor(investidor);
    }

    public void deleteInvestidor(Integer id) {
        investidorRepository.deleteInvestidor(id);
    }
}
