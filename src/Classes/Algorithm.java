package Classes;

import Cerv.MasterController;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.regex.Pattern;

/**
 * Name: Cerv
 * Created using Intellij IDEA
 * By:  Luca Tenuta
 * Date: 11/16/2015.
 * Time: 12:40 PM
 */
public class Algorithm {
    public static void returnAlgo(MasterController controller, URL url, HttpsURLConnection connection) throws SSLPeerUnverifiedException {
        try {
            Certificate[] certs = connection.getServerCertificates();
            for (Certificate cert : certs) {
                if (cert instanceof X509Certificate) {
                    X509Certificate sslCommon = (X509Certificate) cert;

                    String commonNameLong = sslCommon.getSubjectDN().getName();
                    Pattern orgRegex = Pattern.compile("[A-Z]*\\=");
                    String[] serialStrip = orgRegex.split(sslCommon.getSubjectDN().getName());
                    System.out.println("Algo: " + sslCommon.getSigAlgName());

                    controller.setSslAlgo(sslCommon.getSigAlgName());
                    break;
                }
            }

        } catch (SSLPeerUnverifiedException e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }
}

