package entity;

public class Personnel extends Composant {
    private double tauxHoraire;
    private double heuresTravail;
    private Double productiviteOuvrier;

    public Personnel(String nom, TypeComposant typeComposant, double tauxTva, Long projectId, double tauxHoraire, double heuresTravail, Double productiviteOuvrier) {
        super(nom, typeComposant, tauxTva,projectId);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public Personnel(Long id, String nom, TypeComposant typeComposant, double tauxTva, Long projectId, double tauxHoraire, double heuresTravail, Double productiviteOuvrier) {
        super(id, nom, typeComposant, tauxTva, projectId);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }
    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public double getHeuresTravail() {
        return heuresTravail;
    }
    public void setHeuresTravail(double heuresTravail) {
        this.heuresTravail = heuresTravail;
    }

    public Double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }
    public void setProductiviteOuvrier(Double productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", typeComposant=" + getTypeComposant() +
                ", tauxTva=" + getTauxTva() +
                ", tauxHoraire=" + tauxHoraire +
                ", heuresTravail=" + heuresTravail +
                ", productiviteOuvrier=" + productiviteOuvrier +
                ", projectId=" + getProjectId() +
                '}';
    }
}
