package test.java.UI;

import main.java.pages.LoginPage;
import org.testng.annotations.Test;
import test.java.BaseTest;

public class LoginPageTests extends BaseTest {

    @Test
    public void loginPageLoadTest() {

        LoginPage loginPage = new LoginPage();

        loginPage.verifyLoginPageOpened();
    }
}
