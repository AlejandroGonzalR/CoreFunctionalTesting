import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

public class ValidateRequiredFields {

    private WebDriver driver;
    private final StringBuffer verificationErrors = new StringBuffer();
    private final String requiredInputText = "This field is required";

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", Constants.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @BeforeTest
    public void setup(ITestContext ctx) {
        TestRunner runner = (TestRunner) ctx;
        runner.setOutputDirectory(Constants.REQUIRED_FIELDS_PATH);
    }

    @Test
    public void requiredFieldsTest() {
        driver.get(Constants.BASE_URL);
        driver.findElement(By.id("submit")).click();
        assertEquals(driver.findElement(By.xpath("//div[@id='input-group-name']/div/span")).getAttribute("innerHTML"), requiredInputText);
        assertEquals(driver.findElement(By.xpath("//div[@id='input-group-email']/div/span")).getAttribute("innerHTML"), requiredInputText);
        assertEquals(driver.findElement(By.xpath("//div[@id='input-group-subject']/div/span")).getAttribute("innerHTML"), requiredInputText);
        assertEquals(driver.findElement(By.xpath("//div[@id='input-group-message']/div/span")).getAttribute("innerHTML"), requiredInputText);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}