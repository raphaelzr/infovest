package br.com.senac.quarkus.projetofullstack.service;

import br.com.senac.quarkus.projetofullstack.entity.PrecoDiarioAtivo;
import br.com.senac.quarkus.projetofullstack.repository.PrecoDiarioAtivoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PrecoDiarioAtivoService {

    @Inject
    PrecoDiarioAtivoRepository precoDiarioAtivoRepository;

    public List<PrecoDiarioAtivo> getAllPrecosDiariosAtivos() {
        return precoDiarioAtivoRepository.getAllPrecosDiariosAtivos();
    }

    public PrecoDiarioAtivo getPrecoDiarioAtivoById(Integer id) {
        return precoDiarioAtivoRepository.getPrecoDiarioAtivoById(id);
    }

    public void createPrecoDiarioAtivo(PrecoDiarioAtivo precoDiarioAtivo) {
        precoDiarioAtivoRepository.createPrecoDiarioAtivo(precoDiarioAtivo);
    }

    public void updatePrecoDiarioAtivo(PrecoDiarioAtivo precoDiarioAtivo) {
        precoDiarioAtivoRepository.updatePrecoDiarioAtivo(precoDiarioAtivo);
    }

    public void deletePrecoDiarioAtivo(Integer id) {
        precoDiarioAtivoRepository.deletePrecoDiarioAtivo(id);
    }
}
