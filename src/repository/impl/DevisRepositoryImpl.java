package repository.impl;

import config.DatabaseConnection;
import entity.Devis;
import repository.DevisRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DevisRepositoryImpl implements DevisRepository {
    private Connection connection;
    private final String tableName = "devis";

    public DevisRepositoryImpl() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error en connection", e);
        }
    }

    @Override
    public void ajouterDevis(Devis devis) {
        final String query = "INSERT INTO " + tableName + " (montant_estime, date_emission, date_validite, accepte, projet_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int count = 1;

            statement.setDouble(count++, devis.getMontantEstime());
            statement.setDate(count++, Date.valueOf(devis.getDateEmission()));
            statement.setDate(count++, Date.valueOf(devis.getDateValidite()));
            statement.setBoolean(count++, devis.isAccepte());
            statement.setLong(count++, devis.getProjetId());

            int executed = statement.executeUpdate();

            if (executed == 0) {
                throw new RuntimeException("Erreur d'ajout : Aucun devis n'a été inséré.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du devis : " + e.getMessage(), e);
        }
    }
}
