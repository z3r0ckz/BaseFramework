package Browser;

import ConfigManager.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {
    private static WebDriver driver;
    private static final String browserOpt = ConfigManager.get("browserOpt");
    public static <browserOpt> WebDriver getDriver(){
        WebDriver result = null;
        switch (browserOpt){
            case "chrome" -> {
                if(driver == null){
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments(ChromeCaps.readChromeBrowserConfig());
                    WebDriverManager.chromedriver().setup();
                    result = new ChromeDriver(options);
                }
            }
            case "firefox" -> {
                if (driver ==null){
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments(FirefoxCaps.readFirefoxBrowserConfig());
                    WebDriverManager.firefoxdriver().setup();
                    result = new FirefoxDriver(options);
                }
            }
            default -> throw new IllegalArgumentException("Invalid browser name: "+ browserOpt);
        }
        if (result == null){
            result = driver;
        }
        return result;
    }
}
