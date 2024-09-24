package ui;

import com.sun.tools.javac.Main;
import entity.Client;
import entity.EtatProjet;
import entity.Project;
import service.ClientService;
import service.ProjectService;
import utils.InputValidator;


import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class ProjectUi {
    Scanner scanner = new Scanner(System.in);
    private final ClientService clientService;
    private final ProjectService projectService;
    private final ClientUi clientUi;

    public ProjectUi(ClientService clientService, ProjectService projectService, ClientUi clientUi) {
        this.clientService = clientService;
        this.projectService = projectService;
        this.clientUi = clientUi;
    }


    public void menu() {
        while (true) {
            System.out.println("\n1. Ajouter un nouveau projet");
            System.out.println("2. Chercher un projet existant");
            System.out.println("3. Modifier la marge d'un project");
            System.out.println("4. Exit");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    createProject();
                    break;
                case 2:
                    showOneProject();
                    break;
                case 3:
                    editProject();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }


    private void showOneProject() {
        System.out.println("Veuillez entrer l'ID du projet :");
        Long projectId = scanner.nextLong();
        scanner.nextLine();

        Optional<Project> project = projectService.getProject(projectId);
        if (project.isPresent()) {
            Project proj = project.get();
            System.out.println(proj);
        } else {
            System.out.println("Projet n'existe pas");
        }
    }

    private void createProject() {
        System.out.println("\n--- Création d'un Nouveau Projet---");

        System.out.println("\nLe client existe-t-il déjà ? (1: Oui, 0: Non)");
        int choix = scanner.nextInt();
        scanner.nextLine();

        Client client = null;
        Long clientId = null;  // This will store the client ID

        switch (choix) {
            case 1:
                System.out.println("Veuillez entrer l'ID du client:");
                clientId = scanner.nextLong();
                scanner.nextLine();

                Optional<Client> existingClient = clientService.getClientById(clientId);

                if (existingClient.isPresent()) {
                    client = existingClient.get();
                } else {
                    System.out.println("Client non existant");
                    return;
                }
                break;

            case 0:
                System.out.println("\nVeuillez entrer le nom du client:");
                String clientName = scanner.nextLine();
                System.out.println("\nVeuillez entrer l'adresse du client:");
                String clientAdresse = scanner.nextLine();
                System.out.println("\nVeuillez entrer le numéro de téléphone du client:");
                String clientPhoneNumber = scanner.nextLine();
                System.out.println("\nLe client est-il professionnel ? (1: Oui, 0: Non)");
                boolean isProfessionnel = scanner.nextInt() == 1;
                scanner.nextLine();

                client = new Client(clientName, clientAdresse, clientPhoneNumber, isProfessionnel);
                clientId = clientService.createClient(client);

                if (clientId == null) {
                    System.out.println("Erreur lors de la création du client.");
                    return;
                }

                Optional<Client> newClient = clientService.getClientById(clientId);
                if (newClient.isPresent()) {
                    client = newClient.get();
                } else {
                    System.out.println("Erreur lors de la récupération du client nouvellement créé.");
                    return;
                }
                break;

            default:
                System.out.println("Erreur: Choix non valide.");
                return;
        }

        System.out.println("\nVeuillez entrer le nom du projet:");
        String name = scanner.nextLine();
        while (!InputValidator.validateString(name)) {
            System.out.println("Erreur : Nom du projet non valide. Veuillez réessayer.");
            name = scanner.next();
        }

        System.out.println("\nVeuillez entrer la marge bénéficiaire du projet:");
        String margeBeneficiaireInput = scanner.next();
        while (!InputValidator.validateDouble(margeBeneficiaireInput)) {
            System.out.println("Erreur : Marge non valide. Veuillez réessayer.");
            margeBeneficiaireInput = scanner.next();
        }
        Double margeBeneficiaire = Double.parseDouble(margeBeneficiaireInput);

        EtatProjet etatProjet = EtatProjet.en_cours;

        Project project = new Project(name, margeBeneficiaire, 0.0, etatProjet, clientId);

        projectService.createProject(project, client);

        System.out.println("Projet créé avec succès.");
    }


    public void editProject() {
        System.out.println("Veuillez entrer l'ID du projet à modifier:");
        Long projectId = scanner.nextLong();
        scanner.nextLine();

        Optional<Project> projectOptional = projectService.getProject(projectId);

        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();

            System.out.println("\nVeuillez entrer la marge bénéficiaire du projet:");
            String margeBeneficiaireInput = scanner.next();
            while (!InputValidator.validateDouble(margeBeneficiaireInput)) {
                System.out.println("Erreur : Marge non valide non valide. Veuillez réessayer.");
                margeBeneficiaireInput = scanner.next();
            }
            Double margeBeneficiaire = Double.parseDouble(margeBeneficiaireInput);

            project.setMargeBeneficiaire(margeBeneficiaire);

            projectService.updateProject(project);
            System.out.println("Le projet a été mis à jour avec succès.");
        } else {
            System.out.println("Projet non trouvé.");
        }
    }



}
