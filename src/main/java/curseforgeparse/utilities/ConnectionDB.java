package curseforgeparse.utilities;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
public class ConnectionDB {

    public static void main(String[] args) {
        MongoDatabase parsing;
        try (MongoClient mongoClient = new MongoClient(
                new MongoClientURI("mongodb://localhost:27017"))) {

            parsing = mongoClient.getDatabase("CurseForgeParsingLocal");
        }
        MongoCollection modpacksCollection = parsing.getCollection("ModPacks");

    }
}