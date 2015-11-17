package Classes;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Name: Cerv
 * Created using Intellij IDEA
 * By:  Luca Tenuta
 * Date: 11/16/2015.
 * Time: 12:42 PM
 */
public class URLValidation {
    public String urlVerifier (String url) throws MalformedURLException {

        if (url.contains("http://")) {
            System.out.println("Entered url contain HTTP");
            String[] httpStripped = url.split("http://");
            System.out.println("Stripped url: " + httpStripped[1]);
            url = "https://" + httpStripped[1];
        } else {
            if (!url.contains("https://")) {
                //System.out.println("Entered url doesn't contain HTTPS");
                url = "https://" + url;
                URL urlCheck = new URL(url);
            }
        }
        return url;
    }
}