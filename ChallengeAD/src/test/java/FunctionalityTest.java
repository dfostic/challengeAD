
import appdirect.pages.HomePage;
import appdirect.pages.LoginPage;
import appdirect.pages.SignupPage;
import appdirect.util.AppFactory;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author fostic
 */
public class FunctionalityTest {

    public WebDriver driver;

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        driver = new ChromeDriver();    // Create a new instance of the Firefox driver
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(SignupPage.URL);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        driver.quit();  // Close the driver
    }

    public void ExtractJSLogs() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }   

    @Test(testName = "Test-15")
    public void invalidEmailVaidation() throws InterruptedException {
        /**
         * Validate already used e-mail address won’t be accepted
         */

        driver.findElement(SignupPage.EMAIL_INPUT_PANEL).sendKeys("testA@mailinator.com");
        driver.findElement(SignupPage.BTN_SIGNUP).click();
        
        WebElement errElement = driver.findElement(SignupPage.ELEMENT_ERROR);
        Thread.sleep(500);
        
        assertEquals(errElement.getText(), SignupPage.ERR_ALREADY_REGISTERED, String.format("Already registered error message. "
                + "Expected: |%s| -> Received: |%s|", SignupPage.ERR_ALREADY_REGISTERED, errElement.getText()));
    }   

    @Test(testName = "Test-17")
    public void validateValidEmailAddress() throws InterruptedException {
        /**
         * Validate already used e-mail address won’t be accepted
         */
        
        String email = AppFactory.generateEmailRandom();

        driver.findElement(SignupPage.EMAIL_INPUT_PANEL).sendKeys(email);
        driver.findElement(SignupPage.BTN_SIGNUP).click();
        Thread.sleep(500);
        
        WebElement successHeaderElement = driver.findElement(SignupPage.ELEMENT_SUCCESS_HEADER);
        
        assertEquals(successHeaderElement.getText(), SignupPage.SIGNUP_SUCCESS_HEADER, String.format("Success Registering. "
                + "Expected: |%s| -> Received: |%s|", SignupPage.SIGNUP_SUCCESS_HEADER, successHeaderElement.getText()));
    }
       

    @Test(testName = "Test-18A")
    public void validateLeadingSpaceTrimmed() throws InterruptedException {
        /**
         * Validate leading spaces are trimmed
         */
        
        String email = AppFactory.generateEmailRandom();
        email = "    " + email;

        driver.findElement(SignupPage.EMAIL_INPUT_PANEL).sendKeys(email);
        driver.findElement(SignupPage.BTN_SIGNUP).click();
        Thread.sleep(500);
        
        WebElement successHeaderElement = driver.findElement(SignupPage.ELEMENT_SUCCESS_HEADER);
        
        assertEquals(successHeaderElement.getText(), SignupPage.SIGNUP_SUCCESS_HEADER, String.format("Success Registering. "
                + "Expected: |%s| -> Received: |%s|", SignupPage.SIGNUP_SUCCESS_HEADER, successHeaderElement.getText()));
    }
       

    @Test(testName = "Test-18B")
    public void validateTrailingSpaceTrimmed() throws InterruptedException {
        /**
         * Validate trailing spaces are trimmed
         */
        
        String email = AppFactory.generateEmailRandom();
        email = email + "     ";

        driver.findElement(SignupPage.EMAIL_INPUT_PANEL).sendKeys(email);
        driver.findElement(SignupPage.BTN_SIGNUP).click();
        Thread.sleep(500);
        
        WebElement successHeaderElement = driver.findElement(SignupPage.ELEMENT_SUCCESS_HEADER);
        
        assertEquals(successHeaderElement.getText(), SignupPage.SIGNUP_SUCCESS_HEADER, String.format("Success Registering. "
                + "Expected: |%s| -> Received: |%s|", SignupPage.SIGNUP_SUCCESS_HEADER, successHeaderElement.getText()));
    }
       

    @Test(testName = "Test-18C")
    public void validateleadingAndTrailingSpaceTrimmed() throws InterruptedException {
        /**
         * Validate leading and trailing spaces are trimmed
         */
        
        String email = AppFactory.generateEmailRandom();
        email = "     " + email + "     ";

        driver.findElement(SignupPage.EMAIL_INPUT_PANEL).sendKeys(email);
        driver.findElement(SignupPage.BTN_SIGNUP).click();
        Thread.sleep(500);
        
        WebElement successHeaderElement = driver.findElement(SignupPage.ELEMENT_SUCCESS_HEADER);
        
        assertEquals(successHeaderElement.getText(), SignupPage.SIGNUP_SUCCESS_HEADER, String.format("Success Registering. "
                + "Expected: |%s| -> Received: |%s|", SignupPage.SIGNUP_SUCCESS_HEADER, successHeaderElement.getText()));
    }
       

    @Test(testName = "Test-19", priority=1)
    public void signUpEnd2End() throws InterruptedException {
        /**
         * Validate SignUp from home page
         */
        
        driver.get(HomePage.URL);
        driver.findElement(HomePage.LNK_LOGIN_TOP).click();
        Thread.sleep(1000);
        driver.findElement(LoginPage.LNK_SIGNUP_BTN).click();
        Thread.sleep(1000);
        
        String email = AppFactory.generateEmailRandom();

        driver.findElement(SignupPage.EMAIL_INPUT_PANEL).sendKeys(email);
        driver.findElement(SignupPage.BTN_SIGNUP).click();
        Thread.sleep(500);
        
        WebElement successHeaderElement = driver.findElement(SignupPage.ELEMENT_SUCCESS_HEADER);
        
        assertEquals(successHeaderElement.getText(), SignupPage.SIGNUP_SUCCESS_HEADER, String.format("Success Registering. "
                + "Expected: |%s| -> Received: |%s|", SignupPage.SIGNUP_SUCCESS_HEADER, successHeaderElement.getText()));
    }
}
