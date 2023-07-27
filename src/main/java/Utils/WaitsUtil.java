package Utils;


import Browser.Browser;
import ConfigManager.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitsUtil {
    private final WebDriverWait waitTime;
    public WaitsUtil(){
        int timeout = (int) Long.parseLong(ConfigManager.get("timeout"));
        waitTime = new WebDriverWait(Browser.Driver(),Duration.ofSeconds(timeout));
    }
    public boolean waitElementDisplayed(By locator) {
        try {
            return waitTime.until
                    (ExpectedConditions.visibilityOf(Browser.Driver().findElement(locator))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean waitElementToBeClicked(By locator){
        try {
            waitTime.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
