package Classes;

import Cerv.MasterController;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;

/**
 * Name: Cerv
 * Created using Intellij IDEA
 * By:  Luca Tenuta
 * Date: 11/16/2015.
 * Time: 12:42 PM
 */
public class SSLValidCertificate {
    public static void returnValid(MasterController controller, URL url, HttpsURLConnection connection) throws SSLPeerUnverifiedException, CertificateNotYetValidException, CertificateExpiredException {
        try {
            Certificate[] certs = connection.getServerCertificates();
            for (Certificate cert : certs) {
                if (cert instanceof X509Certificate) {
                    X509Certificate sslCommon = (X509Certificate) cert;

                    try {
                        sslCommon.checkValidity();
                    } catch (CertificateExpiredException e){
                        controller.setValidSSL(false);
                    } catch (CertificateNotYetValidException e){
                        controller.setValidSSL(false);
                    }
                    controller.setValidSSL(true);
                    break;
                }
            }

        } catch (SSLPeerUnverifiedException e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }
}
