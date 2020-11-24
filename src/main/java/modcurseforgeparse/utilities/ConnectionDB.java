package modcurseforgeparse.utilities;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import modcurseforgeparse.Main;
import org.bson.Document;
import org.slf4j.Logger;

public class ConnectionDB {
    protected static final Logger logger = Main.logger;
    protected static MongoClient mongoClient;

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
            logger.error(ex.getMessage());
        }
        return collection;
    }
    public static void killMongoConnection(){
        mongoClient.close();
    }
}