package service;

import entity.Client;

public interface ClientService {
    void createClient(Client client);
    void deleteClient(Long id);
}
