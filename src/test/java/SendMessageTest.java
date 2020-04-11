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

public class SendMessageTest {

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
        runner.setOutputDirectory(Constants.SEND_MESSAGE_PATH);
    }

    @Test
    public void sendMessageTest() {
        driver.get(Constants.BASE_URL);

        driver.findElement(By.name("your-name")).clear();
        driver.findElement(By.name("your-name")).sendKeys("Alejandro Gonzalez");
        driver.findElement(By.name("your-email")).clear();
        driver.findElement(By.name("your-email")).sendKeys("alejandrogonzalr@gmail.com");
        driver.findElement(By.name("your-subject")).clear();
        driver.findElement(By.name("your-subject")).sendKeys("Core functional tests with Selenium");
        driver.findElement(By.name("your-message")).clear();
        driver.findElement(By.name("your-message")).sendKeys("This is a test message");
        driver.findElement(By.id("submit")).click();

        assertEquals(driver.findElement(By.xpath("//div[@id='b-toaster-bottom-right']/descendant::div[@class='toast-body']")).getAttribute("innerHTML"), "Message sent successfully");
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