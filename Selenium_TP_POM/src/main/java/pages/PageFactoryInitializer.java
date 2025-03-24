package pages;

import BaseTest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageFactoryInitializer {
    protected WebDriver driver;

    PageFactoryInitializer() {
        this.driver = BaseTest.driver;
        PageFactory.initElements(driver,this);
    }
}
