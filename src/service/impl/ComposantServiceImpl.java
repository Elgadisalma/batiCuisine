package service.impl;

import entity.Materiel;
import entity.Personnel;
import repository.ComposantRepository;
import service.ComposantService;

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
}
