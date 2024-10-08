package repository.impl;

import config.DatabaseConnection;
import entity.EtatProjet;
import entity.Project;
import repository.ProjectRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public void editProject(Project project) {
        final String query = "UPDATE " + tableName + " SET marge_beneficiaire = ? WHERE id = ?";
        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
            int count = 1;
            stmt.setDouble(count++, project.getMargeBeneficiaire());
            stmt.setLong(count++, project.getId());

            int executed = stmt.executeUpdate();
            if (executed == 0) {
                throw new SQLException("Aucun projet trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur :" + e.getMessage(), e);
        }
    }

    @Override
    public void annullateProject(Long id) {
        final String query = "UPDATE " + tableName + " SET etat_projet = ?::etatprojet WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, EtatProjet.annule.toString());
            statement.setLong(2, id);

            int executed = statement.executeUpdate();
            if (executed == 0) {
                throw new SQLException("Aucun projet trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'annulation du projet : " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Project> getProject(Long id) {
        final String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Project project = mapResultSetToProject(rs);
                return Optional.of(project);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération du projet : " + e.getMessage(), e);
        }
    }

    private Project mapResultSetToProject(ResultSet rs) throws SQLException {
        return new Project(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getDouble("marge_beneficiaire"),
                rs.getDouble("cout_total"),
                EtatProjet.valueOf(rs.getString("etat_projet")),  // Assurez-vous que les valeurs sont dans l'énumération EtatProjet
                rs.getLong("client_id")
        );
    }

}
