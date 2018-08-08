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
               // Log.i("LunchList", days[day]);
                for(int i = 0; i < dailyMenu.length(); i++){
                    String item = dailyMenu.getJSONObject(i).getString("title_fi");
                    Log.i("LunchList", item);
                    menu.addItem(item);
                }

                restaurant.setMenu(menu, day);


            }
            Menu saturdayMenu = new Menu();
            Menu sundayMenu   = new Menu();
            saturdayMenu.addItem("Ruokalista ei saatavilla");
            sundayMenu.addItem("Ruokalistaa ei saatavilla");
            restaurant.setMenu(saturdayMenu, 5);
            restaurant.setMenu(sundayMenu, 6);

            return restaurant;

        } catch (JSONException e) {
            Log.e("SodexoParser", e.getMessage());
            e.printStackTrace();
            Menu  menu = new Menu();
            menu.addItem("Ruokalista ei saatavilla");
            restaurant.setMenu(menu, 1);

        }
        finally{
            return restaurant;
        }

    }
}
