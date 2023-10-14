package main.java.pageEvents;

import main.java.pageObjects.LoginPageElements;
import org.testng.Assert;
import test.java.BaseTest;

public class LoginPageEvents {

    public void verifyLoginPageOpened() {
        BaseTest.logger.info("Verifying login page title text visibility");
        Assert.assertTrue(LoginPageElements.loginText.isDisplayed(),
                "Login page welcome message is not displayed");
    }
}
