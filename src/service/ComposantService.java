package service;

import entity.Materiel;
import entity.Personnel;

public interface ComposantService {
    void saveMateriel(Materiel materiel);
    void savePersonnel(Personnel personnel);
}
