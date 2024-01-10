package dambi.nobelprizes.model;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 */
@Repository
public interface PrizeRepository {

    List<Prize> findAll();
    Prize findById(String id);
    Prize save(Prize prize);
    long delete(String id);

    @Query("{'year': ?0, 'category': ?1}")
    Prize findByCategoryYear(String year, String category);

    @Query("{'year': ?0, 'category': ?1}")
    long deleteByYear(String year, String category);
}
