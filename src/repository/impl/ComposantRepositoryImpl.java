package repository.impl;

import config.DatabaseConnection;
import entity.Composant;
import entity.Materiel;
import entity.Personnel;
import repository.ComposantRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ComposantRepositoryImpl implements ComposantRepository {
    private Connection connection;

    public ComposantRepositoryImpl() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveMateriel(Materiel materiel) {
        final String query = "INSERT INTO materiel (nom, type_composant, taux_tva, projet_id ,cout_unitaire, quantite, cout_transport, coefficient_qualite) VALUES (?, ?::typecomposant, ?, ?, ?, ?, ?,?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int count = 1;

            statement.setString(count++, materiel.getNom());
            statement.setString(count++, materiel.getTypeComposant().toString());
            statement.setDouble(count++, materiel.getTauxTva());
            statement.setLong(count++, materiel.getProjectId());
            statement.setDouble(count++, materiel.getCoutUnitaire());
            statement.setDouble(count++, materiel.getQuantite());
            statement.setDouble(count++, materiel.getCoutTransport());
            statement.setDouble(count++, materiel.getCoefficientQualite());

            int executed = statement.executeUpdate();

            if (executed == 0) {
                throw new RuntimeException("Erreur de sauvegarde du matériel");
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la sauvegarde du matériel : " + e.getMessage());
        }
    }


    @Override
    public Optional<Materiel> findByName(int id) {
        return Optional.empty();
    }

    @Override
    public void savePersonnel(Personnel personnel) {
        final String query = "INSERT INTO personnel (nom, type_composant, taux_tva, projet_id, taux_horaire, heures_travail, productivite_ouvrier) VALUES(?,?::typecomposant,?,?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            int count = 1;

            statement.setString(count++,personnel.getNom());
            statement.setString(count++,personnel.getTypeComposant().toString());
            statement.setDouble(count++,personnel.getTauxTva());
            statement.setLong(count++, personnel.getProjectId());
            statement.setDouble(count++,personnel.getTauxHoraire());
            statement.setDouble(count++,personnel.getHeuresTravail());
            statement.setDouble(count++,personnel.getProductiviteOuvrier());

            int executed = statement.executeUpdate();
            if (executed == 0) {
                throw new RuntimeException("Erreur de sauvegarde du personnel");
            }
        } catch (SQLException e) {
            System.out.println("Erreur de sauvegarde du personnel : " + e.getMessage());
        }
    }


    @Override
    public Optional<Boolean> checkProjectExists(Long projectId) {
        final String query = "SELECT EXISTS (SELECT 1 FROM projets WHERE id = ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, projectId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(resultSet.getBoolean(1));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'existence du projet : " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Map<String, Double> getAllTaxes(Long projectId) {
        final String query = "SELECT nom, taux_tva FROM composant WHERE projet_id = ?";
        Map<String, Double> taxes = new HashMap<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, projectId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String composantName = rs.getString("nom");
                Double tauxTVA = rs.getDouble("taux_tva");

                taxes.put(composantName, tauxTVA);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des taxes : " + e.getMessage(), e);
        }

        return taxes;
    }



}
