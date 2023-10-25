package main.java.pages;

import main.java.utils.DriverWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import test.java.BaseTest;

public class LoginPage {

    WebDriver driver = DriverWeb.get();

    WebElement textLogin = driver.findElement(By.className("blockHeader__block-header--AHdxL"));
    WebElement inputUserName = driver.findElement(By.name("login"));
    WebElement inputPassword = driver.findElement(By.name("password"));
    WebElement buttonLogin = driver.findElement(By.tagName("button"));

    public void verifyLoginPageOpened() {

        BaseTest.logger.info("Verifying login page title text visibility");
        Assert.assertTrue(textLogin.isDisplayed(),
                "Login page welcome message is not displayed");
    }

    public void fillCredentials(String userName, String password) {

        inputUserName.sendKeys(userName);
        inputPassword.sendKeys(password);
        buttonLogin.click();
    }

    public void isBadCredentialsMsgDisplayed() {

        Assert.assertTrue(driver.findElement(
                        By.xpath("//div[contains(@class, 'notificationItem__error')]")).isDisplayed());
    }
}
