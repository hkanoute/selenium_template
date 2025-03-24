package BaseTest;

import com.aventstack.extentreports.ExtentTest;
import listeners.ExtentRepport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ConfigReader;
import java.time.Duration;

@Listeners(ExtentRepport.class)
public class BaseTest {
    public static WebDriver driver;
    public static Logger logger = LogManager.getLogger();
    protected ExtentRepport repport;
    protected ExtentTest test;


    @BeforeMethod(groups = {"connexion", "panier"})
    public void setUp(ITestResult testResult) {
        switch (ConfigReader.getProperty("BROWSER")) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("TIME_OUT"))));
        driver.get(ConfigReader.getProperty("BASE_URL"));
    }

    @AfterMethod(groups = {"connexion", "panier"})
    public void tearDown(ITestResult testResult) {
        driver.quit();
    }


}
