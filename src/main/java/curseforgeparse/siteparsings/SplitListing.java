package curseforgeparse.siteparsings;
import com.mongodb.client.MongoCollection;
import curseforgeparse.Main;
import curseforgeparse.classes.ModPackClass;
import curseforgeparse.classes.ModPackToDBObject;
import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SplitListing {
    private static final ModPackClass mod = new ModPackClass();
    public static final MongoCollection<Document> modPacks = Main.collection;
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
        ModPackToDBObject.modpackDbToObject(mod, modPacks);
    }

    public static void splitInfoBar(WebElement listing) {
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

    public static void splitDataBar(WebElement listing) {
        WebElement dataBar = listing.findElement(By.className("my-1"));
        String downloads = dataBar.findElement(By.xpath("span[1]")).getText();
        String updated = dataBar.findElement(By.xpath("span[2]")).getText();
        String created = dataBar.findElement(By.xpath("span[3]")).getText();


        mod.setDownloads(downloads.substring(0, downloads.length() - 10));
        mod.setUpdated(updated.substring(8));
        mod.setCreated(created.substring(8));
    }

    public static void dealWithTags(WebElement listing) {
        List<WebElement> tags = listing.findElements(By.xpath("div/div[3]/div[2]/div"));
        List<String> tagTypes = new ArrayList<>();
        for (WebElement e : tags) {
            tagTypes.add(e.findElement(By.xpath("a/figure")).getAttribute("title"));
        }
        mod.setTags(tagTypes);
    }
}