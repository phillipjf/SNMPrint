package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

public class Printer {
    String mName = "";
    String mIpAddr = "";
    int kVal = 0;
    int cVal = 0;
    int mVal = 0;
    int yVal = 0;
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

    public void setkVal(int bVal) {
        this.kVal = bVal;
    }

    public void setcVal(int cVal) {
        this.cVal = cVal;
    }

    public void setmVal(int mVal) {
        this.mVal = mVal;
    }

    public void setyVal(int yVal) {
        this.yVal = yVal;
    }

    /*********** Get Methods ****************/

    public String getPrinterName()
    {
        return this.mName;
    }

    public String getIP()
    {
        return this.mIpAddr;
    }

    public long getID()
    {
        return this.mPrinterID;
    }

    public int getyVal() {
        return yVal;
    }

    public int getmVal() {
        return mVal;
    }

    public int getcVal() {
        return cVal;
    }

    public int getkVal() {
        return kVal;
    }
    @Override
    public String toString() {
        return "ID: " + mPrinterID + " | NAME: " + mName + " | IP: 131.204.116." + mIpAddr;
    }

}
