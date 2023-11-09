package main.java.pages;

import main.java.utils.DriverWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {

    WebDriver driver = DriverWeb.get();

    WebElement buttonAddNewWidget = driver.findElement(
            By.xpath("//button/span[contains(text(), 'Add new widget')]"));

    public void clickOnAddNewWidget() {

        buttonAddNewWidget.click();
    }

    public boolean isWidgetDisplayed(String widgetName) {

        return driver.findElement(By.xpath("//div[text()='" + widgetName + "']")).isDisplayed();
    }
}
