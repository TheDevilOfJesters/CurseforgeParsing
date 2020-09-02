package curseforgeparse.siteparsings;

import com.google.common.io.Resources;
import curseforgeparse.Main;
import curseforgeparse.classes.ModPackClass;
import curseforgeparse.utilities.Utility;
import org.bson.types.ObjectId;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParseSearchPageWIthMongo {


    protected static final String modpackPage = "https://www.curseforge.com/minecraft/modpacks";
    protected static WebDriver driver;
    public static ModPackClass mod = new ModPackClass();

    public static void parseSearchPage(WebDriver Driver){
        driver = Driver;
        driver.get(modpackPage);
        testPageLoaded();
        scrapeListingsLoop();
        System.out.println(mod.toString());
        driver.findElement(By.xpath("/html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[1]/div/div[2]/div[2]/div/a[4]")).click();

        testPageLoaded();
        scrapeListingsLoop();
        System.out.println(mod.toString());

//        navigatePages();
    }

    public static void testPageLoaded(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[3]/div/div[1]/div")));
    }

    public static void scrapeListingsLoop(){
        List<WebElement> listings = driver.findElements(By.xpath("/html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[3]/div/div"));
        ListingBreak(listings.get(0));
    }
    public static void ListingBreak(WebElement listing){
        WebElement infoBarE = listing.findElement(By.className("items-end"));
        WebElement titleE = infoBarE.findElement(By.tagName("h3"));
        mod.setTitle(titleE.getText());
        mod.setPackurl(titleE.findElement(By.xpath("./..")).getAttribute("href"));

        WebElement authorE = infoBarE.findElement(By.xpath(("a[2]")));
        mod.setAuthor(authorE.getText());
        mod.setAuthorHome(authorE.getAttribute("href"));

        WebElement dataBar = listing.findElement(By.className("my-1"));
        String downloads = dataBar.findElement(By.xpath("span[1]")).getText();
        mod.setDownloads(downloads.split(String.valueOf(' '))[0].trim());
        String updated = dataBar.findElement(By.xpath("span[2]")).getText();
        mod.setUpdated(updated.split(" ", 2)[1].trim());
        String created = dataBar.findElement(By.xpath("span[3]")).getText();
        mod.setCreated(created.split(" ", 2)[1].trim());

        String description = listing.findElement(By.className("leading-snug")).getText();
        mod.setDescription(description);
        List<WebElement> tags = listing.findElements(By.xpath("div/div[3]/div[2]/div"));
        List<String> tagTypes = new ArrayList<String>();;
        for (WebElement e: tags){
            tagTypes.add('"' + e.findElement(By.xpath("a/figure")).getAttribute("title") + '"');
        }
        mod.setTags(tagTypes);
//        mod.setId(Utility.ReadToString(Resources.getResource("lastValue.txt")));
        mod.setId(ObjectId.get());
    }


    public static void navigatePages(){
        List<WebElement> paginationLinks = driver.findElements(By.xpath("/html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[1]/div/div[2]/div[2]/div/a"));
        List<WebElement> paginationControls = driver.findElements(By.xpath("/html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[4]/div/div"));
        Main.logger.info(String.valueOf(paginationControls.size()));
        Main.logger.info(String.valueOf(paginationLinks.size()));
    }


    public boolean hasClass(WebElement element, String htmlClass) {
        String[] classes = element.getAttribute("class").split("\\s+");
        if (classes != null) {
            for (String classAttr: classes) {
                if (classAttr.equals(htmlClass)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getCurrentPage(){
        List<WebElement> navSpans = driver.findElements(By.xpath("/html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[4]/div/span"));

        for(WebElement navspan: navSpans){
           if(!navspan.getText().equals("…")){
               return navspan.getText();
           }
        }
        return null;
    }
}
