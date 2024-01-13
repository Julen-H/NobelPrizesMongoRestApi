package dambi.nobelprizes.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Klase hau Prize objektuaren modeloa izango litzateke. Prize objektua datu basean 
 * dokumentuetan banatutako objektuak mapeatzeko erabiltzen da. Klaseak hiru atributu
 * ditu, sariaren urtea, sariaren kategoria eta sarituen lista. Klase honetan objektuaren
 * eraikitzaileak, getter-ak, setter-ak eta toString funtzioa aurkitu daitezke.
 * 
 * @author Julen Herrero
 */
@Document(collection = "prizes")
public class Prize {
    
    private String year;
    private String category;
    private List<Laureate> laureates;
    
    /**
     * Prize objektuaren berezko eraikitzailea. Eraikitzaile hau beharrezkoa da Mongo-k
     * behar bezala objektua eta datu basearen dokumentuak mapeatzeko.
     */
    public Prize() {

    }
    
    /**
     * Prize objektuaren eraikitzailea. Objektuak diruen atributuak zeintzuk diren zehazten dira.
     * Hauek hiru dira, sariaren urtea, kategoria eta sarituen lista. Eraikitzaile honen bitartez
     * zenbait eragiketa egin daitezke, Prize objektua sortu daiteke eta request-en bitartez Mongo
     * datu basera datuak igo daitezke.
     * 
     * @param year Sariaren urtea
     * @param category Sariaren kategoria
     * @param laureates Sarituen lista
     */
    public Prize(String year, String category, List<Laureate> laureates) {
        this.year = year;
        this.category = category;
        this.laureates = laureates;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Laureate> getLaureates() {
        return laureates;
    }

    public void setLaureates(List<Laureate> laureates) {
        this.laureates = laureates;
    }

    @Override
    public String toString() {
        return "Prize [year=" + year + ", category=" + category + ", laureates=" + laureates + "]";
    }

}
