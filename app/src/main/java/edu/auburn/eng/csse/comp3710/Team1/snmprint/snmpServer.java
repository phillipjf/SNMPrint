package edu.auburn.eng.csse.comp3710.Team1.snmprint;
/*
 * http://hosteddocs.ittoolbox.com/KA110807.pdf
 * http://www.oidview.com/mibs/0/Printer-MIB.html
 */

import android.app.Activity;
import java.net.InetAddress;
import snmp.*;

public class snmpServer extends Activity {

    /*
     * This method process the request and Get the Value on the device
     * @returns String. community=READ_COMMUNITY
     */
    public int getPrintStatus(String strIPAddress, String community, int iSNMPVersion, String strOID) {
        //String str = "";
        int str = -1;
        //int printValues = -1;
        try {
            InetAddress hostAddress = InetAddress.getByName(strIPAddress);

            SNMPv1CommunicationInterface comInterface = new SNMPv1CommunicationInterface(iSNMPVersion, hostAddress, community);

            // returned Vector has just one pair inside it.
            SNMPVarBindList newVars = comInterface.getMIBEntry(strOID);

            // extract the (OID,value) pair from the SNMPVarBindList; the pair is just a two-element SNMPSequence
            SNMPSequence pair = (SNMPSequence) (newVars.getSNMPObjectAt(0));

            // extract the object identifier from the pair; it's the first element in the sequence
            //SNMPObjectIdentifier snmpOID = (SNMPObjectIdentifier)pair.getSNMPObjectAt(0);

            //extract the corresponding value from the pair; it's the second element in the sequence
            SNMPObject snmpValue = pair.getSNMPObjectAt(1);

            str = Integer.valueOf(snmpValue.toString());
        } catch (Exception e) {
            System.out.println("Exception during SNMP operation: " + e + "\n");
        }
        return str;
    }
} 

