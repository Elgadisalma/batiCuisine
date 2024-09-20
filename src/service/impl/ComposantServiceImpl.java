package service.impl;

import entity.Composant;
import entity.Materiel;
import entity.Personnel;
import repository.ComposantRepository;
import service.ComposantService;

import java.util.HashMap;
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

    @Override
    public Map<String, Double> getAllMateriels(Long projectId) {
        List<Materiel> materiels = composantRepository.getAllMateriels(projectId);
        double totalMaterielCout = 0;

        for (Materiel materiel : materiels) {
            double coutMateriel =
                    ((materiel.getCoutUnitaire() * materiel.getQuantite() * materiel.getCoefficientQualite())
                            + materiel.getCoutTransport()) * materiel.getTauxTva();

            totalMaterielCout += coutMateriel;
        }

        Map<String, Double> result = new HashMap<>();
        result.put("totalMaterielCout", totalMaterielCout);

        return result;
    }

    @Override
    public Map<String, Double> getAllPersonnels(Long projectId) {
        List<Personnel> personnels = composantRepository.getAllPersonnels(projectId);
        double totalPersonnelCout = 0;

        for (Personnel personnel : personnels) {
            double coutPersonnel = (personnel.getTauxHoraire() * personnel.getHeuresTravail() * personnel.getProductiviteOuvrier() * personnel.getTauxTva());

            totalPersonnelCout += coutPersonnel;
        }

        Map<String, Double> result = new HashMap<>();
        result.put("totalPersonnelCout", totalPersonnelCout);
        return result;
    }


}
