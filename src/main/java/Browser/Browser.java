package Browser;

import org.openqa.selenium.WebDriver;

public class Browser {
    private static WebDriver driver;
    public static WebDriver Driver(){
        if(driver == null){
            driver = BrowserFactory.getDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }
}
