package repository.impl;

import config.DatabaseConnection;
import entity.Client;
import repository.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository{

    private Connection connection;
    private final String tableName = "clients";


    public ClientRepositoryImpl() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error en connection", e);
        }
    }

    @Override
    public void addClient(Client client) {
        final String query = "INSERT INTO " + tableName + " (name, adresse, phoneNumber, professionnel) VALUES (?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            int count = 1;
            statement.setString(count++, client.getName());
            statement.setString(count++, client.getAdresse());
            statement.setString(count++, client.getPhoneNumber());
            statement.setBoolean(count++, client.isProfessionnel());

            int executed = statement.executeUpdate();
            if (executed == 0) {
                throw new SQLException("Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error", e);
        }

    }

    @Override
    public void deleteClient(Long id) {
        final String query = "DELETE FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1, id);
            int executed = statement.executeUpdate();
            if (executed == 0) {
                throw new SQLException("Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Client> getClient(Long id) {
        final String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Client client = mapResultSetToClient(rs);
                return Optional.of(client);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public List<Client> getClients() {
//        final String query = "SELECT * FROM " + tableName;
//        final List<Client> clients = new ArrayList<>();
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            final ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Client client = mapResultSetToClient(rs);
//                clients.add(client);
//            }
//            return clients;
//        }
//        return List.of();
//    }
//
//    @Override
//    public void updateClient(Client client) {
//        final String query = "UPDATE " + tableName + " SET name = ?, adresse = ?, phoneNumber = ?, professionnel = ? WHERE id = ?";
//        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
//            int count = 1;
//            stmt.setString(count++, client.getName());
//            stmt.setString(count++, client.getAdresse());
//            stmt.setString(count++, client.getPhoneNumber());
//            stmt.setBoolean(count++, client.isProfessionnel());
//            stmt.setLong(count++, client.getId());
//
//            int executed = stmt.executeUpdate();
//            if (executed == 0) {
//                throw new SQLException("Failed");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    private Client mapResultSetToClient (ResultSet rs) throws SQLException {
        return new Client(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("adresse"),
                rs.getString("phoneNumber"),
                rs.getBoolean("professionnel")
        );
    }


}
