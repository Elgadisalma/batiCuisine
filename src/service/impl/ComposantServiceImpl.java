package service.impl;

import entity.Composant;
import entity.Materiel;
import entity.Personnel;
import repository.ComposantRepository;
import service.ComposantService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ComposantServiceImpl implements ComposantService {
    private ComposantRepository composantRepository;
    public ComposantServiceImpl(ComposantRepository composantRepository) {
        this.composantRepository = composantRepository;
    }

    @Override
    public void saveMateriel(Materiel materiel) {
        composantRepository.saveMateriel(materiel);
        System.out.println(" Matériau ajouté avec succès !");
    }

    @Override
    public void savePersonnel(Personnel personnel) {
        composantRepository.savePersonnel(personnel);
        System.out.println("Main-d'œuvre ajoutée avec succès !");
    }

    @Override
    public Optional<Boolean> checkProjectExists(Long projectId) {
        return composantRepository.checkProjectExists(projectId);
    }

    @Override
    public Map<String, Double> displayTaxes(Long projectId) {
        return composantRepository.getAllTaxes(projectId);
    }


}
