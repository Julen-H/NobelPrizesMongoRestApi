package dambi.nobelprizes.model;

/**
 * Klase hau Laureate motatako objektuaren klasea da. Laureate edo saritua sari
 * baten oinarrizko elementu
 * bat da. Hala ere sari batek saritu bat baino gehiago izan dezake, beraz sari
 * baten laureates atributua
 * Laureates motatako objektuen lista bat izango litzateke.
 * 
 * Saritu bakoitzak bost atributu ditu, sarituaren identifikadorea, izena eta
 * abizena, sari hori ematearen
 * motibazioa edo arrazoia eta share (beste zenbat sariturekin partekatu duen
 * ohorea). Guztiak String
 * motatakoak dira.
 * 
 * @author Julen Herrero
 */
public class Laureate {

    private String id;
    private String firstname;
    private String surname;
    private String motivation;
    private String share;

    /**
     * Laureates motatako objektuaren berezko eraikitzailea. Mongo-rekin lan egitean
     * derrigorrezkoa da
     * eraikitzaile huts bat edo defektuzkoa edukitzea, bestela ezin du era egokian
     * mapeatu klasea.
     */
    public Laureate() {
    }

    /**
     * Laureates motatako objektuaren eraikitzailea. Aurretik aipatutako moduan
     * boist String ditu, bakoitza
     * atributu bat izanik. Eraikitzaile hau beharrezkoa da zenbait funtziotan
     * objektuaren prozesamendua
     * egiteko.
     * 
     * @param id Sarituaren identifikadorea String formatuan
     * @param firstname Sarituaren izena String formatuan
     * @param surname Sarituaren abizena String formatuan
     * @param motivation Saria ematearen arrazoia edo sarituaren kontribuzioa String formatuan 
     * @param share Sarituak beste zenbat sariturekin partekatu duen saria String formatuan
     */
    public Laureate(String id, String firstname, String surname, String motivation, String share) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.motivation = motivation;
        this.share = share;
    }

    /**
     * Sarituaren identifikadorearen getter-a da funtzio hau. Honek String motako
     * atributu bat bueltatzen
     * du; kasu honetan sarituaren identifikadorea.
     * 
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Sarituaren identifikadorearen setter-a da funtzio hau. Honek String motako
     * parametro bat
     * jasotzen du eta jaso duen identifikadorea erreferentzia egiten dion
     * objektuaren
     * identifikadorea izatera pasatzen dela zehazten dio.
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sarituaren izenaren getter-a da funtzio hau. Honek String motako
     * parametro bat bueltatzen du; sarituaren izena.
     * 
     * @return String
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sarituaren abizenaren getter-a da funtzio hau. Honek String motako
     * parametro bat jasotzen du eta jaso duen parametroa erreferentzia egiten dion
     * objektuaren
     * izena izatera pasatzen dela zehazten dio.
     * 
     * @param firstname
     */
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
