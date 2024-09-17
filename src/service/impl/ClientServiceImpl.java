package service.impl;

import entity.Client;
import repository.ClientRepository;
import repository.impl.ClientRepositoryImpl;
import service.ClientService;

import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl() {
        this.clientRepository = new ClientRepositoryImpl();
    }

    @Override
    public void createClient(Client client) {
        clientRepository.addClient(client);
        System.out.println("Client created successfully");
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
}
