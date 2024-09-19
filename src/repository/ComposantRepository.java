package repository;

import entity.Materiel;
import entity.Personnel;

import java.util.Optional;

public interface ComposantRepository {
    void saveMateriel(Materiel materiel);
    Optional<Materiel> findByName(int id);
    void savePersonnel(Personnel personnel);
}
