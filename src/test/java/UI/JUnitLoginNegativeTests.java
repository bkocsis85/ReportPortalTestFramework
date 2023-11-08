package test.java.UI;

import main.java.pages.LoginPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import test.java.BaseTest;

public class JUnitLoginNegativeTests extends BaseTest {

    @ParameterizedTest
    @CsvSource({"account1, password1", "account2, password2", "account3,password3"})
    public void loginWithWrongCredentials(String userName, String password) {

        LoginPage loginPage = new LoginPage();

        loginPage.fillCredentials(userName, password);

        loginPage.isBadCredentialsMsgDisplayed();
    }
}
