package modcurseforgeparse.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class TabManager {
    protected static ArrayList<String> tabs;

    private TabManager(){}

    public static void initTabManager(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.open()");  //create a new tab with javascript
    }

    public static void getTabHandles(WebDriver driver){
        tabs = new ArrayList<>(driver.getWindowHandles());
    }

    public static void changeTabs(WebDriver driver, int tabToChange){
        driver.switchTo().window(tabs.get(tabToChange));
    }

}
