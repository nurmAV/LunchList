package com.example.axelv.lunchlist.model;

public class Restaurant {

    private Menu[] menus = new Menu[7];
    private String openingHours;
    private String name;

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


