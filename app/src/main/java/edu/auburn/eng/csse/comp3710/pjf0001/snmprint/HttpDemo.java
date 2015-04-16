/*
package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import edu.auburn.eng.csse.comp3710.pjf0001.snmprint.R;

public class HttpDemo extends Activity
{

    ImageView theImageView;
    Button theButton;
    String imageUrl = "http://swemac.cse.eng.auburn.edu/~umphrda/comp3710/content/";
    int pictureNumber;
    Bitmap theImage;
    AsyncTask theTask = new DownloadPicTask();

    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.main);

        theButton = (Button) findViewById(R.id.get_imagebt);
        theImageView = (ImageView) findViewById(R.id.imview);
        pictureNumber = -1;


        theButton.setOnClickListener(new Button.OnClickListener()
        {

            public void onClick(View v)
            {
                pictureNumber = (pictureNumber + 1) % 4;
                String fileName = imageUrl + "png" + pictureNumber + ".png";

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected())
                {
                        new DownloadPicTask().execute(fileName);
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "no pic available", Toast.LENGTH_SHORT);
                    toast.show();
                }

                Log.i("DEBUG", fileName);
            }
        });
    }

    private class DownloadPicTask extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... url)
        {
            return downloadFile(url[0]);
        }

        protected void onProgressUpdate()
        {
        }

        protected void onPostExecute(Bitmap bigmap)
        {
            theImageView.setImageBitmap(bigmap);
        }

    }

    private Bitmap downloadFile(String theURL)
    {

        URL myFileUrl = null;
        try
        {
            myFileUrl = new URL(theURL);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        try
        {
            HttpURLConnection theConnection = (HttpURLConnection) myFileUrl.openConnection();
            theConnection.setDoInput(true);
            theConnection.connect();
            Log.i("umphrda", "URL = " + theURL);
            int length = theConnection.getContentLength();
            Log.i("umphrda", "file length = " + length);
            InputStream theInputStream = theConnection.getInputStream();

            theImage = BitmapFactory.decodeStream(theInputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Log.i("umphrda", e.toString());
            theImage = null;
        }
        return theImage;
    }
}
*/