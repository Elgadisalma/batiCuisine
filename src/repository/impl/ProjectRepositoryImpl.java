package repository.impl;

import config.DatabaseConnection;
import entity.Project;
import repository.ProjectRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProjectRepositoryImpl implements ProjectRepository {
    private Connection connection;
    private final String tableName = "projets";


    public ProjectRepositoryImpl() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error en connection", e);
        }
    }

    @Override
    public void saveProject(Project project) {
        final String query = "INSERT INTO " + tableName + " (name, marge_beneficiaire, cout_total, etat_projet, client_id) VALUES (?, ?, ?, ?::etatprojet, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int count = 1;

            statement.setString(count++, project.getName());
            statement.setDouble(count++, project.getMargeBeneficiaire());
            statement.setDouble(count++, project.getCoutTotal());
            statement.setString(count++, project.getEtatProjet().toString());

            statement.setLong(count++, project.getClientId());

            // Exécute la requête
            int executed = statement.executeUpdate();

            if (executed == 0) {
                throw new RuntimeException("Erreur de sauvegarde : Aucun enregistrement ajouté.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la sauvegarde du projet : " + e.getMessage());
        }
    }



    @Override
    public Optional<Project> getProject(Long id) {
        return Optional.empty();
    }


    @Override
    public List<Project> getAllProjects() {
        return List.of();
    }
}
