package dambi.nobelprizes.model;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Klase honetan sarien errepositorioaren funtzioak zehazten dira. Funtzio hauek
 * beharrezkoak diren eragiketak izango lirateke (CRUD ereduaren eragiketak). 
 * Funtzio hauek geroago Mongo errepositorioan implementatuko dira eta kontroladoretik
 * ere deituko dira. Azkenik, klase hau interfaze moduan eraikitzen da geroago 
 * beste klaseetan implementatu ahal izateko.
 * 
 * @author Julen Herrero
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
