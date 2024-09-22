package entity;

public class Composant {
    private Long id;
    private String nom;
    private TypeComposant typeComposant;
    private Double tauxTva;
    private Long projectId;

    public Composant(String nom, TypeComposant typeComposant, double tauxTva, Long projectId) {
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.tauxTva = tauxTva;
        this.projectId = projectId;
    }

    public Composant(Long id, String nom, TypeComposant typeComposant, double tauxTva, Long projectId) {
        this.id = id;
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.tauxTva = tauxTva;
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeComposant getTypeComposant() {
        return typeComposant;
    }
    public void setTypeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }

    public double getTauxTva() {
        return tauxTva;
    }
    public void setTauxTva(double tauxTva) {
        this.tauxTva = tauxTva;
    }

    public Long getProjectId() {
        return projectId;
    }
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Composant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", typeComposant=" + typeComposant +
                ", tauxTva=" + tauxTva +
                ", projectId=" + projectId +
                '}';
    }
}
