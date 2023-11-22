package main.java.pages;

import main.java.utils.DriverWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddNewWidgetDialog {

    WebDriver driver = DriverWeb.get();

    public boolean isAddNewWidgetDialogDisplayed() {
        return driver.findElement(
                By.xpath("//div[@id='modal-root']//span[text() = 'Add new widget']")).isDisplayed();
    }

    public void selectFilter(String filterName) {

        driver.findElement(
                By.xpath("//div[@class='widget-type-selector']//div[text() = '" + filterName + "']")).
                click();
    }

    public void clickOnNextStepBtn() {

        driver.findElement(By.xpath("//span[text()='Next step']")).click();
    }

    public void selectDemoFilter() {

        driver.findElement(
                By.xpath("//span[contains(@class, 'inputRadio__toggler--1-jGS')]")).click();
    }

    public void fillWidgetName(String widgetName) {

        WebElement inputWidgetName = driver.findElement(
                By.xpath("//input[@placeholder = 'Enter widget name']"));

        inputWidgetName.clear();
        inputWidgetName.sendKeys(widgetName);
    }

    public void clickOnAddBtn() {

        driver.findElement(
                By.xpath("//button[text() = 'Add']")).click();
    }
}
