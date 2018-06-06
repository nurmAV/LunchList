package com.example.axelv.lunchlist;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.axelv.lunchlist.model.Menu;
import com.example.axelv.lunchlist.model.Restaurant;
import com.example.axelv.lunchlist.model.RestaurantType;
import com.example.axelv.lunchlist.parsers.AmicaParser;
import com.example.axelv.lunchlist.parsers.SodexoParser;
import com.example.axelv.lunchlist.views.RestaurantView;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements ResultHandler{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            // Get the first day of the week
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);

            calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

            SimpleDateFormat amicaFormat = new SimpleDateFormat("yyyy-MM-dd");
            String amicaDate = amicaFormat.format(calendar.getTime());

            SimpleDateFormat sodexoFormat  = new SimpleDateFormat("yyyy/MM//dd");
            String sodexoDate = sodexoFormat.format(calendar.getTime());


            // Amica and sodexo restaurant IDs to be plugged into the URLs
            String[] amicaIDs = {"0190", "0199"}; // Alvari, TUAS
            String[] sodexoIDs = {"142", "26521","13918", "140"}; // T-talo, Kvarkki, Valimo, Konetekniikka

            Tuple<URL, Integer>[] restaurants = new Tuple[amicaIDs.length + sodexoIDs.length];
            for(int i = 0; i < amicaIDs.length; i++ ) {
                String url = String.format("http://www.amica.fi/modules/json/json/Index?costNumber=%s&language=fi&firstDay=%s",amicaIDs[i], amicaDate);
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
            // Initialize Sodexo reguest







        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
    public void changeText(Restaurant[] restaurants) {
        //TextView tw = findViewById(R.id.textview);
        /*AmicaParser amicaParser = new AmicaParser();
        SodexoParser sodexoParser = new SodexoParser();
        Restaurant restaurant = sodexoParser.parse(s);*/

        // Hide the loading text
       // TextView loadingText = findViewById(R.id.loadingText);
       // loadingText.setText("");

        //Find the linear layout in which to put the restaurants
        LinearLayout layout =  findViewById(R.id.linearLayout);
        for(Restaurant restaurant : restaurants)
            layout.addView(new RestaurantView(restaurant, getApplicationContext()));
    }

    public void updateProgress(int i){
        //TextView loadingText = findViewById(R.id.loadingText);
        //loadingText.setText("Loading... " + i + "/6" );
    }
}
