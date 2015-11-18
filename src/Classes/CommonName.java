package Classes;

import Cerv.MasterController;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Name: Cerv
 * Created using Intellij IDEA
 * By:  Luca Tenuta
 * Date: 11/16/2015.
 * Time: 12:41 PM
 */
public class CommonName {
    public static void returnCommonName(MasterController controller, URL url, HttpsURLConnection connection, Error errorAlert)  throws IOException {
        try {
            Certificate[] certs = connection.getServerCertificates();
            for (Certificate cert : certs) {
                if (cert instanceof X509Certificate) {
                    X509Certificate sslCommon = (X509Certificate) cert;

                    //regex to strip everythting but common name
                    String commonNameLong = sslCommon.getSubjectDN().getName();
                    Pattern cnNameStripper = Pattern.compile("[A-Z]*\\=");
                    String[] commmonName = cnNameStripper.split(sslCommon.getSubjectDN().getName());
                    System.out.println("Common Name: " + commmonName[1].replace(",", ""));
                    controller.setCommonName(commmonName[1].replace(",", ""));
                    break;
                }
            }
        }catch (IOException ex) {
            errorAlert.errorAlert("Connection error", ex);
        }
    }
}
