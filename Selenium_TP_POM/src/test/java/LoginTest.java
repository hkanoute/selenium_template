import BaseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;


public class LoginTest extends BaseTest {

    @Test
    public void connexionTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("Admin","admin123");
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertEquals(dashboardPage.getTitleText(), "Dashboard");
    }

    @Test
    public void connexionFailedTest(){
    }

}
