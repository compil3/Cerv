package Classes;

import Cerv.MasterController;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Name: Cerv
 * Created using Intellij IDEA
 * By:  Luca Tenuta
 * Date: 11/16/2015.
 * Time: 12:41 PM
 */
public class IpAddress {

    public static void returnIpAddress(MasterController controller, String url) {
        try {
            Inet4Address address = (Inet4Address) Inet4Address.getByName(url);
            System.out.println("IP Address From Method: " + address.getHostAddress());
            controller.setIpAddress(address.getHostAddress());

        } catch (IOException ex) {
            Logger.getLogger(IpAddress.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}