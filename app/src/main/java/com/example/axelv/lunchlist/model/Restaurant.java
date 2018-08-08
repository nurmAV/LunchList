package com.example.axelv.lunchlist.model;

import java.io.Serializable;

public class Restaurant implements Serializable {

    private Menu[] menus = new Menu[7];
    private String openingHours;
    private String name;

    public Restaurant() {
        for(int i = 0; i < 7; i++){
            Menu menu = new Menu();
            menu.addItem("Ruokalista ei saatavilla");
            this.setMenu(menu, i);
        }
    }



    public void setMenu(Menu menu, int day) {
        this.menus[day] = menu;
    }
    public Menu getMenu(int day) {
        return menus[day];
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }
    public String getOpeningHours() {
        return openingHours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


