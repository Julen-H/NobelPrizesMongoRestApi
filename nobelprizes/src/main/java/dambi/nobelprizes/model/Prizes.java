package dambi.nobelprizes.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "prizes")
public class Prizes {
    
    private List<Prize> prizes;

    /*public Prizes(List<Prize> prizes) {
        this.prizes = prizes;
    }*/

    public List<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
    }

    @Override
    public String toString() {
        return "Prizes [prizes=" + prizes + "]";
    }

}
