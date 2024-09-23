package ui;

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
            System.out.println("\n1. Create Projet");
            System.out.println("2. Show one Project");
            System.out.println("3. Edit Project");
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
                    System.exit(0);
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
        System.out.println("Création d'un projet:");

        System.out.println("Le client existe-t-il déjà ? (1: Oui, 0: Non)");
        int choix = scanner.nextInt();
        scanner.nextLine();

        Client client = null;

        switch (choix) {
            case 1:
                System.out.println("Veuillez entrer l'ID du client:");
                Long clientId = scanner.nextLong();
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
                clientUi.createClient();
                break;

            default:
                System.out.println("Erreur: Choix non valide.");
                return;
        }

        System.out.println("\nVeuillez entrer le nom du projet:");
        String name = scanner.nextLine();
        while (!InputValidator.validateString(name)) {
            System.out.println("Erreur : Nom du personnel non valide. Veuillez réessayer.");
            name = scanner.next();
        }

        System.out.println("\nVeuillez entrer la marge bénéficiaire du projet:");
        String margeBeneficiaireInput = scanner.next();
        while (!InputValidator.validateDouble(margeBeneficiaireInput)) {
            System.out.println("Erreur : Marge non valide non valide. Veuillez réessayer.");
            margeBeneficiaireInput = scanner.next();
        }
        Double margeBeneficiaire = Double.parseDouble(margeBeneficiaireInput);

        EtatProjet etatProjet = EtatProjet.en_cours;

        Project project = new Project(name, margeBeneficiaire,0.0, etatProjet, client.getId());

        projectService.createProject(project, client);
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
