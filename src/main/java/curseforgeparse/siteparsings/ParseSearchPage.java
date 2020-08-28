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
        scrapeListingsLoop();
        System.out.println("Page number: " + getCurrentPage());
        navigatePages();
    }

    public static void testPageLoaded(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[3]/div/div[1]/div")));
    }

    public static void scrapeListingsLoop(){
        List<WebElement> listings = driver.findElements(By.xpath("/html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[3]/div/div"));
        LIstingBreak(listings.get(1));
        System.out.println("Number of listings: " + listings.size());
    }
    public static void LIstingBreak(WebElement listing){
        System.out.println("test 0");
        WebElement infoBar = listing.findElement(By.className("items-end"));
        String title = infoBar.findElement(By.tagName("h3")).getText();
        System.out.println(title);

        String author = infoBar.findElement(By.className(("text" +"base"))).getText();
        System.out.println(author);

        WebElement dataBar = listing.findElement(By.xpath("//div/div[2]/div[2]"));
        String dowloads = dataBar.findElement(By.xpath("//span[1]")).getText();

        System.out.println(dowloads);


//                "/html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[3]/div/div"
//                   /html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[3]/div/div[2]/div/div[2]/div[1]/a[2]
//                   /html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[3]/div/div[3]/div/div[2]/div[1]/a[2]
//                   /html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[3]/div/div[3]/div/div[2]/div[1]/a[2]
//                   /html/body/div[4]/main/div[1]/div[2]/section/div[2]/div/div[3]/div/div[9]/div/div[2]/div[1]/a[2]
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
