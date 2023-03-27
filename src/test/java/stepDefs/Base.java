package stepDefs;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.IRetryAnalyzer;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class Base {
    public WebDriver driver;
    public String baseUrl = "https://opensource-demo.orangehrmlive.com";
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;
    ExtentSparkReporter spark;

    @BeforeSuite
    public void setUp() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/Report.html");
        extent.attachReporter(spark);
    }

    @BeforeTest
    public void beforeTest() {
        // Set the system properties for the drivers based on the browser
        String browser = System.getProperty("browser", "chrome");
        switch (browser.toLowerCase()) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "path/to/msedgedriver");
                driver = new EdgeDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseUrl);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Case Failed: " + result.getName());
            test.log(Status.INFO, "Failure Reason: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case Skipped: " + result.getName());
        } else {
            test.log(Status.PASS, "Test Case Passed: " + result.getName());
        }
//        driver.close();
    }

    @AfterTest
    public void afterTest() {
        extent.flush();
    }

    @AfterSuite
    public void tearDown() {
//        driver.close();
        driver.quit();
        extent.flush();
    }

    public static class RetryAnalyzer implements IRetryAnalyzer {
        private int count = 0;
        private static int maxTry = 3;

        @Override
        public boolean retry(ITestResult result) {
            if (!result.isSuccess() && count < maxTry) {
                count++;
                return true;
            }
            return false;
        }
    }

    public void createTest(String testName, String testDescription) {
        test = extent.createTest(testName, testDescription);
    }

    public void logInfo(String message) {
        test.log(Status.INFO, message);
    }

    public void logPass(String message) {
        test.log(Status.PASS, message);
    }

    public void logFail(String message) {
        test.log(Status.FAIL, message);
    }

//    @DataProvider(name = "failedTests")
//    public Object[][] getFailedTests() {
//        List<ITestResult> failedTests = extent.getTestRunner().getFailedTests();
//        Object[][] testCases = new Object[failedTests.size()][1];
//        for (int i = 0; i < failedTests.size(); i++) {
//            testCases[i][0] = failedTests.get(i).getMethod().getConstructorOrMethod().getMethod();
//        }
//        return testCases;
//    }

//    @Test(dataProvider = "failedTests")
//    public void rerunFailedTests(ITestNGMethod testMethod) {
//        test = extent.createTest("Re-running Failed Test: " + testMethod.getMethodName(), "");
//        test.assignCategory(testMethod.getGroups());
//        test.assignAuthor("Automation Team");
//        try {
//
//            testMethod.getMethod().invoke(this);
//            test.log(Status.PASS, "Test Case Passed on Re-run: " + testMethod.getMethodName());
//        } catch (Exception e) {
//            test.log(Status.FAIL, "Test Case Failed on Re-run: " + testMethod.getMethodName());
//            test.log(Status.FAIL, "Failure Reason: " + e.getMessage());
//        }
//    }
}


