package ui;

import entity.Devis;
import service.DevisService;
import service.ProjectService;
import utils.InputValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DevisUi {
    Scanner scanner = new Scanner(System.in);
    private final ProjectService projectService;
    private final DevisService devisService;

    public DevisUi(ProjectService projectService, DevisService devisService) {
        this.projectService = projectService;
        this.devisService = devisService;
    }

    public void addToDevis(Long projectId, Double coutTotal) {
        System.out.println("Voulez-vous ajouter ce calcul au devis ? (1- Oui, 0- Non)");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            System.out.println("Total du montant estimé : " + coutTotal + "$");

            LocalDate dateEmission = null;
            while (dateEmission == null) {
                System.out.println("Entrez la date d'émission du devis (format : jj/mm/aaaa) :");
                String dateEmissionInp = scanner.nextLine();
                dateEmission = InputValidator.parseDate(dateEmissionInp);
                if (dateEmission == null) {
                    System.out.println("Veuillez reessayer avec une date valide");
                }
            }

            LocalDate dateValidite = null;
            while (dateValidite == null) {
                System.out.println("Entrez la date de validité du devis (format : jj/mm/aaaa) :");
                String dateValiditeInp = scanner.nextLine();
                dateValidite = InputValidator.parseDate(dateValiditeInp);
                if (dateValidite == null) {
                    System.out.println("Veuillez reessayer avec une date valide");
                }
            }

            System.out.println("Voulez-vous valider ce devis ou annuler le projet ? (1- valider, 0- annuler)");
            int accepter = scanner.nextInt();
            scanner.nextLine();

            boolean isAccepte;
            switch (accepter) {
                case 1:
                    isAccepte = true;
                    break;
                case 0:
                    isAccepte = false;
                    projectService.annulerProjet(projectId);
                    break;
                default:
                    System.out.println("Option invalide.");
                    return;
            }

            Devis devis = new Devis(coutTotal, dateEmission, dateValidite, isAccepte, projectId);
            devisService.addDevis(devis);
            System.out.println("Devis added successfully");
        } else {
            System.out.println("Retour au menu principal...");
        }
    }


}
