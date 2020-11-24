package modcurseforgeparse.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import modpackcurseforgeparse.Main;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public final class SeleniumSetup {
    private SeleniumSetup(){}

    protected static WebDriver driver;

    public static WebDriver setupDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("window-size=1980,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        return driver;
    }

    public static void teardown() {
        driver.quit();
        Main.logger.info("Driver closed successfully");
    }


}


