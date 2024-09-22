package ui;

import entity.Client;
import entity.EtatProjet;
import entity.Project;
import service.ClientService;
import service.ProjectService;


import java.util.Optional;
import java.util.Scanner;

public class ProjectUi {
    Scanner scanner = new Scanner(System.in);
    private final ClientService clientService;
    private final ProjectService projectService;

    public ProjectUi(ClientService clientService, ProjectService projectService) {
        this.clientService = clientService;
        this.projectService = projectService;
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
        System.out.println("Veuillez entrer l'ID du projet à modifier:");
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
                    System.out.println("Erreur: Le client avec cet ID n'existe pas.");
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

                clientService.createClient(client);
                System.out.println("Nouveau client créé avec succès.");
                break;

            default:
                System.out.println("Erreur: Choix non valide.");
                return;
        }

        System.out.println("\nVeuillez entrer le nom du projet:");
        String name = scanner.nextLine();

        System.out.println("\nVeuillez entrer la marge bénéficiaire du projet:");
        Double margeBeneficiaire = scanner.nextDouble();

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

            System.out.println("Veuillez entrer la nouvelle marge bénéficiaire:");
            Double margeBeneficiaire = scanner.nextDouble();

            project.setMargeBeneficiaire(margeBeneficiaire);

            projectService.updateProject(project);
            System.out.println("Le projet a été mis à jour avec succès.");
        } else {
            System.out.println("Projet non trouvé.");
        }
    }



}
