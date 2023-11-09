package main.java.pages;

import main.java.utils.DriverWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AllDashboardsPage {

    WebDriver driver = DriverWeb.get();

    WebElement linkDemoDashboard = driver.findElement(
            By.xpath("//div[@class='gridRow__grid-row--1pS-5']/a[text() = 'DEMO DASHBOARD']"));

    public void selectDemoDashboard() {
        linkDemoDashboard.click();
    }
}
