package com.example.axelv.lunchlist;

import android.os.AsyncTask;
import android.util.Log;
import com.example.axelv.lunchlist.model.Restaurant;
import com.example.axelv.lunchlist.model.RestaurantType;
import com.example.axelv.lunchlist.parsers.AmicaParser;
import com.example.axelv.lunchlist.parsers.SodexoParser;


import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DataFetchTask extends AsyncTask<Tuple<URL, Integer>[], Void, Tuple<String, Integer>[]> {
    ResultHandler handler;

    @Override
    protected Tuple<String, Integer>[] doInBackground(Tuple<URL, Integer>[]... urls) {

        Tuple<URL, Integer>[] restaurantTuples = urls[0];
        Tuple<String, Integer>[] res = new Tuple[urls[0].length];

        for(int i = 0; i < restaurantTuples.length; i++) {
            try {
                Tuple<URL, Integer> tuple = restaurantTuples[i];
                Log.i("DataFetchTask", tuple.first().toString());
                HttpsURLConnection connection = (HttpsURLConnection) tuple.first().openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = reader.readLine();
                String json = "";

                while (line != null) {
                    json += line + "\n";
                    line = reader.readLine();
                }
                Log.i("LunchList", json);
                res[i] = new Tuple(json, tuple.second());
                //publishProgress(i +1);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    @Override
    protected void onPostExecute(Tuple<String, Integer>[] res){

        Restaurant[] restaurants = new Restaurant[res.length];
        for(int i = 0; i < res.length; i++) {
            Tuple<String, Integer> tuple = res[i];
            AmicaParser amicaParser = new AmicaParser();
            SodexoParser sodexoParser = new SodexoParser();

            // Find the correct parser
            switch(tuple.second()){
                case RestaurantType.AMICA:
                    restaurants[i] = amicaParser.parse(tuple.first());
                    break;
                case RestaurantType.SODEXO:
                    restaurants[i] = sodexoParser.parse(tuple.first());
                    break;
                default:
                    // Do nothing
            }

            handler.onResult(restaurants);

        }

    }

    //@Override
    //protected void onProgressUpdate(Integer... values){
      //      handler.updateProgress(values[0]);
    //}


}


