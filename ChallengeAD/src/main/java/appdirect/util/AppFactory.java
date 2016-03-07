package appdirect.util;

import java.util.Random;

/**
 *
 * @author fostic
 */
public class AppFactory {

    /**
     * Generates a random valid e-mail address
     *
     * @return 
     */
    public static String generateEmailRandom() {
        long currentTime = System.currentTimeMillis();
        return "appdirect." + currentTime + "@test.com";
    }
    

}
