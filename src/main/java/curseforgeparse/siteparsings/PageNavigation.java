package curseforgeparse.siteparsings;

import curseforgeparse.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Logger;

public class PageNavigation {
    protected static final String PAGENNATION = "/html/body/div[3]/main/div[1]/div[2]/section/div[2]/div/div[4]/div";
    protected static final Logger logger = Main.logger;

    private PageNavigation(){}

    public static void pageNavigation(WebDriver driver) {
        WebElement pagen = driver.findElement(By.xpath(PAGENNATION));

        List<WebElement> paginationLinks = pagen.findElements(By.className("pagination-item"));
        List<WebElement> paginationControls = pagen.findElements(By.xpath("div"));

        logger.info(String.format("Controls: %d", paginationControls.size()));
        logger.info(String.format("Links: %d", paginationLinks.size()));
        paginationControls.get(1).click(); //next page
    }

    public static boolean hasClass(WebElement element, String htmlClass) {
        String[] classes = element.getAttribute("class").split("\\s+");
        for (String classAttr : classes) {
            if (classAttr.equals(htmlClass)) {
                return true;
            }
        }
        return false;
    }

    public static String getCurrentPage(WebDriver driver) {
        List<WebElement> navSpans = driver.findElements(By.className("pagination-item"));
        for (WebElement navSpan : navSpans) {
            boolean isText = hasClass(navSpan, "text-gray-400");
            if (!navSpan.getText().equals("…") && isText) {
                return navSpan.getText();
            }
        }
        return null;
    }

}
