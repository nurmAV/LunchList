package com.example.axelv.lunchlist.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.axelv.lunchlist.R;
import com.example.axelv.lunchlist.model.Restaurant;


/**
 * Represents a list of all the restaurants menus on a day
 */
public class DayFragment extends Fragment {

    Restaurant[] restaurants;
    int dayOfWeek;
    View view;
    LinearLayout menus;
    public DayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  =  inflater.inflate(R.layout.fragment_day, container, false);
        menus = view.findViewById(R.id.menus);


        Bundle args = getArguments();
        restaurants = (Restaurant[]) args.getSerializable("restaurants");
        dayOfWeek   = args.getInt("day_of_week");

        for(Restaurant restaurant: restaurants){
            Log.i("DayFragment", "" + dayOfWeek);
            menus.addView(new RestaurantView(restaurant, dayOfWeek, getActivity().getApplicationContext()));
        }


        return view;
    }
    /** Updates the view with the next days menus*/
    public void nextDay(){
        dayOfWeek = (dayOfWeek + 8) % 7;
        LinearLayout menus = view.findViewById(R.id.menus);
        menus.removeAllViews();
        for(Restaurant restaurant : restaurants) {
            menus.addView(new RestaurantView(restaurant, dayOfWeek, getActivity().getApplicationContext()));
            }
        }
    /** Updates the view with the previous days menus*/
    public void previousDay() {
        dayOfWeek = (dayOfWeek + 6) % 7;
        LinearLayout menus = view.findViewById(R.id.menus);
        menus.removeAllViews();
        for(Restaurant restaurant : restaurants) {
            menus.addView(new RestaurantView(restaurant, dayOfWeek, getActivity().getApplicationContext()));
        }
    }
}


