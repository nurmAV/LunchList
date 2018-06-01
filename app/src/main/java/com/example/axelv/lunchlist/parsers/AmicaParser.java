package com.example.axelv.lunchlist.parsers;

import android.util.Log;
import com.example.axelv.lunchlist.model.Menu;
import com.example.axelv.lunchlist.model.Restaurant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Handles the parsing of JSON coming from Amica restaurants
 */
public class AmicaParser extends Parser {

    @Override
    public Restaurant parse(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            Restaurant restaurant = new Restaurant();
            restaurant.setName(jsonObject.getString("RestaurantName"));
            JSONArray menusForDays =jsonObject.getJSONArray("MenusForDays");

            for(int i = 0; i< menusForDays.length(); i++){
                JSONObject object = menusForDays.getJSONObject(i);
                JSONArray array = object.getJSONArray("SetMenus");
                Menu menu = new Menu();

                for(int j = 0; j < array.length(); j++){
                    JSONObject menuForDayObject = new JSONObject(array.getString(j));
                    JSONArray components = menuForDayObject.getJSONArray("Components");

                    for(int k = 0; k < components.length(); k++){
                        menu.addItem(components.getString(k));
                    }


                }
                restaurant.setMenu(menu, i);
            }


            return restaurant;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
