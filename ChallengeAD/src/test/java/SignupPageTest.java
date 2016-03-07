
import appdirect.pages.SignupPage;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author fostic
 */
public class SignupPageTest {

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

    @Test(testName = "Test-9")
    public void validateTwoLoginRedirectionLinks() {
        /**
         * Validate AppDirect SignUp page has Email label
         */

        int emailLabel = driver.findElements(SignupPage.LABEL_EMAIL).size();
        assertEquals(emailLabel, 1, "EMAIL label not found");
    }

    @Test(testName = "Test-10")
    public void validateSignUpInputPanel() {
        /**
         * Validate Validate AppDirect SignUp input panel
         */

        int inputPanel = driver.findElements(SignupPage.EMAIL_INPUT_PANEL).size();
        assertEquals(inputPanel, 1, "EMAIL ADDRESS input panel not found");
    }

    @Test(testName = "Test-11")
    public void validateSignUpInputPanelPlaceholder() {
        /**
         * Validate AppDirect SignUp input panel has ‘email@address.com’ string example
         */

        int placeholder = driver.findElements(SignupPage.INPUT_PANEL_PLACEHOLDER).size();
        assertEquals(placeholder, 1, "Could not find input with placeholder \"email@address.com\"");
    }

    @Test(testName = "Test-12")
    public void validateSignUpButton() {
        /**
         * Validate AppDirect SignUp Form has Sign Up button
         */

        int signUpBtn = driver.findElements(SignupPage.BTN_SIGNUP).size();
        assertEquals(signUpBtn, 1, "Could not find Sign Up button");
    }

    @Test(testName = "Test-13")
    public void validateSignUpLink() {
        /**
         * Validate AppDirect SignUp Form has Sign Up link button
         */

        int signUpBtn = driver.findElements(SignupPage.LNK_SIGNUP).size();
        assertEquals(signUpBtn, 1, "Could not find Sign Up button");
    }

    @Test(testName = "Test-14")
    public void signUpLinkRedirection() throws InterruptedException {
        /**
         * Validate “Sign up for an account” link of login page redirects to SignUp page
         */

        driver.findElement(SignupPage.LNK_SIGNUP).click();

        long t = System.currentTimeMillis();
        long end = t + 5000;

        // Waiting for logini page to open
        while (System.currentTimeMillis() < end) {
            if (!driver.getCurrentUrl().contains(SignupPage.URL_SECURE)) {
                break;
            } else {
                Thread.sleep(1000);
            }
        }

        String newPageUrl = driver.getCurrentUrl();
        String newPageTitle = driver.getTitle();

        assertTrue(newPageUrl.contains(SignupPage.REGISTRATION_PAGE_URL), String.format("Redirected page URL didn't match. "
                + "Expected: |%s| -> Received: |%s|", SignupPage.REGISTRATION_PAGE_URL, newPageUrl));
        assertTrue(newPageTitle.equals(SignupPage.REGISTRATION_PAGE_TITLE), String.format("Page Title didn't match. "
                + "Expected: |%s| -> Received url: |%s|", SignupPage.REGISTRATION_PAGE_TITLE, newPageTitle));
    }

}
