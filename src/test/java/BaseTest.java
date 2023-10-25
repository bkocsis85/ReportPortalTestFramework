package test.java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import main.java.utils.Constants;
import main.java.utils.DriverWeb;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public ExtentSparkReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;

    @BeforeTest
    public void beforeTest() {

        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports"
                + File.separator + "AutomationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", "Balazs Kocsis");
    }

    @BeforeMethod
    //@Parameters(value = {"browserName"})
    public void beforeMethod(Method testMethod) {

        logger = extent.createTest(testMethod.getName());
        WebDriver driver = setupDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverWeb.set(driver);
        setupEnvironment();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {

        WebDriver driver = DriverWeb.get();

        if (result.getStatus() == ITestResult.SUCCESS) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Passed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
        } else if (result.getStatus() == ITestResult.FAILURE) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Failed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            logger.log(Status.FAIL, m);
        }  else if (result.getStatus() == ITestResult.SKIP) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Skipped";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
            logger.log(Status.SKIP, m);
        }
        driver.quit();
    }

    @AfterTest
    public void afterTest() {
        extent.flush();
    }

    public WebDriver setupDriver(String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" +
                    File.separator + "chromedriver.exe");
            return new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers" +
                    File.separator + "geckodriver.exe");
            return new FirefoxDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" +
                    File.separator + "chromedriver.exe");
            return new ChromeDriver();
        }
    }

    public void setupEnvironment() {

        WebDriver driver = DriverWeb.get();

        if (Constants.environment.equals("LOCAL")) {
            driver.get(Constants.urlLocal);
        } else if (Constants.environment.equals("REMOTE")) {
            driver.get(Constants.urlRemote);
        }
    }
}
