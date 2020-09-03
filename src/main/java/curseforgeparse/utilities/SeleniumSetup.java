package curseforgeparse.utilities;

import curseforgeparse.Main;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public final class SeleniumSetup {
    protected static WebDriver driver;

    public SeleniumSetup() {
    }

    public static WebDriver setupDriver() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        return driver;
    }

    public static void teardown() {
        driver.quit();
        Main.logger.info("\nDriver closed successfully");
    }


}


