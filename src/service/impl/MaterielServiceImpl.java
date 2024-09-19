package service.impl;

import entity.Materiel;
import repository.MaterielRepository;
import service.MaterielService;

public class MaterielServiceImpl implements MaterielService {
    private MaterielRepository materielRepository;
    public MaterielServiceImpl(MaterielRepository materielRepository) {
        this.materielRepository = materielRepository;
    }

    @Override
    public void saveMateriel(Materiel materiel) {
        materielRepository.save(materiel);
        System.out.println(" Matériau ajouté avec succès !");
    }
}
