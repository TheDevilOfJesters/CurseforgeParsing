package curseforgeparse;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

import static curseforgeparse.siteparsings.ParseSearchPage.parseSearchPage;
import static curseforgeparse.utilities.SeleniumSetup.setupDriver;
import static curseforgeparse.utilities.SeleniumSetup.teardown;

public class Main {

    public static Logger logger = Logger.getLogger(String.valueOf(Main.class));


    public static void main(String[] args) {
        logger.info("Starting timing");
        WebDriver driver = setupDriver();
        try{
            parseSearchPage(driver);
            Thread.sleep(5);
            teardown();
        } catch (Exception e){
            System.out.println(e);
            teardown();
        }

    }
}
