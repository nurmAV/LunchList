package com.example.axelv.lunchlist.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import com.example.axelv.lunchlist.R;
import com.example.axelv.lunchlist.model.Restaurant;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayFragment extends Fragment {


    public DayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_day, container, false);
        LinearLayout menus = view.findViewById(R.id.menus);

        Bundle args = getArguments();
        Restaurant[] restaurants = (Restaurant[]) args.getSerializable("restaurants");

        for(Restaurant restaurant: restaurants){
            menus.addView(new RestaurantView(restaurant, getActivity().getApplicationContext()));
        }


        return view;
    }

}
