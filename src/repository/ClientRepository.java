package repository;

import entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    Long addClient(Client client);
    void deleteClient(Long id);
    Optional<Client> getClient(Long id);
    List<Client> getClients();
    void updateClient(Client client);
}
