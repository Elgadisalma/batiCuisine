package service;

import entity.Client;

import java.util.Optional;

public interface ClientService {
    void createClient(Client client);
    void deleteClient(Long id);
    Optional<Client> getClientById(Long id);
}
