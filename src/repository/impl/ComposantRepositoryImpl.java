package repository.impl;

import config.DatabaseConnection;
import entity.Materiel;
import repository.ComposantRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class ComposantRepositoryImpl implements ComposantRepository {
    private Connection connection;
    private final String tableName = "materiel";

    public ComposantRepositoryImpl() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Materiel materiel) {
        final String query = "INSERT INTO " + tableName + " (nom, type_composant, taux_tva, cout_unitaire, quantite, cout_transport, coefficient_qualite) VALUES (?, ?::typecomposant, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int count = 1;

            statement.setString(count++, materiel.getNom());
            statement.setString(count++, materiel.getTypeComposant().toString());
            statement.setDouble(count++, materiel.getTauxTva());
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
}
