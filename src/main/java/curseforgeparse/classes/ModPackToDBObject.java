package curseforgeparse.classes;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import curseforgeparse.Main;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class ModPackToDBObject {

    private ModPackToDBObject(){}
    protected static final Logger logger = Main.logger;

    public static void modpackDbToObject(ModPackClass modpack, MongoCollection<Document> collection) {

        String[] tags =  modpack.getTags().toArray(new String[0]);

        Bson filter = and(eq("Title", modpack.getTitle()), eq("Author", modpack.getAuthor()),eq("AuthorHome",
                modpack.getAuthorHome()), eq("PackUrl", modpack.getPackurl()), eq("Created", modpack.getCreated()));
        BasicDBObject updateFields = new BasicDBObject();
        updateFields.append("Downloads", modpack.getDownloads());
        updateFields.append("Updated", modpack.getUpdated());
        updateFields.append("Description", modpack.getDescription());
        updateFields.append("tags", tags);
        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", updateFields);

        UpdateOptions options = new UpdateOptions().upsert(true);
        collection.updateOne(filter, setQuery, options);

        logger.info("Database Updated");
    }
}
