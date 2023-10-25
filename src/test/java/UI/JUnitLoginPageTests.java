package test.java.UI;

import main.java.pages.LoginPage;
import org.junit.jupiter.api.Test;
import test.java.BaseTest;

public class JUnitLoginPageTests extends BaseTest {

    @Test
    public void loginPageLoadTest() {

        LoginPage loginPage = new LoginPage();

        loginPage.verifyLoginPageOpened();
    }
}
