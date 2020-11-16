package curseforgeparse;

import com.mongodb.client.MongoCollection;
import curseforgeparse.utilities.ConnectionDB;
import org.bson.Document;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

import static curseforgeparse.siteparsings.SplitPage.parseSearchPage;
import static curseforgeparse.utilities.ConnectionDB.killMongoConnection;
import static curseforgeparse.utilities.SeleniumSetup.setupDriver;
import static curseforgeparse.utilities.SeleniumSetup.teardown;

public class Main {

    public static final Logger logger = Logger.getLogger(String.valueOf(Main.class));
    public static final MongoCollection<Document> collection = ConnectionDB.connectToMongo();

    public static void main(String[] args) {
        logger.info("Starting timing");
        WebDriver driver = setupDriver();
            try {
            parseSearchPage(driver);
            Thread.sleep(5);
            teardown();
        } catch (Exception e) {
            logger.warning(e.getMessage());
            teardown();
            killMongoConnection();
        }

    }
}
