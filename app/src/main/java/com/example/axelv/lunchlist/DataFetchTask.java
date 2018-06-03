package com.example.axelv.lunchlist;

import android.os.AsyncTask;
import android.util.Log;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DataFetchTask extends AsyncTask<URL[], Void, String> {
    ResultHandler handler;

    @Override
    protected String doInBackground(URL[]... urls) {
        try {
            URLConnection connection = urls[0][0].openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = reader.readLine();
            String res = "";

            while(line != null){
                res +=  line + "\n";
                line = reader.readLine();
            }
            return res;


        } catch (IOException e) {
            e.printStackTrace();
        }
      return "";
    }

    @Override
    protected void onPostExecute(String res){
        Log.i("LunchList", res);
        handler.changeText(res);


    }
}


