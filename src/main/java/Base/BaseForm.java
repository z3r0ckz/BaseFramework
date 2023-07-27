package Base;

import Utils.WaitsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public abstract class BaseForm {
    protected By uniqueFormName;
    protected String formName;
    protected WaitsUtil waits;

    public BaseForm(By uniqueFormName, String formName){
        this.uniqueFormName = uniqueFormName;
        this.formName = formName;
    }

    public boolean isPageOpen(By uniqueFormLocator, String formName) {
        try {
            waits.waitElementDisplayed(uniqueFormLocator);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }


}
