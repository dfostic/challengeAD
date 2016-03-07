package appdirect.pages;

import org.openqa.selenium.By;

/**
 * This class is created for all elements on Login page that may be of interest. To be added any element in future
 *
 * @author fostic
 */
public class LoginPage {

    // TODO: Create config file and config parser
    public static final String URL = "http://www.appdirect.com/login";
    public static final String URL_SECURE = "https://www.appdirect.com/login";
    public static final String PAGE_TITLE = "Log In | AppDirect";

    public static final By LNK_SIGNUP_BTN = By.cssSelector("a[ga=\"loginToSignupClick\"][href=\"./signup\"]");

    public static final By LNK_SIGNUP_ACC = By.cssSelector("a[href=\"/signup\"]");

    //*[@id="id4"]/div/section/div/p/a
}
