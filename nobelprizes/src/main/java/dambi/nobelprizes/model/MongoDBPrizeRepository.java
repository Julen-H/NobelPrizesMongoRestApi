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

    @Autowired
    public MongoDBPrizeRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    void init() {
        prizeCollection = client.getDatabase("nobelprize").getCollection("prizes", Prize.class);
    }

    @Override
    public List<Prize> findAll() {
        return prizeCollection.find().into(new ArrayList<>());
    }

    @Override
    public Prize findById(String id) {
        return prizeCollection.find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public Prize save(Prize prize) {
        prizeCollection.insertOne(prize);
        return prize;
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
