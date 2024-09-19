package entity;

public class Project {
    private Long id;
    private String name;
    private double margeBeneficiaire;
    private Double coutTotal;
    private EtatProjet etatProjet;
    private Long clientId;

    public Project(String name, double margeBeneficiaire, Double coutTotal, EtatProjet etatProjet, Long clientId) {
        this.name = name;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.clientId = clientId;
    }

    public Project(Long id, String name, double margeBeneficiaire, Double coutTotal, EtatProjet etatProjet, Long clientId) {
        this.id = id;
        this.name = name;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.clientId = clientId;
    }

    public Project(Long id, Double margeBeneficiaire, Double coutTotal) {
        this.id = id;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }
    public void setMargeBeneficiaire(double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public Double getCoutTotal() {
        return coutTotal;
    }
    public void setCoutTotal(Double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public EtatProjet getEtatProjet() {
        return etatProjet;
    }
    public void setEtatProjet(EtatProjet etatProjet) {
        this.etatProjet = etatProjet;
    }

    public Long getClientId() {
        return clientId;
    }
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", margeBeneficiaire=" + margeBeneficiaire +
                ", coutTotal=" + coutTotal +
                ", etatProjet=" + etatProjet +
                ", clientId=" + clientId +
                '}';
    }
}
