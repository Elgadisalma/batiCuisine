package service;

import entity.Materiel;
import entity.Personnel;

import java.util.Optional;

public interface ComposantService {
    void saveMateriel(Materiel materiel);
    void savePersonnel(Personnel personnel);
    Optional<Boolean> checkProjectExists(Long projectId);

}
