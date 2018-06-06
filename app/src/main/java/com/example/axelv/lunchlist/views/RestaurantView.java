package com.example.axelv.lunchlist.views;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.axelv.lunchlist.model.Menu;
import com.example.axelv.lunchlist.model.Restaurant;

public class RestaurantView extends LinearLayout {

    private Restaurant restaurant;

    public RestaurantView(Restaurant restaurant, Context context){
        super(context);
        //this.setBackgroundColor(Color.rgb(230,230,230));
        this.restaurant = restaurant;
        this.setOrientation(LinearLayout.VERTICAL);
        this.setPadding(5,10,5,10);
        TextView restaurantName = new TextView(context);
        restaurantName.setText(restaurant.getName());
        restaurantName.setGravity(Gravity.CENTER_HORIZONTAL);
        restaurantName.setTextColor(Color.rgb(5,5,5));
        restaurantName.setPadding(50,50,50,50);
        restaurantName.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50);
        restaurantName.setBackgroundColor(Color.rgb(150,150,150));

        this.addView(restaurantName);
        Log.i("LunchList", restaurant.getName());
        Menu menu = restaurant.getMenu(0);
        for(String s : menu.getItems()){
            TextView tw = new TextView(context);
            tw.setText(s);
            tw.setTextColor(Color.rgb(150,150,150));
            tw.setPadding(10, 10, 10, 10);
            tw.setBackgroundColor(Color.rgb(200, 200, 200));
            this.addView(tw);
        }
    }
}
