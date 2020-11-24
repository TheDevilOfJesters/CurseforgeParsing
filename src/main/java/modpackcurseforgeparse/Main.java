package modpackcurseforgeparse;

import com.mongodb.client.MongoCollection;
import modpackcurseforgeparse.utilities.ConnectionDB;
import org.bson.Document;
import org.openqa.selenium.WebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static modpackcurseforgeparse.siteparsings.SplitPage.parseSearchPage;
import static modpackcurseforgeparse.utilities.ConnectionDB.killMongoConnection;
import static modpackcurseforgeparse.utilities.SeleniumSetup.setupDriver;
import static modpackcurseforgeparse.utilities.SeleniumSetup.teardown;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static final MongoCollection<Document> collection = ConnectionDB.connectToMongo();

    public static void main(String[] args) {
        logger.info(System.getProperty("java.version"));
        logger.info("Starting timing");
        WebDriver driver = setupDriver();

            try {
            parseSearchPage(driver);
            Thread.sleep(5);
            teardown(driver);
            killMongoConnection();
            } catch (Exception e) {
            logger.warn(e.getMessage());
                killMongoConnection();
                teardown(driver);
        }
    }
}
