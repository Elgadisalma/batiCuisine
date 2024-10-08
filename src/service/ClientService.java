package service;

import entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Long createClient(Client client);
    void deleteClient(Long id);
    Optional<Client> getClientById(Long id);
    List<Client> getAllClients();
    void updateClient(Client client, Long id);
}
