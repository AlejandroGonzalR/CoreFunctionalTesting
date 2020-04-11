import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

public class ValidateLayout {

    private WebDriver driver;
    private final StringBuffer verificationErrors = new StringBuffer();

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
        runner.setOutputDirectory(Constants.VALIDATE_LAYOUT_PATH);
    }

    @Test
    public void layoutTest() {
        driver.get(Constants.BASE_URL);
        assertEquals(driver.findElement(By.cssSelector("h1")).getText(),"Send your message");
        assertTrue(isElementPresent(By.name("your-name")));
        assertTrue(isElementPresent(By.name("your-email")));
        assertTrue(isElementPresent(By.name("your-subject")));
        assertTrue(isElementPresent(By.name("your-message")));
        assertTrue(isElementPresent(By.cssSelector("button#submit")));
        assertTrue(isElementPresent(By.cssSelector("button#reset")));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}