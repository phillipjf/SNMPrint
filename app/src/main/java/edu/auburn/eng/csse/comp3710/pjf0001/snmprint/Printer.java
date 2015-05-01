package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

public class Printer {
        String mName = "";
        String mIpAddr = "";
        long mPrinterID = -1;

    /*********** Set Methods ******************/

    public void setPrinterName(String PrinterName)
    {
        this.mName = PrinterName;
    }

    public void setID(long pID)
    {
        this.mPrinterID = pID;
    }

    public void setIP(String pIP)
    {
        this.mIpAddr = pIP;
    }

    /*********** Get Methods ****************/

    public String getPrinterName()
    {
        return this.mName;
    }

    public long getID()
    {
        return this.mPrinterID;
    }

    public String getIP()
    {
        return this.mIpAddr;
    }


    @Override
    public String toString() {
        return "ID: " + mPrinterID + " | NAME: " + mName + " | IP: 131.204.116." + mIpAddr;
    }

}
