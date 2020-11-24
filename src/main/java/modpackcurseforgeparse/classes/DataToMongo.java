package modpackcurseforgeparse.classes;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import modpackcurseforgeparse.Main;
import modpackcurseforgeparse.siteparsings.SplitListing;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class DataToMongo {

    protected static final Logger logger = Main.logger;
    protected static final MongoCollection<Document> collection = Main.collection;
    private DataToMongo() {
    }

    public static void initUpdate() {
        dataToMongo();
    }

    private static void dataToMongo() {
        Bson filter = setUpFilter(SplitListing.mod);
        BasicDBObject setQuery = setUpQuery();
        mongoUpdate(filter, setQuery);
    }

    public static Bson setUpFilter(ModPackClass modpack){
        return and(eq("Title", modpack.getTitle()), eq("Author", modpack.getAuthor()), eq("AuthorHome",
                modpack.getAuthorHome()), eq("PackUrl", modpack.getPackurl()), eq("Created", modpack.getCreated()));
    }

    private static BasicDBObject setUpQuery() {
        BasicDBObject updateFields = new BasicDBObject();
        String[] tags = SplitListing.mod.getTags().toArray(new String[0]);
        String[] gameVersions = SplitListing.mod.getGameVersions().toArray(new String[0]);
        updateFields.append("Downloads", SplitListing.mod.getDownloads());
        updateFields.append("Updated", SplitListing.mod.getUpdated());
        updateFields.append("Description", SplitListing.mod.getDescription());
        updateFields.append("tags", tags);
        updateFields.append("gameVersions", gameVersions);
        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", updateFields);
        return setQuery;
    }


    private static void mongoUpdate(Bson filter, BasicDBObject setQuery) {
        UpdateOptions options = new UpdateOptions().upsert(true);

        final UpdateResult writeResult = collection.updateOne(filter, setQuery, options);
        logger.info(String.format("Matched results: %o | Modified Results: %o | For: %s",
                writeResult.getMatchedCount(), writeResult.getModifiedCount(), SplitListing.mod.getTitle()));
        if (writeResult.getUpsertedId() != null) {
            logger.info(String.format("Inserted: %s with", SplitListing.mod.getTitle()));
        }

        logger.info("Database Updated");
    }
}
