package Base.BaseElements;

import Base.BaseElement;
import org.openqa.selenium.By;

public class TextBox extends BaseElement {
    public TextBox(By locator, String name) {
        super(locator, name);
    }
}
