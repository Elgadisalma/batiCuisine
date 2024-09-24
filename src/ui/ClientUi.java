package ui;

import entity.Client;
import repository.impl.ClientRepositoryImpl;
import service.ClientService;
import service.impl.ClientServiceImpl;
import utils.InputValidator;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientUi {
    private final Scanner scanner = new Scanner(System.in);
    private final ClientService clientService;

    public ClientUi(ClientService clientService) {
        this.clientService = clientService;
    }

    public void menu() {
        while (true) {
            System.out.println("\n1. Ajouter un nouveau client");
            System.out.println("2. Chercher un client existant");
            System.out.println("3. Modifier un client");
            System.out.println("4. Supprimer un client");
            System.out.println("5. Afficher tous les clients");
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
                    return;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    public void createClient() {
        System.out.println("Ajouter un client");
        System.out.println("Veullez entrer le nom du client");
        String name = scanner.nextLine();
        while (!InputValidator.validateString(name)) {
            System.out.println("Erreur : Nom incorrect. Veuillez réessayer.");
            name = scanner.next();
        }

        System.out.println("Veuillez entrer l'adresse du client'");
        String adresse = scanner.nextLine();
        while (!InputValidator.validateString(adresse)) {
            System.out.println("Erreur : Adreesee incorrect. Veuillez réessayer.");
            adresse = scanner.next();
        }

        System.out.println("Veuillez entrer le phonenUmber du client");
        String phoneNumber = scanner.nextLine();
        while (!InputValidator.validateString(phoneNumber)) {
            System.out.println("Erreur : Numero de telephone incorrect. Veuillez réessayer.");
            phoneNumber = scanner.next();
        }

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
    }

    private void showAllClients() {
        List<Client> clients = clientService.getAllClients();
        System.out.println("liste des clients");
        for (Client client : clients) {
            System.out.println(client);
        }
        menu();
    }

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

    private void updateClient() {
        System.out.println("Modifier un client");
        System.out.println("veuillez entrer l'id du client");
        Long id = scanner.nextLong();
        scanner.nextLine();


        Optional<Client> optionalClient = clientService.getClientById(id);

        if (optionalClient.isPresent()) {
            System.out.println("Veullez entrer le nouveau nom");
            String name = scanner.nextLine();
            while (!InputValidator.validateString(name)) {
                System.out.println("Erreur : Nom non valide. Veuillez réessayer.");
                name = scanner.next();
            }

            System.out.println("Veuillez entrer la nouvelle adresse'");
            String adresse = scanner.nextLine();
            while (!InputValidator.validateString(adresse)) {
                System.out.println("Erreur : Adreesee incorrect. Veuillez réessayer.");
                adresse = scanner.next();
            }

            System.out.println("Veuillez entrer le nouveau phonenUmber");
            String phoneNumber = scanner.nextLine();
            while (!InputValidator.validateString(phoneNumber)) {
                System.out.println("Erreur : Numero de telephone incorrect. Veuillez réessayer.");
                phoneNumber = scanner.next();
            }

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
            clientService.updateClient(client, id);

        } else {
            System.out.println("Client non trouvé avec l'id " + id);
        }
    }

    private void deleteClient() {
        System.out.println("\nVeuillez entrer le id du client a supprimer");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Optional<Client> optionalClient = clientService.getClientById(id);

        if (optionalClient.isPresent()) {
        clientService.deleteClient(id);
        } else {
            System.out.println("Client non trouvé avec l'id " + id);
        }
    }

}
