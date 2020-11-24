package modpackcurseforgeparse.siteparsings;

import modpackcurseforgeparse.Main;
import modpackcurseforgeparse.classes.DataToMongo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.util.List;

import static java.lang.Thread.sleep;

public class SplitPage {
    protected static final String MODPACK_PAGE = "https://www.curseforge.com/minecraft/modpacks";
    protected static WebDriver driver;
    protected static final Logger logger = Main.logger;

    private SplitPage() {
    }

    public static void parseSearchPage(WebDriver myDriver) {
        driver = myDriver;
        driver.get(MODPACK_PAGE);
        logger.info("Starting page load test");
        testPageLoaded();
        logger.info("Starting to scrape page" + PageNavigation.getCurrentPage(driver));
        scrapeListingsLoop();
        PageNavigation.pageNavigation(driver);

    }

    public static void testPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        logger.info("testing started");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/main/div[1]/div[2]/section/div[2]/div/div[3]/div")));
        logger.info("Testing done");
    }

    public static void scrapeListingsLoop(){
        logger.info("Scraping site starting");
        List<WebElement> listings = driver.findElements(By.xpath("/html/body/div[3]/main/div[1]/div[2]/section/div[2]/div/div[3]/div/div"));
        if(!listings.isEmpty()){
            logger.info("Number of listings {}", listings.size());
            for (WebElement listing : listings) {
                SplitListing.splitListing(listing);
//                SplitListing.dealWithVersions(driver);
                try{
                    sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DataToMongo.initUpdate();

            }
        }
    }
}