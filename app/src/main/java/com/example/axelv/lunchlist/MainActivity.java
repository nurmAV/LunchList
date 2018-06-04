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

           // Log.i("LunchList", amicaFormat.format(calendar.getTime()));
            // Initialize and execute the AsyncTask fetching the data
            DataFetchTask dft = new DataFetchTask();
            dft.handler = this;
            URL[] amicaURLs = new URL[amicaIDs.length];
            URL[] sodexoURLs = new URL[sodexoIDs.length];

            /*for(int i = 0; i < amicaUrls.length; i++){
                amicaUrls[i] = new URL(
                    String.format("http://www.amica.fi/modules/json/json/Index?costNumber=%s&language=fi&firstDay=%s", amicaIDs[i], amicaDate)
                );
            }*/
            for(int j = 0; j < sodexoURLs.length; j++){
                sodexoURLs[j] = new URL(
                        String.format("https://www.sodexo.fi/ruokalistat/output/weekly_json/%s/%s/fi",sodexoIDs[j], sodexoDate)
                );
            }


            //dft.execute(new URL("http://www.amica.fi/modules/json/json/Index?costNumber=0190&language=fi&firstDay=" + sdf.format(calendar.getTime())));
            dft.execute(sodexoURLs);
            // Initialize Sodexo reguest







        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
    public void changeText(String s) {
        //TextView tw = findViewById(R.id.textview);
        AmicaParser amicaParser = new AmicaParser();
        SodexoParser sodexoParser = new SodexoParser();
        Restaurant restaurant = sodexoParser.parse(s);


        LinearLayout layout =  findViewById(R.id.linearLayout);
        layout.addView(new RestaurantView(restaurant, getApplicationContext()));
    }
}
