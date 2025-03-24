package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends PageFactoryInitializer {
    @FindBy(tagName = "h6")
    private WebElement h6Title;

    public String getTitleText() {
        return  h6Title.getText();
    }
}
