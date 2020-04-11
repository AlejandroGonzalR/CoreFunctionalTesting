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
        runner.setOutputDirectory("test-output/send-message");
    }

    @Test
    public void sendMessageTest() {
        driver.get(baseUrl);

        driver.findElement(By.name("your-name")).clear();
        driver.findElement(By.name("your-name")).sendKeys("Alejandro Gonzalez");
        driver.findElement(By.name("your-email")).clear();
        driver.findElement(By.name("your-email")).sendKeys("alejandrogonzalr@gmail.com");
        driver.findElement(By.name("your-subject")).clear();
        driver.findElement(By.name("your-subject")).sendKeys("Test Pruebas Funcionales con Selenium");
        driver.findElement(By.name("your-message")).clear();
        driver.findElement(By.name("your-message")).sendKeys("Este es un mensaje de prueba");
        driver.findElement(By.cssSelector("button.form-control-input")).click();

        assertEquals(driver.findElement(By.xpath("//div[@id='b-toaster-bottom-right']/descendant::div[@class='toast-body']")).getAttribute("innerHTML"), "Mensaje enviado satisfactoriamente.");
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