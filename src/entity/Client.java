package entity;

public class Client {
    private Long id;
    private String name;
    private String adresse;
    private String phoneNumber;
    private boolean professionnel;

    public Client(String name, String adresse, String phoneNumber, boolean professionnel) {
        this.name = name;
        this.adresse = adresse;
        this.phoneNumber = phoneNumber;
        this.professionnel = professionnel;
    }

    public Client(Long id, String name, String adresse, String phoneNumber, boolean professionnel) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.phoneNumber = phoneNumber;
        this.professionnel = professionnel;
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

    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isProfessionnel() {
        return professionnel;
    }
    public void setProfessionnel(boolean professionnel) {
        this.professionnel = professionnel;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adresse='" + adresse + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", professionnel=" + professionnel +
                '}';
    }
}