package modpackcurseforgeparse.siteparsings;
import modpackcurseforgeparse.Main;
import modpackcurseforgeparse.classes.ModPackClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import static modpackcurseforgeparse.utilities.TabManager.*;

public class SplitListing {
    public static final ModPackClass mod = new ModPackClass();
    protected static final Logger logger = Main.logger;

    private SplitListing() {
    }

    public static void splitListing(WebElement listing) {
        logger.info("Splitting InfoBar");
        splitInfoBar(listing);
        logger.info("Splitting DataBar");
        splitDataBar(listing);
        logger.info("Dealing with Tags");
        dealWithTags(listing);
        logger.info("Grabbing description");
        mod.setDescription(listing.findElement(By.className("leading-snug")).getText());
        dealWithVersions();
    }

    private static void splitInfoBar(WebElement listing) {
        WebElement infoBar = listing.findElement(By.xpath("div/div[2]/div"));
        WebElement titleE = infoBar.findElement(By.xpath("a[1]/h3"));
        String packTitleUrl = titleE.findElement(By.xpath("./..")).getAttribute("href");
        WebElement authorE = infoBar.findElement(By.xpath(("a[2]")));
        String urlAuthor = authorE.getAttribute("href");

        mod.setTitle(titleE.getAttribute("innerHTML"));
        mod.setPackurl(packTitleUrl);
        mod.setAuthor(authorE.getAttribute("innerHTML"));
        mod.setAuthorHome(urlAuthor);
    }

    private static void splitDataBar(WebElement listing) {
        WebElement dataBar = listing.findElement(By.className("my-1"));
        String downloads = dataBar.findElement(By.xpath("span[1]")).getText();
        String updated = dataBar.findElement(By.xpath("span[2]")).getText();
        String created = dataBar.findElement(By.xpath("span[3]")).getText();


        mod.setDownloads(downloads.substring(0, downloads.length() - 10));
        mod.setUpdated(updated.substring(8));
        mod.setCreated(created.substring(8));
    }

    private static void dealWithTags(WebElement listing) {
        List<WebElement> tags = listing.findElements(By.xpath("div/div[3]/div[2]/div"));
        List<String> tagTypes = new ArrayList<>();
        for (WebElement e : tags) {
            tagTypes.add(e.findElement(By.xpath("a/figure")).getAttribute("title"));
        }
        mod.setTags(tagTypes);
    }

    private static void initTheTabs(WebDriver driver){
        initTabManager(driver);
        getTabHandles(driver);
        changeTabs(driver, 1);
        driver.get(mod.getPackurl() + "/files/all");

    }

    private static void testVersionAvail(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        logger.info("testing started");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/main/div[1]/div[2]/section/div/div/div/section/div[1]/div[2]/div/form/div/div[1]/div/div[2]/select/option")));
    }

    private static String cleanVersion(String gameVersion){
        if(gameVersion.contains(" ")){
            gameVersion = null;
        }
        return gameVersion;
    }

    private static List<String> addToVersionLoop(WebDriver driver){
        List<WebElement> versions = driver.findElements(By.xpath("/html/body/div[3]/main/div[1]/div[2]/section/div/div/div/section/div[1]/div[2]/div/form/div/div[1]/div/div[2]/select/option"));
        List<String> gameVersions = new ArrayList<>();

        for (WebElement e : versions.subList(1, versions.size())) {
            String gameVersion = e.getText().trim();
            gameVersion = cleanVersion(gameVersion);
            if (gameVersion != null) {
                gameVersions.add(gameVersion);
                logger.info(String.format("version: %s added to game: %s", gameVersion, mod.getTitle()));
            }
        }
        logger.info(String.format("All versions added to game: %s", mod.getTitle()));
        return gameVersions;
    }

    public static void dealWithVersions() {
        WebDriver driver = SplitPage.driver;
        initTheTabs(driver);
        testVersionAvail(driver);
        logger.info(String.format("Now adding versions for mod: %s", mod.getTitle()));
        mod.setGameVersions(addToVersionLoop(driver));
        driver.close();
        changeTabs(driver, 0);
    }


}