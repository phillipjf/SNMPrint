package edu.auburn.eng.csse.comp3710.Team1.snmprint;

public class Printer {
    String mName = "";
    String mIpAddr = "";
    long mPrinterID = -1;

    int cVal = -1;
    int mVal = -1;
    int yVal = -1;
    int kVal = -1;

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


}
