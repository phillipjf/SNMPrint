package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

public class Printer {
    String mIpAddr;
    String mName;
    long mPrinterID;

    public Printer(String name, String ipAddr){
        mName = name;
        mIpAddr = ipAddr;
        mPrinterID = -1;
    }
    @Override
    public String toString() {
        return mName;
    }

}
