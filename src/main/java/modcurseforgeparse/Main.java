package modcurseforgeparse;

import com.mongodb.client.MongoCollection;
import modcurseforgeparse.utilities.ConnectionDB;
import org.bson.Document;
import org.openqa.selenium.WebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static modcurseforgeparse.utilities.ConnectionDB.killMongoConnection;
import static modcurseforgeparse.utilities.SeleniumSetup.setupDriver;
import static modcurseforgeparse.utilities.SeleniumSetup.teardown;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(modcurseforgeparse.Main.class);
    public static final MongoCollection<Document> collection = ConnectionDB.connectToMongo();

    public static void main(String[] args) {
        logger.info(System.getProperty("java.version"));
        logger.info("Starting timing");
        WebDriver driver = setupDriver();

        try {
            Thread.sleep(5);
            teardown();
            killMongoConnection();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            killMongoConnection();
            teardown();
        }
    }
}
