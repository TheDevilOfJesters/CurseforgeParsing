package curseforgeparse.siteparsings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ParseSearchPage {
    protected static final String modpackPage = "https://www.curseforge.com/minecraft/modpacks";
    protected static WebDriver driver;
    public static void parseSearchPage(WebDriver Driver){
        driver = Driver;
        driver.get(modpackPage);

        testPageLoaded();
        scrapeListingsLoop();
        System.out.println("Page number: " + getCurrentPage());
        driver.findElement(By.xpath("/html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[1]/div/div[2]/div[2]/div/a[4]")).click();
        testPageLoaded();
        System.out.println("\n");
        scrapeListingsLoop();
//        System.out.println("Page number: " + getCurrentPage());
        navigatePages();
    }

    public static void testPageLoaded(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[3]/div/div[1]/div")));
    }

    public static void scrapeListingsLoop(){
        List<WebElement> listings = driver.findElements(By.xpath("/html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[3]/div/div"));
        ListingBreak(listings.get(1));
//        System.out.println("Number of listings: " + listings.size());
    }
    public static void ListingBreak(WebElement listing){
        WebElement infoBar = listing.findElement(By.className("items-end"));
        String title = infoBar.findElement(By.tagName("h3")).getText();
        System.out.println(title);

        String author = infoBar.findElement(By.xpath(("a[2]"))).getText();
        System.out.println(author);

        WebElement dataBar = listing.findElement(By.className("my-1"));
//        WebElement dataBar = listing.findElement(By.xpath("//div/div[2]/div[2]"));
        String dowloads = dataBar.findElement(By.xpath("span[1]")).getText();
        String Updated = dataBar.findElement(By.xpath("span[2]")).getText();
        String created = dataBar.findElement(By.xpath("span[3]")).getText();

        String description = listing.findElement(By.className("leading-snug")).getText();

        System.out.println(dowloads);
        System.out.println(Updated);
        System.out.println(created);
        System.out.println(description);

        List<WebElement> tags = listing.findElements(By.xpath("/div/div[3]/div"));
        System.out.println(tags.size());
    }


    public static void navigatePages(){
        List<WebElement> pagnationLinks = driver.findElements(By.xpath("/html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[1]/div/div[2]/div[2]/div/a"));
        List<WebElement> pagnationControls = driver.findElements(By.xpath("/html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[4]/div/div"));
        System.out.println("Controls: " + pagnationControls.size());
        System.out.println("pagnationLinks: " + pagnationLinks.size());
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
