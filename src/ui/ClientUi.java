package ui;

import entity.Client;
import service.ClientService;
import service.impl.ClientServiceImpl;

import java.util.Optional;
import java.util.Scanner;

public class ClientUi {
    private final ClientService clientService = new ClientServiceImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n1. Create Client");
            System.out.println("2. Show one Client");
            System.out.println("3. Update Client");
            System.out.println("4. Delete Client");
            System.out.println("5. Show all Clients");
            System.out.println("6. Exit");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    createClient();
                    break;
                case 2:
                    showOneClient();
                    break;
                case 3:
                    updateClient();
                    break;
                case 4:
                    deleteClient();
                    break;
                case 5:
                    showAllClients();
                    break;
                case 6:
                    break;
            }
        }
    }

    private void createClient() {
        System.out.println("Ajouter un client");
        System.out.println("Veullez entrer le nom du client");
        String name = scanner.nextLine();

        System.out.println("Veuillez entrer l'adresse du client'");
        String adresse = scanner.nextLine();

        System.out.println("Veuillez entrer le phonenUmber du client");
        String phoneNumber = scanner.nextLine();

        System.out.println("Le client est-il professionnel (1 : oui, 0 : non) ? ");
        int estProf = scanner.nextInt();
        scanner.nextLine();

        boolean professionnel;
        if (estProf == 1) {
            professionnel = true;
        } else if (estProf == 0) {
            professionnel = false;
        } else {
            System.out.println("Erreur: choix de disponibilité invalide.");
            return;
        }

        Client client = new Client(name, adresse, phoneNumber, professionnel);
        clientService.createClient(client);
        menu();
    }

    private void showAllClients() {}

    private void showOneClient() {
        System.out.println("Veuillez entrer l'id du client");
        Long id = scanner.nextLong();
        scanner.nextLine();

        try {
            Optional<Client> optionalClient = clientService.getClientById(id);

            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                System.out.println(client);
            } else {
                System.out.println("Client non trouvé avec l'id " + id);
            }
        } catch (RuntimeException e) {
            System.out.println("Erreur lors de la récupération du client : " + e.getMessage());
        }
    }


    private void updateClient() {}

    private void deleteClient() {
        System.out.println("\nVeuillez entrer le id du client a supprimer");
        Long id = scanner.nextLong();
        scanner.nextLine();

        clientService.deleteClient(id);
        menu();
    }

}
