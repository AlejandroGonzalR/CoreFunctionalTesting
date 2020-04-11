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
    private String baseUrl;
    private final StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        String chromeDriverPath = "src/main/resources/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "https://vuesimpleform.web.app/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @BeforeTest
    public void setup(ITestContext ctx) {
        TestRunner runner = (TestRunner) ctx;
        runner.setOutputDirectory("test-output/required-fields");
    }

    @Test
    public void requiredFieldsTest() {
        driver.get(baseUrl);
        driver.findElement(By.cssSelector("button.form-control-input")).click();
        assertEquals(driver.findElement(By.xpath("//div[@id='input-group-1']/div/span")).getAttribute("innerHTML"), "Campo requerido");
        assertEquals(driver.findElement(By.xpath("//div[@id='input-group-2']/div/span")).getAttribute("innerHTML"), "Campo requerido");
        assertEquals(driver.findElement(By.xpath("//div[@id='input-group-3']/div/span")).getAttribute("innerHTML"), "Campo requerido");
        assertEquals(driver.findElement(By.xpath("//div[@id='input-group-4']/div/span")).getAttribute("innerHTML"), "Campo requerido");
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