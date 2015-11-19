package Classes;

import Cerv.MasterController;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

/**
 * Name: Cerv
 * Created using Intellij IDEA
 * By:  Luca Tenuta
 * Date: 11/16/2015.
 * Time: 12:41 PM
 */
public class MXRecord {
    public static void mxLookup(MasterController controller, String host) throws TextParseException {
        Record[] records = new Lookup(host, Type.MX).run();
        for (Record record : records) {
            controller.setMXRecord(record.getAdditionalName());
       }
    }
}