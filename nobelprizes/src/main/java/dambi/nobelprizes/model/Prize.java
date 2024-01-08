package dambi.nobelprizes.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "prizes")
public class Prize {
    
    private String year;
    private String category;
    private List<Laureate> laureates;
    
    public Prize() {

    }
    
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
