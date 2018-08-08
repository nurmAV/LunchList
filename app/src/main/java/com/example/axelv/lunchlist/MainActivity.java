package com.example.axelv.lunchlist;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.example.axelv.lunchlist.model.Restaurant;
import com.example.axelv.lunchlist.model.RestaurantType;
import com.example.axelv.lunchlist.views.DayFragment;


import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity{


        Calendar calendar;
        int currentDayOfWeek;
        Restaurant[] restaurants;
        Button tomorrowButton, yesterdayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = Calendar.getInstance();
        currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        Restaurant[] restaurants = (Restaurant[]) getIntent().getSerializableExtra("restaurants");
        FrameLayout frame = findViewById(R.id.fragmentContainer);
        FragmentManager manager = getSupportFragmentManager();
        DayFragment dayFragment = new DayFragment();
        Bundle args = new Bundle();
        args.putSerializable("restaurants", restaurants);
        args.putInt("day_of_week", resolveDay(currentDayOfWeek));
        Log.i("MainActivity", Integer.toString(currentDayOfWeek));
        dayFragment.setArguments(args);
        manager.beginTransaction().add(R.id.fragmentContainer, dayFragment).commit();


    }
    public void changeText(Restaurant[] restaurants) {

        FrameLayout frame = findViewById(R.id.fragmentContainer);
        FragmentManager manager = getSupportFragmentManager();
        DayFragment dayFragment = new DayFragment();
        Bundle args = new Bundle();
        args.putSerializable("restaurants", restaurants);
        args.putInt("day_of_week", resolveDay(currentDayOfWeek));
        Log.i("MainActivity", Integer.toString(currentDayOfWeek));
        dayFragment.setArguments(args);
        manager.beginTransaction().add(R.id.fragmentContainer, dayFragment).commit();
    }

    public void updateProgress(int i){
        //TextView loadingText = findViewById(R.id.loadingText);
        //loadingText.setText("Loading... " + i + "/6" );
    }

    /**  Read*/
    private int resolveDay(int day){
        if(day == 1) return 6;
        else return day - 2;
    }

    public void increaseDay() {
        currentDayOfWeek = (currentDayOfWeek  + 8) % 7;
    }
    public void decreaseDay() {
        currentDayOfWeek = (currentDayOfWeek + 6) % 7;
    }

    private void changeDay(int day) {
        DayFragment df  = new DayFragment();
        Bundle args = new Bundle();
        args.putInt("day_of_week", day);
        //df.setArguments(n);

    }




}


