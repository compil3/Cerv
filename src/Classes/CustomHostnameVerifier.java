package Classes;

import Cerv.MasterController;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/**
 * Name: Cerv
 * Created using Intellij IDEA
 * By:  Luca Tenuta
 * Date: 11/16/2015.
 * Time: 12:41 PM
 */
public class CustomHostnameVerifier {
    public static boolean returnHostnameVerified(MasterController controller, HttpsURLConnection connection) {
        try {
            HostnameVerifier hv = new HostnameVerifier() {
                public boolean verify(String arg0, SSLSession arg1) {
                    System.out.print("CustomHostnameVerifier triggered");
                    controller.getHostname(false);
                    return false;
                }
            };

            HttpsURLConnection.setDefaultHostnameVerifier(hv);
            controller.getHostname(true);

        } catch (Exception localException) {
            return false;
        }
        return false;
    }
}