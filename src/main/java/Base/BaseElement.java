package Base;

import Browser.Browser;
import Logger.MyLogger;
import Utils.WaitsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class BaseElement {
    protected By uniqueLocator;
    protected String elementName;
    private final WaitsUtil waits= new WaitsUtil();
    public BaseElement(By locator, String name){
        this.uniqueLocator = locator;
        this.elementName = name;
    }
    public WebElement getElement(){
        waits.waitElementDisplayed(uniqueLocator);
        return Browser.Driver().findElement(uniqueLocator);
    }
    public List<WebElement> getElements(By uniqueLocator){
        waits.waitElementDisplayed(uniqueLocator);
        return Browser.Driver().findElements(uniqueLocator);
    }
    public By click(){
        waits.waitElementDisplayed(uniqueLocator);
        waits.waitElementToBeClicked(uniqueLocator);
        getElement().click();
        MyLogger.info("Click on: "+uniqueLocator);
        return null;
    }

    public String getText(){
        waits.waitElementDisplayed(uniqueLocator);
        MyLogger.info("Get text from: "+uniqueLocator);
        return getElement().getText();
    }
    public void sendKeys(String text){
        waits.waitElementDisplayed(uniqueLocator);
        MyLogger.info("SendKeys to: "+uniqueLocator+ "this text: "+ text);
        getElement().sendKeys(text);
    }
}
