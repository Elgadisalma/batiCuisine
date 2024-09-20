package service;

import entity.Composant;
import entity.Materiel;
import entity.Personnel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ComposantService {
    void saveMateriel(Materiel materiel);
    void savePersonnel(Personnel personnel);
    Optional<Boolean> checkProjectExists(Long projectId);
    Map<String,Double> displayTaxes(Long projectId);
    Map<String, Double> getAllMateriels(Long projectId);
    Map<String, Double> getAllPersonnels(Long projectId);
}
