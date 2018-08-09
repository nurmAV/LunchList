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
import android.widget.TextView;
import com.example.axelv.lunchlist.model.Restaurant;
import com.example.axelv.lunchlist.model.RestaurantType;
import com.example.axelv.lunchlist.views.DayFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

        private Calendar calendar;
        private int currentDayOfWeek;
        private Restaurant[] restaurants;
        private Button tomorrowButton, yesterdayButton;
        private TextView dayName;
        private String[] dayNames = {"Maanantai", "Tiistai", "Keskiviikko", "Torstai", "Perjantai", "Lauantai", "Sunnuntai"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up constants
        calendar                      = Calendar.getInstance();
        currentDayOfWeek              = resolveDay(calendar.get(Calendar.DAY_OF_WEEK));
        restaurants                   = (Restaurant[]) getIntent().getSerializableExtra("restaurants");
        FrameLayout frame             = findViewById(R.id.fragmentContainer);
        FragmentManager manager       = getSupportFragmentManager();
        final DayFragment dayFragment = new DayFragment();
        Bundle args                   = new Bundle();

        // Set arguments for the DayFragment
        args.putSerializable("restaurants", restaurants);
        args.putInt("day_of_week", currentDayOfWeek);
        dayFragment.setArguments(args);
        manager.beginTransaction().add(R.id.fragmentContainer, dayFragment).commit();

        // UI elements
        dayName         = findViewById(R.id.dayOfWeek);
        tomorrowButton  = findViewById(R.id.tomorrow);
        yesterdayButton = findViewById(R.id.yesterday);
        dayName.setText(dayNames[currentDayOfWeek]);
        tomorrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayFragment.nextDay();
                currentDayOfWeek = (currentDayOfWeek + 8) % 7;
                dayName.setText(dayNames[currentDayOfWeek]);
            }
        });

        yesterdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayFragment.previousDay();
                currentDayOfWeek = (currentDayOfWeek + 6) % 7;
                dayName.setText(dayNames[currentDayOfWeek]);
            }
        });


    }
    /** Maps the calendar day of week to its corresponding restaurant array index */
    private int resolveDay(int day){
        if(day == 1) return 6;
        else return day - 2;
    }

}


