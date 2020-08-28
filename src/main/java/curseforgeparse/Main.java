package curseforgeparse;

import curseforgeparse.utilities.SeleniumSetup;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static curseforgeparse.utilities.SeleniumSetup.*;
import static curseforgeparse.siteparsings.ParseSearchPage.*;

public class Main {
    static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        org.apache.log4j.BasicConfigurator.configure();
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
