package dambi.nobelprizes.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import jakarta.annotation.PostConstruct;

import static com.mongodb.client.model.Filters.eq;

/**
 * Klase hau web zerbitzuaren Mongo errepositorioa izango litzateke. Honek, Prize errepositoaren 
 * funtzioak implementatzen ditu interfaze bat delako lehen hau. Interfazean zehaztu diren funtzioak
 * era sakonago batean mapeatzen ditu eta hauek Mongo datu basearen kolekzioarekin zelan erlazionatzen
 * diren zehazten du.
 * 
 * Interfaze honen funtzioak implementatzeaz gain Mongoren bezeroa eta datu basearen kolekzioa ezartzen ditu
 * transakzioak era egokian aurrera eraman ahal izateko.
 * 
 * Garrantzitsua da zehaztea klase honetan aurkitzen diren funtzio guztiak PrizeRepository interfazetik 
 * implementatu direla.
 * 
 * @author Julen Herrero
 */
@Repository
public class MongoDBPrizeRepository implements PrizeRepository {
    
    /*private static final TransactionOptions txnOptions = TransactionOptions.builder()
                                                                           .readPreference(ReadPreference.primary())
                                                                           .readConcern(ReadConcern.MAJORITY)
                                                                           .writeConcern(WriteConcern.MAJORITY)
                                                                           .build();*/

    private final MongoTemplate mongoTemplate; 

    @Autowired
    private MongoClient client;
    private MongoCollection<Prize> prizeCollection;

    /**
     * MongoTemplate-aren eraikitzailea. MongoTemplate core paketeko klase bat da eta errore komunak ekiditeko
     * erabiltzen da. Gainera, klase honek aplikazioaren kodearen dokumentua eta emaitzak ateratzea ahalbidetzen
     * du. Criteriarekin erlazionatu da proiektuan request-ak egiteko garaian parametro anitzak pasatzeko, horretarko
     * Query anotazioa ere erabili da.
     * 
     * @param mongoTemplate MongoTemplate klaseko instantzia
     */
    @Autowired
    public MongoDBPrizeRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Funtzio honek Mongo datu basea zein den eta datu basean zein kolekzio aukeratu behar duen zehazten du.
     */
    @PostConstruct
    void init() {
        prizeCollection = client.getDatabase("nobelprize").getCollection("prizes", Prize.class);
    }

    /**
     * Funtzio honek sarien kolekzioan aurkitzen diren sari guztiak bilatzen ditu eta ArrayList batera 
     * pasatzen ditu. Find funtzioak Prize motako Iterableak hartzen ditu eta into metodoarekin ArrayList-era
     * bolkatzen ditu.
     * 
     * @return List<Prize> Sari guztien List bat
     */
    @Override
    public List<Prize> findAll() {
        return prizeCollection.find().into(new ArrayList<>());
    }

    /**
     * Funtzio honek sarien kolekzioan bilaketa bat egiten du, horretarako topatu nahi den sariaren
     * identifikadorea pasatzen da. Identifikadorea edukita sarien kolekzioan pasatako parametroarekin 
     * bat egiten duen saria topatu eta bueltatzen du. Eq importazio estatikoa erabilita find metodoari
     * filtroa pasatzen zaio, identifikadorea zehazki Mongo-k sariei ematen dio ObjectId-a da beraz 
     * filtroan zehaztu egiten da zein eremuri erreferentzia egiten dion.
     * 
     * @return Prize 
     */
    @Override
    public Prize findById(String id) {
        return prizeCollection.find(eq("_id", new ObjectId(id))).first();
    }

    /**
     * Funtzio honek sari berri bat kolekziora gehitzen du. Alde batetik gehitu nahi den saria pasatzen da
     * eta sari hori insertOne metodoaren bitartez kolekziora gehitzen da. Azkenik gehitu berri den saria
     * bueltatzen da.
     * 
     * @param prize Prize klaseko objektu bat
     * @return Prize Gehitu berri den Prize klaseko objektua
     */
    @Override
    public Prize save(Prize prize) {
        prizeCollection.insertOne(prize);
        return prize;
    }

    /**
     * Funtzio honek pasatako parametroekin bat egiten duen saria topatzen 
     * 
     * @param year Saria banatu zen urtea String formatuan
     * @param category Sariaren kategoria String formatuan
     * @return Prize Pasatako parametroeki bat egiten duen Prize klaseko objektua
     */
    @Override
    public Prize findByCategoryYear(String year, String category) {
        Criteria criteria = Criteria.where("year").is(year).and("category").is(category);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, Prize.class);
    }

    @Override
    public long deleteByYear(String year, String category) {
        Criteria criteria = Criteria.where("year").is(year).and("category").is(category);
        Query query = new Query(criteria);

        return mongoTemplate.remove(query, Prize.class).getDeletedCount();
    }

    @Override 
    public long delete(String _id) {
        return prizeCollection.deleteMany(eq("_id", new ObjectId(_id))).getDeletedCount();
    }

}
