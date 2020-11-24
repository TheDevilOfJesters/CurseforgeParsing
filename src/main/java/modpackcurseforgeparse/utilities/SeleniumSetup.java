package modpackcurseforgeparse.utilities;

import modpackcurseforgeparse.Main;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public final class SeleniumSetup {
    private SeleniumSetup(){}

    private static WebDriver driver;

    public static WebDriver setupDriver() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
//        options.addArguments("window-size=1200,1100");
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver(options);
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1200,1100");
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver(options);
        return driver;
    }

    public static void teardown(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
        Main.logger.info("Driver closed successfully");
    }


}


