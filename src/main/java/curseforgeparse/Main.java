package curseforgeparse;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

import static curseforgeparse.siteparsings.ParseSearchPage.parseSearchPage;
import static curseforgeparse.utilities.SeleniumSetup.setupDriver;
import static curseforgeparse.utilities.SeleniumSetup.teardown;

public class Main {
<<<<<<< HEAD
<<<<<<< HEAD
    public static Logger logger = Logger.getLogger(String.valueOf(Main.class));
=======
    static Logger logger = Logger.getLogger(String.valueOf(Main.class));
>>>>>>> 6054e2eaae031701dd91618839e677b33f53c5d1
=======
    static Logger logger = Logger.getLogger(String.valueOf(Main.class));
>>>>>>> 6e8418b506b5489d4c8e90736107f714ae19dd75


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
