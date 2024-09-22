package entity;

import java.time.LocalDate;
import java.util.Date;

public class Devis {
    private Long id;
    private double montantEstime;
    private LocalDate dateEmission;
    private LocalDate dateValidite;
    private boolean accepte;
    private Long projetId;

    public Devis(double montantEstime, LocalDate dateEmission, LocalDate dateValidite, boolean accepte, Long projetId) {
        this.montantEstime = montantEstime;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = accepte;
        this.projetId = projetId;
    }

    public Devis(Long id, double montantEstime, LocalDate dateEmission, LocalDate dateValidite, boolean accepte, Long projetId) {
        this.id = id;
        this.montantEstime = montantEstime;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = accepte;
        this.projetId = projetId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public double getMontantEstime() {
        return montantEstime;
    }
    public void setMontantEstime(double montantEstime) {
        this.montantEstime = montantEstime;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }
    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public LocalDate getDateValidite() {
        return dateValidite;
    }
    public void setDateValidite(LocalDate dateValidite) {
        this.dateValidite = dateValidite;
    }

    public boolean isAccepte() {
        return accepte;
    }
    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    public Long getProjetId() {
        return projetId;
    }
    public void setProjetId(Long projetId) {
        this.projetId = projetId;
    }

    @Override
    public String toString() {
        return "Devis{" +
                "id=" + id +
                ", montantEstime=" + montantEstime +
                ", dateEmission=" + dateEmission +
                ", dateValidite=" + dateValidite +
                ", accepte=" + accepte +
                ", projetId=" + projetId +
                '}';
    }

}
