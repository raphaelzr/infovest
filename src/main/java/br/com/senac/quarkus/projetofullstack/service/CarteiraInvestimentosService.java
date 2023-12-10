package br.com.senac.quarkus.projetofullstack.service;

import br.com.senac.quarkus.projetofullstack.entity.CarteiraInvestimentos;
import br.com.senac.quarkus.projetofullstack.repository.CarteiraInvestimentosRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CarteiraInvestimentosService {

    @Inject
    CarteiraInvestimentosRepository carteiraInvestimentosRepository;

    public List<CarteiraInvestimentos> getAllCarteirasInvestimentos() {
        return carteiraInvestimentosRepository.getAllCarteirasInvestimentos();
    }

    public CarteiraInvestimentos getCarteiraInvestimentosById(Integer id) {
        return carteiraInvestimentosRepository.getCarteiraInvestimentosById(id);
    }

    public void createCarteiraInvestimentos(CarteiraInvestimentos carteiraInvestimentos) {
        carteiraInvestimentosRepository.createCarteiraInvestimentos(carteiraInvestimentos);
    }

    public void updateCarteiraInvestimentos(CarteiraInvestimentos carteiraInvestimentos) {
        carteiraInvestimentosRepository.updateCarteiraInvestimentos(carteiraInvestimentos);
    }

    public void deleteCarteiraInvestimentos(Integer id) {
        carteiraInvestimentosRepository.deleteCarteiraInvestimentos(id);
    }
}
