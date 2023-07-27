package Base;

import Browser.Browser;
import ConfigManager.ConfigManager;
import Logger.MyLogger;
import Utils.WaitsUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {
    public final WebDriver driver = Browser.Driver();
    protected final String url = ConfigManager.get("baseUrl");
    protected final WaitsUtil waits = new WaitsUtil();

    @BeforeClass
    public void setup(){
        driver.get(url);
        MyLogger.info("Go to the Page: "+ url);
    }

    @AfterTest
    public void teardown(){
        MyLogger.info("End of the Test");
        //driver.quit();
    }


}
