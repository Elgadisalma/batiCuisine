package ui;

import entity.Devis;
import service.DevisService;
import service.ProjectService;

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

            LocalDate dateEmission = saisirDate("Entrez la date d'émission du devis (format : jj/mm/aaaa) :");
            LocalDate dateValidite = saisirDate("Entrez la date de validité du devis (format : jj/mm/aaaa) :");

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

    private LocalDate saisirDate(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;
        boolean isValid = false;

        while (!isValid) {
            System.out.println(message);
            String dateInput = scanner.nextLine();
            try {
                date = LocalDate.parse(dateInput, formatter);
                isValid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Format de date invalide, veuillez réessayer.");
            }
        }
        return date;
    }
}
