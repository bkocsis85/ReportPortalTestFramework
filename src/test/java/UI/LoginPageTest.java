package test.java.UI;

import main.java.pageEvents.LoginPageEvents;
import org.testng.annotations.Test;
import test.java.BaseTest;

public class LoginPageTest extends BaseTest {

    @Test
    public void loginPageLoadTest() {

        LoginPageEvents loginPageEvents = new LoginPageEvents();

        loginPageEvents.verifyLoginPageOpened();
    }
}
