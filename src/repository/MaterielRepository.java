package repository;

import entity.Materiel;

import java.util.Optional;

public interface MaterielRepository {
    void save(Materiel materiel);
    Optional<Materiel> findByName(int id);
}
