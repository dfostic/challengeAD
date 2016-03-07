package appdirect.pages;

import org.openqa.selenium.By;

/**
 * This class is created for all elements on home page that may be of interest
 * To be added any element in future
 *
 * @author fostic
 */
public class HomePage {
    
    //TODO: Create config file and config parser
    public static final String URL = "http://www.appdirect.com/";

    public static final By LNK_LOGIN_TOP = By.xpath("//*[@id=\"newnav\"]/header/div/menu/ul/li[3]/a");

    public static final By LNK_LOGIN_BOTTOM = By.xpath("/html/body/footer/div/div[2]/div[1]/ul[3]/li[4]/a");

    public static final By LNK_LOGIN_MATCH = By.xpath("//a[contains(@href, 'https://www.appdirect.com/login')]");

}
