package service.impl;

import entity.Client;
import repository.ClientRepository;
import repository.impl.ClientRepositoryImpl;
import service.ClientService;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Long createClient(Client client) {
        return clientRepository.addClient(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteClient(id);
        System.out.println("Client deleted successfully");
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepository.getClient(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.getClients();
    }

    @Override
    public void updateClient(Client client, Long id) {
        Optional<Client> existingClient = clientRepository.getClient(id);

        if (existingClient.isPresent()) {
            client.setId(id);
            clientRepository.updateClient(client);
            System.out.println("Client updated successfully");
        } else {
            System.out.println("Client not found.");
        }
    }
}
