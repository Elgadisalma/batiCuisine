package ui;

import entity.Materiel;
import entity.TypeComposant;
import service.ComposantService;

import java.util.Scanner;

public class ComposantUi {
    Scanner scanner = new Scanner(System.in);
    private final ComposantService composantService;

    public ComposantUi(ComposantService composantService) {
        this.composantService = composantService;
    }
    public void menu(){
        System.out.println("Menu composant");
        System.out.println("1- Voulez-vous ajouter des materiaux");
        System.out.println("2- Voulez-vous ajouter des main-d'oeuvre");
        System.out.println("3- Exit");

        while (true){
            int choix = 0;
            choix = scanner.nextInt();
            switch(choix){
                case 1:
                    ajoutMateriel();

                    while (true) {
                        System.out.println("Voulez-vous ajouter un autre matériel ? (y = 1) / (n = 0) :");
                        int choice = scanner.nextInt();

                        if (choice == 1) {
                            ajoutMateriel();
                        } else if (choice == 0) {
                            menu();
                        } else {
                            System.out.println("Choix non valide, veuillez entrer 1 ou 0.");
                        }
                    }
                case 2:
                    ajoutPersonnel();
                    break;
                case 3:
                    break;
            }
        }
    }

    public void ajoutMateriel() {
        System.out.println("Entrez le nom du matériau : ");
        String nom = scanner.next();

        System.out.println("Taux TVA du materiel:");
        Double tauxTva = scanner.nextDouble();

        System.out.println("Entrez le coût de transport de ce matériau (€) : ");
        Double coutUnitaire = scanner.nextDouble();

        System.out.println("Entrez la quantité de ce matériau (en m²) :");
        Double quantite = scanner.nextDouble();

        System.out.println("Entrez le coût unitaire de ce matériau (€/m²) :");
        Double coutTransport = scanner.nextDouble();

        System.out.println("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.1 = haute qualité)");
        Double coefficientQualite = scanner.nextDouble();

        TypeComposant typeComposant = TypeComposant.materiel;

        Materiel materiel = new Materiel(nom, typeComposant, tauxTva, coutUnitaire, quantite, coutTransport, coefficientQualite);

        composantService.saveMateriel(materiel);
    }

    public void ajoutPersonnel(){

    }


}
