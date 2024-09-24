package ui;

import entity.Materiel;
import entity.Personnel;
import entity.TypeComposant;
import service.ComposantService;
import utils.InputValidator;

import java.util.Scanner;

public class ComposantUi {
    Scanner scanner = new Scanner(System.in);
    private final ComposantService composantService;

    public ComposantUi(ComposantService composantService) {
        this.composantService = composantService;
    }

    public void menu() {
        System.out.println("Menu composant");
        System.out.println("1- Voulez-vous ajouter des materiaux");
        System.out.println("2- Voulez-vous ajouter des mains d'oeuvre");
        System.out.println("3- Exit");

        while (true) {
            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    ajoutMateriel();
                    break;
                case 2:
                    ajoutPersonnel();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Choix non valide");
            }
        }
    }

    public void ajoutMateriel() {
        System.out.println("Entrez l'ID du projet : ");
        Long projectId = scanner.nextLong();

        if (!composantService.checkProjectExists(projectId).orElse(false)) {
            System.out.println("Le projet n'existe pas");
            return;
        }

        System.out.println("Entrez le nom du materiel : ");
        String nom = scanner.next();
        while (!InputValidator.validateString(nom)) {
            System.out.println("Erreur : Nom du materiel non valide. Réessayer");
            nom = scanner.next();
        }

        System.out.println("Taux TVA du matériel :");
        String tauxTvaInput = scanner.next();
        while (!InputValidator.validateDouble(tauxTvaInput)) {
            System.out.println("Erreur : Taux TVA non valide. Veuillez réessayer.");
            tauxTvaInput = scanner.next();
        }
        Double tauxTva = Double.parseDouble(tauxTvaInput);

        System.out.println("Entrez le coût de transport de ce matériau (€) : ");
        String coutUnitaireInput = scanner.next();
        while (!InputValidator.validateDouble(coutUnitaireInput)) {
            System.out.println("Erreur : Coût unitaire non valide. Veuillez réessayer.");
            coutUnitaireInput = scanner.next();
        }
        Double coutUnitaire = Double.parseDouble(coutUnitaireInput);

        System.out.println("Entrez la quantité de ce matériau (en m²) :");
        String quantiteInput = scanner.next();
        while (!InputValidator.validateDouble(quantiteInput)) {
            System.out.println("Erreur : Quantité non valide. Veuillez réessayer.");
            quantiteInput = scanner.next();
        }
        Double quantite = Double.parseDouble(quantiteInput);

        System.out.println("Entrez le coût unitaire de ce matériau (€/m²) :");
        String coutTransportInput = scanner.next();
        while (!InputValidator.validateDouble(coutTransportInput)) {
            System.out.println("Erreur : Coût unitaire de transport non valide. Veuillez réessayer.");
            coutTransportInput = scanner.next();
        }
        Double coutTransport = Double.parseDouble(coutTransportInput);

        System.out.println("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.1 = haute qualité) :");
        String coefficientQualiteInput = scanner.next();
        while (!InputValidator.validateDouble(coefficientQualiteInput)) {
            System.out.println("Erreur : Coefficient de qualité non valide. Veuillez réessayer.");
            coefficientQualiteInput = scanner.next();
        }
        Double coefficientQualite = Double.parseDouble(coefficientQualiteInput);

        TypeComposant typeComposant = TypeComposant.materiel;

        Materiel materiel = new Materiel(nom, typeComposant, tauxTva, projectId, coutUnitaire, quantite, coutTransport, coefficientQualite);
        composantService.saveMateriel(materiel);
    }

    public void ajoutPersonnel() {
        System.out.println("Entrez l'ID du projet : ");
        Long projectId = scanner.nextLong();

        if (!composantService.checkProjectExists(projectId).orElse(false)) {
            System.out.println("Le projet n'existe pas");
            return;
        }

        System.out.println("Entrez le nom du personnel : ");
        String nom = scanner.next();
        while (!InputValidator.validateString(nom)) {
            System.out.println("Erreur : Nom du personnel non valide. Veuillez réessayer.");
            nom = scanner.next();
        }

        System.out.println("Taux TVA du personnel :");
        String tauxTvaInput = scanner.next();
        while (!InputValidator.validateDouble(tauxTvaInput)) {
            System.out.println("Erreur : Taux TVA non valide. Veuillez réessayer.");
            tauxTvaInput = scanner.next();
        }
        Double tauxTva = Double.parseDouble(tauxTvaInput);

        System.out.println("Entrez le taux horaire de cette main-d'œuvre : ");
        String tauxHoraireInput = scanner.next();
        while (!InputValidator.validateDouble(tauxHoraireInput)) {
            System.out.println("Erreur : Taux horaire non valide. Veuillez réessayer.");
            tauxHoraireInput = scanner.next();
        }
        Double tauxHoraire = Double.parseDouble(tauxHoraireInput);

        System.out.println("Entrez le nombre d'heures travaillées : ");
        String heuresTravailInput = scanner.next();
        while (!InputValidator.validateDouble(heuresTravailInput)) {
            System.out.println("Erreur : Nombre d'heures non valide. Veuillez réessayer.");
            heuresTravailInput = scanner.next();
        }
        Double heuresTravail = Double.parseDouble(heuresTravailInput);

        System.out.println("Entrez le facteur de productivité (1,0 = standard, > 1,1 = haute qualité) :");
        String productiviteOuvrierInput = scanner.next();
        while (!InputValidator.validateDouble(productiviteOuvrierInput)) {
            System.out.println("Erreur : Facteur de productivité non valide. Veuillez réessayer.");
            productiviteOuvrierInput = scanner.next();
        }
        Double productiviteOuvrier = Double.parseDouble(productiviteOuvrierInput);

        TypeComposant typeComposant = TypeComposant.personnel;

        Personnel personnel = new Personnel(nom, typeComposant, tauxTva, projectId, tauxHoraire, heuresTravail, productiviteOuvrier);
        composantService.savePersonnel(personnel);
    }
}
