package test.java.UI;

import main.java.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.BaseTest;

public class LoginNegativeTests extends BaseTest {

    @DataProvider(name = "accounts")
    public static Object[][] accounts() {
        return new Object[][] {{"account1", "password1"}, {"account2", "password2"},
                {"account3", "password3"}, {"account4", "password4"}, {"account5", "password5"}};
    }

    @Test(dataProvider = "accounts")
    public void loginWithWrongCredentials(String userName, String password) {

        LoginPage loginPage = new LoginPage();

        loginPage.fillCredentials(userName, password);

        loginPage.isBadCredentialsMsgDisplayed();
    }
}
