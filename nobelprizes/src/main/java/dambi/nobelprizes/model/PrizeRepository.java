package dambi.nobelprizes.model;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrizeRepository {

    List<Prize> findAll();
    //Laureate findById(String id); Lehen sarituak topatzea zen ideia baina ez du zentzurik
    //Prize findById(String year, String category); // _id eremua gehitzen badut asko errazten da lana
    Prize findById(String id);
    //Prize save(String year, String category, List<Laureate> laureates);
    Prize save(Prize prize);

    long delete(String id);

    @Query("{'year': ?0, 'category': ?1}")
    long deleteByYear(String year, String category);
}
