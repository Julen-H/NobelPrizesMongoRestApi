package dambi.nobelprizes.model;

public class Laureate {
    
    private String id;
    private String firstname;
    private String surname;
    private String motivation;
    private String share;

    public Laureate() {

    }

    public Laureate(String id, String firstname, String surname, String motivation, String share) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.motivation = motivation;
        this.share = share;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getMotivation() {
        return motivation;
    }
    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }
    public String getShare() {
        return share;
    }
    public void setShare(String share) {
        this.share = share;
    }

    @Override
    public String toString() {
        return "Laureate [id=" + id + ", firstname=" + firstname + ", surname=" + surname + ", motivation=" + motivation
                + ", share=" + share + "]";
    }
}
