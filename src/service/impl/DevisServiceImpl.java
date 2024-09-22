package service.impl;

import entity.Devis;
import repository.DevisRepository;
import service.DevisService;

public class DevisServiceImpl implements DevisService {
    final private DevisRepository devisRepository;

    public DevisServiceImpl(DevisRepository devisRepository) {
        this.devisRepository = devisRepository;
    }

    @Override
    public void addDevis(Devis devis) {
        devisRepository.ajouterDevis(devis);
    }
}
