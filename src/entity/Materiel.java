package entity;

public class Materiel extends Composant {
    private double coutUnitaire;
    private double quantite;
    private Double coutTransport;
    private Double coefficientQualite;

    public Materiel(String nom, TypeComposant typeComposant, double tauxTva, double coutUnitaire, double quantite, Double coutTransport, Double coefficientQualite) {
        super(nom, typeComposant, tauxTva);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public Materiel(Long id, String nom, TypeComposant typeComposant, double tauxTva, double coutUnitaire, double quantite, Double coutTransport, Double coefficientQualite) {
        super(id, nom, typeComposant, tauxTva);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public double getCoutUnitaire() {
        return coutUnitaire;
    }
    public void setCoutUnitaire(double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Double getCoutTransport() {
        return coutTransport;
    }
    public void setCoutTransport(Double coutTransport) {
        this.coutTransport = coutTransport;
    }

    public Double getCoefficientQualite() {
        return coefficientQualite;
    }
    public void setCoefficientQualite(Double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }

    @Override
    public String toString() {
        return "Materiel{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", typeComposant=" + getTypeComposant() +
                ", tauxTva=" + getTauxTva() +
                ", coutUnitaire=" + coutUnitaire +
                ", quantite=" + quantite +
                ", coutTransport=" + coutTransport +
                ", coefficientQualite=" + coefficientQualite +
                '}';
    }
}
