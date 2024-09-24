import repository.impl.ClientRepositoryImpl;
import repository.impl.ComposantRepositoryImpl;
import repository.impl.DevisRepositoryImpl;
import repository.impl.ProjectRepositoryImpl;
import service.impl.ClientServiceImpl;
import service.impl.ComposantServiceImpl;
import service.impl.DevisServiceImpl;
import service.impl.ProjectServiceImpl;
import ui.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // clients
        ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
        ClientServiceImpl clientService = new ClientServiceImpl(clientRepository);
        ClientUi clientUi = new ClientUi(clientService);

        // Projects
        ProjectRepositoryImpl projectRepository = new ProjectRepositoryImpl();
        ProjectServiceImpl projectService = new ProjectServiceImpl(projectRepository, clientRepository);
        ProjectUi projectUi = new ProjectUi(clientService, projectService, clientUi);

        // Composants
        ComposantRepositoryImpl composantRepository = new ComposantRepositoryImpl();
        ComposantServiceImpl composantService = new ComposantServiceImpl(composantRepository);
        ComposantUi composantUi = new ComposantUi(composantService);

        // Devis et Calculs
        DevisRepositoryImpl devisRepository = new DevisRepositoryImpl();
        DevisServiceImpl devisService = new DevisServiceImpl(devisRepository);
        DevisUi devisUi = new DevisUi(projectService, devisService);
        CalculUi calculUi = new CalculUi(projectService, composantService, projectUi, devisUi);

        mainMenu(clientUi, projectUi, composantUi, calculUi);

        }

    public static void mainMenu(ClientUi clientUi, ProjectUi projectUi, ComposantUi composantUi, CalculUi calculUi) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(" \n=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===");
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Gestion des Clients");
            System.out.println("2. Gestion des Projets");
            System.out.println("3. Gestion des Composants");
            System.out.println("4. Calcul des Coûts et Devis");
            System.out.println("5. Quitter");
            System.out.println("Veuillez choisir une option :");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    clientUi.menu();
                    break;
                case 2:
                    projectUi.menu();
                    break;
                case 3:
                    composantUi.menu();
                    break;
                case 4:
                    calculUi.menu();
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    System.exit(0);
                default:
                    System.out.println("Option invalide");
            }
        }

    }
}