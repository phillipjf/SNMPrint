/*package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import edu.auburn.eng.csse.comp3710.pjf0001.snmprint.R;

public class HttpDemo2 extends Activity implements Runnable
{

	public HttpDemo2()
	{
		Thread t = new Thread(this);
		t.start();
	}

	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.activity_main);
	}

	public void run()
	{
		String baseURL = "http://www.auburn.edu/~umphrda/index.html";

		try
		{
			URL theURL = new URL(baseURL);
			URLConnection theConnection = theURL.openConnection();
			HttpURLConnection theHttpConnection = (HttpURLConnection) theConnection;
			int returnCode = theHttpConnection.getResponseCode();
			Log.w("umphrda", "The response code is " + returnCode);
			if (returnCode != HttpURLConnection.HTTP_OK)
				Log.w("umphrda", "Bad response code");
			else
			{
				InputStream theInputStream = theConnection.getInputStream();
				Scanner theScanner = new Scanner(theInputStream);
				while (theScanner.hasNextLine())
				{
					Log.i("umphrda", theScanner.nextLine());
				}
			}
		}
		catch (Exception e)
		{
			Log.w("umphrda", "Exception raised: " + e.toString());
		}
	}
}*/





    
