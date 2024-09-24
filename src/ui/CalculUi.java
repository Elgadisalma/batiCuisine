package ui;
import entity.Composant;
import entity.Project;
import service.ComposantService;
import service.ProjectService;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class CalculUi {
    Scanner scanner = new Scanner(System.in);
    private final ProjectService projectService;
    private final ComposantService composantService;
    private final ProjectUi projectUi;
    private final DevisUi devisUi;

    public CalculUi(ProjectService projectService, ComposantService composantService, ProjectUi projectUi, DevisUi devisUi) {
        this.projectService = projectService;
        this.composantService = composantService;
        this.projectUi = projectUi;
        this.devisUi = devisUi;
    }

    public void menu() {
        while (true) {
            System.out.println("\n1. Afficher toutes les taxes des composant");
            System.out.println("2. Calcul Total des cout");
            System.out.println("3. Exit");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    getAllTaxes();
                    break;
                case 2:
                    calculTotal();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void calculTotal() {
        System.out.println("Veuillez entrer l'ID du projet");
        Long projectId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Voulez vous modifier la marge beneficiaire de ce projet (1- Oui , 0- Non)");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1){
            projectUi.editProject();
        }

        Optional<Project> project = projectService.getProject((long) projectId);

        if (project.isPresent()) {
            Map<String, Double> totalMateriel = composantService.getAllMateriels(projectId);
            Map<String, Double> totalPersonnel = composantService.getAllPersonnels(projectId);
            Double totalMateriels = totalMateriel.get("totalMaterielCout");
            Double totalPersonnels = totalPersonnel.get("totalPersonnelCout");

            Double totalComposant = totalMateriels + totalPersonnels;
            System.out.println("\nTotal des composants : " + totalComposant + "$\n");

            Double coutTotal = totalComposant + project.get().getMargeBeneficiaire();
            System.out.println("Total des couts : " + coutTotal + "$\n");

            devisUi.addToDevis(projectId, coutTotal);

        } else {
            System.out.println("Project not found");
        }


    }

    public void getAllTaxes() {
        System.out.println("Veuillez entrer l'ID du projet");
        Long projectId = scanner.nextLong();
        scanner.nextLine();

        Optional<Project> project = projectService.getProject((long) projectId);

        if (project.isPresent()) {
            System.out.println("\nToutes les taxes des composants de ce projet:");
            Map<String, Double> taxes = composantService.displayTaxes(projectId);
            if (taxes.isEmpty()) {
                System.out.println("Aucune taxe trouvée pour ce projet.");
            } else {
                taxes.forEach((composantName, tauxTVA) ->
                        System.out.println("Composant: " + composantName + ", Taux TVA: " + tauxTVA + "%"));
            }
        } else {
            System.out.println("Le projet n'existe pas");
        }
    }
    
    
}
