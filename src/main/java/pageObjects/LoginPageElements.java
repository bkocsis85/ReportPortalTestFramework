package main.java.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.BaseTest;

public interface LoginPageElements {

    WebElement loginText = BaseTest.driver.findElement(By.className("blockHeader__block-header--AHdxL"));
}
