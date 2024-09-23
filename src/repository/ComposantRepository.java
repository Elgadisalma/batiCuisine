package repository;

import entity.Materiel;
import entity.Personnel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ComposantRepository {
    void saveMateriel(Materiel materiel);
    void savePersonnel(Personnel personnel);
    Optional<Boolean> checkProjectExists(Long projectId);
    Map<String,Double> getAllTaxes(Long projectId);
    List<Materiel> getAllMateriels(Long projectId);
    List<Personnel> getAllPersonnels(Long projectId);
}
