package test.java.BDD;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.java.pages.AddNewWidgetDialog;
import main.java.pages.AllDashboardsPage;
import main.java.pages.DashboardPage;
import main.java.pages.LoginPage;
import org.testng.Assert;
import test.java.BaseTest;

public class AddWidgetSteps extends BaseTest {

    @Before
    public void setupDriver() {
        setup();
    }

    @After
    public void killDriver() {
        tearDown();
    }

    @Given("^I log in to Report Portal with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void login(String userName, String password) {

        LoginPage loginPage = new LoginPage();

        loginPage.fillCredentials(userName, password);
    }

    @When ("I select my dashboard")
    public void selectDashboard() {

        AllDashboardsPage allDashboardsPage = new AllDashboardsPage();

        allDashboardsPage.selectDemoDashboard();
    }

    @When("I click on Add new widget button")
    public void clickOnAddNewButton() {

        DashboardPage dashboardPage = new DashboardPage();

        dashboardPage.clickOnAddNewWidget();
    }

    @Then("Add new widget dialog should be displayed")
    public void isAddNewWidgetDialogDisplayed() {

        AddNewWidgetDialog addNewWidgetDialog = new AddNewWidgetDialog();

        Assert.assertTrue(
                addNewWidgetDialog.isAddNewWidgetDialogDisplayed(), "Add new widget dialog is not displayed");
    }

    @When("^I select \"([^\"]*)\"$")
    public void selectFilter(String filterName) {

        AddNewWidgetDialog addNewWidgetDialog = new AddNewWidgetDialog();

        addNewWidgetDialog.selectFilter(filterName);

    }
    @When("Click on Next step button")
    public void click_on_next_step_button() {

        AddNewWidgetDialog addNewWidgetDialog = new AddNewWidgetDialog();

        addNewWidgetDialog.clickOnNextStepBtn();
    }
    @When("Select my filter")
    public void select_my_filter() {

        AddNewWidgetDialog addNewWidgetDialog = new AddNewWidgetDialog();

        addNewWidgetDialog.selectDemoFilter();
    }
    @When("^Fill widget name text field with \"([^\"]*)\"$")
    public void fill_widget_name_text_field_with_name(String widgetName) {

        AddNewWidgetDialog addNewWidgetDialog = new AddNewWidgetDialog();

        addNewWidgetDialog.fillWidgetName(widgetName);
    }
    @When("Click on Add button")
    public void click_on_add_button() {

        AddNewWidgetDialog addNewWidgetDialog = new AddNewWidgetDialog();

        addNewWidgetDialog.clickOnAddBtn();
    }
    @Then("^My widget should be displayed on the dashboard with title: \"([^\"]*)\"$")
    public void my_widget_should_be_displayed_on_the_dashboard(String widgetName) {

        DashboardPage dashboardPage = new DashboardPage();

        Assert.assertTrue(dashboardPage.isWidgetDisplayed(widgetName));
    }

}
