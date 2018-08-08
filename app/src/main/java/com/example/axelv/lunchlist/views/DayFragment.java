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

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayFragment extends Fragment {



    Restaurant[] restaurants;
    int dayOfWeek;
    View view;
    public DayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_day, container, false);
        LinearLayout menus = view.findViewById(R.id.menus);
        TextView dayName =  new TextView(getActivity().getApplicationContext());
        Calendar calendar = Calendar.getInstance();

        //dayName.setText()

        //menus.addView(dayName);
        Bundle args = getArguments();
        restaurants = (Restaurant[]) args.getSerializable("restaurants");
        dayOfWeek = args.getInt("day_of_week");

        for(Restaurant restaurant: restaurants){
            Log.i("DayFragment", "" + dayOfWeek);
            menus.addView(new RestaurantView(restaurant, dayOfWeek, getActivity().getApplicationContext()));
        }


        return view;
    }

    public void nextDay(){
        dayOfWeek = (dayOfWeek + 8) % 7;
        LinearLayout menus = view.findViewById(R.id.menus);
        menus.removeAllViews();
        for(Restaurant restaurant : restaurants) {
            menus.addView(new RestaurantView(restaurant, dayOfWeek, getActivity().getApplicationContext()));
        }

        }
    }


