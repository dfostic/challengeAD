package appdirect.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author fostic
 */
public class SignupPage {

    // TODO: Create config file and config parser
    public static final String URL = "http://www.appdirect.com/signup";
    public static final String URL_SECURE = "https://www.appdirect.com/signup";
    public static final String PAGE_TITLE = "Sign Up for AppDirect";

    public static final String REGISTRATION_PAGE_URL = "https://www.appdirect.com/developers/register";
    public static final String REGISTRATION_PAGE_TITLE = "Register | AppDirect";

    public static final By LABEL_EMAIL = By.cssSelector("label[class=\"adb-label\"]");

    public static final By EMAIL_INPUT_PANEL = By.cssSelector("input[type=\"email\"]");

    public static final By INPUT_PANEL_PLACEHOLDER = By.cssSelector("input[placeholder=\"email@address.com\"]");

    public static final By BTN_SIGNUP = By.cssSelector("button[ga=\"userSignupButtonClick\"][type=\"submit\"][name=\"userSignupButton\"]");

    public static final By LNK_SIGNUP = By.cssSelector("a[href=\"./developers/register\"]");
    
    public static final By ELEMENT_ERROR = By.cssSelector("span[class=\"js-alert-container\"]");
    public static final String ERR_ALREADY_REGISTERED = "This email address has already been registered in our system. "
            + "Please register with a new email address.";
    
    public static final By ELEMENT_SUCCESS_HEADER = By.cssSelector("div[class=\"adb-local_alert--content\"] > h3");
    public static final String SIGNUP_SUCCESS_HEADER = "Thanks for registering.";

    public String errorForExistingEmail(WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String id = driver.findElement(By.cssSelector(".feedback-error")).getAttribute("id");
        WebElement element = (WebElement) executor.executeScript("return document.getElementById('" + id + "');");
        String errorMsg = (String) executor.executeScript("return arguments[0].textContent", element);
        return errorMsg;
    }

}
