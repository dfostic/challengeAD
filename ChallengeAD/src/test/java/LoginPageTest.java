
import appdirect.pages.LoginPage;
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
public class LoginPageTest {

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
        driver.get(LoginPage.URL);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        driver.quit();  // Close the driver
    }

    @Test(testName = "Test-4")
    public void validateTwoLoginRedirectionLinks() {
        /**
         * Validate direct opening of login page https://www.appdirect.com/login
         */

        String loginUrl = driver.getCurrentUrl();
        String loginTitle = driver.getTitle();

        assertTrue(loginUrl.contains(LoginPage.URL_SECURE), String.format("Redirected page URL didn't match. "
                + "Expected: |%s| -> Received url: |%s|", LoginPage.URL_SECURE, loginUrl));
        assertTrue(loginTitle.equals(LoginPage.PAGE_TITLE), String.format("Page Title didn't match. "
                + "Expected: |%s| -> Received url: |%s|", LoginPage.PAGE_TITLE, loginTitle));

    }

    @Test(testName = "Test-5")
    public void validateSignUpBtnExists() {
        /**
         * Validate AppDirect Log In page has Sign Up button
         */
        int signUpBtn = driver.findElements(LoginPage.LNK_SIGNUP_BTN).size();
        assertEquals(signUpBtn, 1, "Sign Up Button element not found");
    }

    @Test(testName = "Test-6")
    public void validateSignUpAccountLink() {
        /**
         * Validate AppDirect Log In page has Sign up for an account link
         */
        int signUpBtn = driver.findElements(LoginPage.LNK_SIGNUP_ACC).size();
        assertEquals(signUpBtn, 1, "Sign up for an account link element not found");
    }

    @Test(testName = "Test-7")
    public void signUpRedirection() throws InterruptedException {
        /**
         * Validate Sign Up link button redirects to page https://www.appdirect.com/signup
         */

        driver.findElement(LoginPage.LNK_SIGNUP_BTN).click();

        long t = System.currentTimeMillis();
        long end = t + 5000;

        // Waiting for logini page to open
        while (System.currentTimeMillis() < end) {
            if (driver.getCurrentUrl().contains(LoginPage.URL_SECURE)) {
                Thread.sleep(1000);
            } else {
                break;
            }
        }

        String newPageUrl = driver.getCurrentUrl();
        String newPageTitle = driver.getTitle();

        assertTrue(newPageUrl.contains(SignupPage.URL_SECURE), String.format("Redirected page URL didn't match. "
                + "Expected: |%s| -> Received: |%s|", SignupPage.URL_SECURE, newPageUrl));
        assertTrue(newPageTitle.equals(SignupPage.PAGE_TITLE), String.format("Page Title didn't match. "
                + "Expected: |%s| -> Received url: |%s|", SignupPage.PAGE_TITLE, newPageTitle));

    }

    @Test(testName = "Test-8")
    public void signUpAccountRedirection() throws InterruptedException {
        /**
         * Validate “Sign up for an account” link of login page redirects to SignUp page
         */

        driver.findElement(LoginPage.LNK_SIGNUP_ACC).click();

        long t = System.currentTimeMillis();
        long end = t + 5000;

        // Waiting for logini page to open
        while (System.currentTimeMillis() < end) {
            if (!driver.getCurrentUrl().contains(LoginPage.URL_SECURE)) {
                break;
            } else {
                Thread.sleep(1000);
            }
        }

        String newPageUrl = driver.getCurrentUrl();
        String newPageTitle = driver.getTitle();

        assertTrue(newPageUrl.contains(SignupPage.URL_SECURE), String.format("Redirected page URL didn't match. "
                + "Expected: |%s| -> Received: |%s|", SignupPage.URL_SECURE, newPageUrl));
        assertTrue(newPageTitle.equals(SignupPage.PAGE_TITLE), String.format("Page Title didn't match. "
                + "Expected: |%s| -> Received url: |%s|", SignupPage.PAGE_TITLE, newPageTitle));
    }

}
