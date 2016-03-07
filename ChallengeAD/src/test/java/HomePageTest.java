
import appdirect.pages.HomePage;
import appdirect.pages.LoginPage;
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
public class HomePageTest {

    public WebDriver driver;

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        driver = new ChromeDriver();    // Create a new instance of the Firefox driver
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(HomePage.URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        driver.quit();  // Close the driver
    }

    @Test(testName = "Test-1")
    public void validateTwoLoginRedirectionLinks() {
        /**
         * Validate AppDirect home page has 2 links for LOG IN
         */
        int loginLinksNo = driver.findElements(HomePage.LNK_LOGIN_MATCH).size();
        assertEquals(loginLinksNo, 2);
    }

    @Test(testName = "Test-2")
    public void loginLinkTopRedirect() throws InterruptedException {
        /**
         * Validate Upper LOG IN link button redirects to login page https://www.appdirect.com/login
         */

        driver.findElement(HomePage.LNK_LOGIN_TOP).click();

        long t = System.currentTimeMillis();
        long end = t + 5000;

        // Waiting for logini page to open
        while (System.currentTimeMillis() < end) {
            if (!driver.getCurrentUrl().equals(HomePage.URL)) {
                break;
            } else {
                Thread.sleep(1000);
            }
        }

        String newPageUrl = driver.getCurrentUrl();
        String newPageTitle = driver.getTitle();

        assertTrue(newPageUrl.contains(LoginPage.URL_SECURE), String.format("Redirected page URL didn't match. "
                + "Expected: %s -> Received url: %s", LoginPage.URL_SECURE, newPageUrl));
        assertTrue(newPageTitle.equals(LoginPage.PAGE_TITLE), String.format("Page Title didn't match. "
                + "Expected: %s -> Received url: %s", LoginPage.PAGE_TITLE, newPageTitle));

    }

    @Test(testName = "Test-3")
    public void loginLinkBottomRedirect() throws InterruptedException {
        /**
         * Validate Lower LOG IN link button redirects to login page https://www.appdirect.com/login
         */

        driver.findElement(HomePage.LNK_LOGIN_BOTTOM).click();

        long t = System.currentTimeMillis();
        long end = t + 5000;

        // Waiting for logini page to open
        while (System.currentTimeMillis() < end) {
            if (!driver.getCurrentUrl().equals(HomePage.URL)) {
                break;
            } else {
                Thread.sleep(1000);
            }
        }

        String newPageUrl = driver.getCurrentUrl();
        String newPageTitle = driver.getTitle();

        assertTrue(newPageUrl.contains(LoginPage.URL_SECURE), String.format("Redirected page URL didn't match. "
                + "Expected: %s -> Received url: %s", LoginPage.URL_SECURE, newPageUrl));
        assertTrue(newPageTitle.equals(LoginPage.PAGE_TITLE), String.format("Page Title didn't match. "
                + "Expected: %s -> Received url: %s", LoginPage.PAGE_TITLE, newPageTitle));
    }

}
