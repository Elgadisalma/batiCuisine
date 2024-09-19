package service.impl;

import entity.Materiel;
import repository.ComposantRepository;
import service.ComposantService;

public class ComposantServiceImpl implements ComposantService {
    private ComposantRepository composantRepository;
    public ComposantServiceImpl(ComposantRepository composantRepository) {
        this.composantRepository = composantRepository;
    }

    @Override
    public void saveMateriel(Materiel materiel) {
        composantRepository.save(materiel);
        System.out.println(" Matériau ajouté avec succès !");
    }
}
