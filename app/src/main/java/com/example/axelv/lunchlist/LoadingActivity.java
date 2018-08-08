package com.example.axelv.lunchlist;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.example.axelv.lunchlist.model.Restaurant;
import com.example.axelv.lunchlist.model.RestaurantType;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class LoadingActivity extends AppCompatActivity implements ResultHandler{
    TextView loadingText;
    int currentDayOfWeek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Calendar calendar = Calendar.getInstance();

        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        Log.i("MainActivity", "Current day of week: ");
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

        SimpleDateFormat amicaFormat = new SimpleDateFormat("yyyy-MM-dd");
        String amicaDate = amicaFormat.format(calendar.getTime());

        SimpleDateFormat sodexoFormat  = new SimpleDateFormat("yyyy/MM//dd");
        String sodexoDate = sodexoFormat.format(calendar.getTime());


        // Amica and sodexo restaurant IDs to be plugged into the URLs
        String[] amicaIDs = {"0190", "0199"}; // Alvari, TUAS
        String[] sodexoIDs = {"142", "26521", "13918", "140"}; // T-talo, Kvarkki, Valimo, Konetekniikka

        try{
            Tuple<URL, Integer>[] restaurants = new Tuple[amicaIDs.length + sodexoIDs.length];
            for(int i = 0; i < amicaIDs.length; i++ ) {
                String url = String.format("https://www.amica.fi/modules/json/json/Index?costNumber=%s&language=fi&firstDay=%s",amicaIDs[i], amicaDate);
                restaurants[i] = new Tuple<>(new URL(url), RestaurantType.AMICA);
            }
            for(int j = 0; j < sodexoIDs.length; j++ ) {
                String url = String.format("https://www.sodexo.fi/ruokalistat/output/weekly_json/%s/%s/fi",sodexoIDs[j], sodexoDate);
                restaurants[amicaIDs.length + j] = new Tuple<>(new URL(url), RestaurantType.SODEXO);
            }

            for(Tuple<URL, Integer> tuple: restaurants){
                Log.i("LunchList", tuple.first().toString() + " - " + tuple.second());
            }

        // Log.i("LunchList", amicaFormat.format(calendar.getTime()));
        // Initialize and execute the AsyncTask fetching the data
        DataFetchTask dft = new DataFetchTask();
        dft.handler = this;






        dft.execute(restaurants);








    } catch (MalformedURLException e) {
        e.printStackTrace();
    }

    }

    @Override
    public void onResult(Restaurant[] restaurants) {
        //Toast.makeText(this, "Result handled", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("restaurants", restaurants);
        startActivity(i);
    }
}
