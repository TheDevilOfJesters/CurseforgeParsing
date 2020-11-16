package curseforgeparse.siteparsings;

import curseforgeparse.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Logger;

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
        logger.info("Starting to scrape page");
        scrapeListingsLoop();
        logger.info("Page number: " + PageNavigation.getCurrentPage(driver));
        PageNavigation.pageNavigation(driver);

    }

    public static void testPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        logger.info("testing started");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/main/div[1]/div[2]/section/div[2]/div/div[3]/div")));
        logger.info("Testing done");
    }

    public static void scrapeListingsLoop() {
        logger.info("Scraping site starting");
        List<WebElement> listings = driver.findElements(By.xpath("/html/body/div[3]/main/div[1]/div[2]/section/div[2]/div/div[3]/div/div"));
        logger.info(String.format("Number of listings %s", listings.size()));

        for (WebElement listing : listings) {
            SplitListing.splitListing(listing);
        }
    }


}