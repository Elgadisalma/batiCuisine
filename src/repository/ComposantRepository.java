package repository;

import entity.Materiel;

import java.util.Optional;

public interface ComposantRepository {
    void save(Materiel materiel);
    Optional<Materiel> findByName(int id);
}
