package curseforgeparse.utilities;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import curseforgeparse.Main;
import org.bson.Document;

import java.util.logging.Logger;


public class ConnectionDB {
    protected static final Logger logger = Main.logger;
    protected static MongoClient mongoClient = null;

    private ConnectionDB() {}

    public static MongoCollection<Document> connectToMongo() {
        MongoCollection<Document> collection = null;

        try {
            logger.info("Opening Mongo Client");
            mongoClient = new MongoClient("localhost", 27017);
            logger.info("Opening Mongo DB ");
            MongoDatabase curseforgeDB = mongoClient.getDatabase("CurseForgeParsingLocal");
            logger.info("Opening Mongo Collection");
            collection = curseforgeDB.getCollection("ModPacks");
            logger.info("Returning collection");
            return collection;
        } catch(Exception ex){
            logger.severe(ex.getMessage());
        }
        return collection;
    }
    public static void killMongoConnection(){
        mongoClient.close();
    }
}