package com.example.axelv.lunchlist.parsers;

import android.util.Log;
import com.example.axelv.lunchlist.model.Menu;
import com.example.axelv.lunchlist.model.Restaurant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SodexoParser extends Parser {
    @Override
    public Restaurant parse(String json) {

        Restaurant restaurant = new Restaurant();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject meta = jsonObject.getJSONObject("meta");
            restaurant.setName(meta.getString("ref_title"));

            JSONObject menus = jsonObject.getJSONObject("menus");

            String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday"};

            for(int day = 0; day < days.length; day++){
                Menu menu = new Menu();
                JSONArray dailyMenu = menus.getJSONArray(days[day]);
                Log.i("LunchList", days[day]);
                for(int i = 0; i < dailyMenu.length(); i++){
                    String item = dailyMenu.getJSONObject(i).getString("title_fi");
                    Log.i("LunchList", item);
                    menu.addItem(item);
                }

                restaurant.setMenu(menu, day);


            }
            return restaurant;

        } catch (JSONException e) {
            Log.e("LunchList", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
