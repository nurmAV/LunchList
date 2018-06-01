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
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            Log.i("LunchList", sdf.format(calendar.getTime()));
            // Initialize and execute the AsyncTask fetching the data
            DataFetchTask dft = new DataFetchTask();
            dft.handler = this;
            dft.execute(new URL("http://www.amica.fi/modules/json/json/Index?costNumber=0190&language=fi&firstDay=" + sdf.format(calendar.getTime())));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
    public void changeText(String s) {
        //TextView tw = findViewById(R.id.textview);
        AmicaParser parser = new AmicaParser();
        Restaurant restaurant = parser.parse(s);


        LinearLayout layout =  findViewById(R.id.linearLayout);
        layout.addView(new RestaurantView(restaurant, getApplicationContext()));
    }
}
