package com.example.axelv.lunchlist.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Menu implements Serializable {

    private ArrayList<String> items = new ArrayList<>();

    public void addItem(String item) {
        items.add(item);
    }
    public String[] getItems() {
        String[] res = new String[items.size()];
        for(int i = 0; i < items.size(); i++){
            res[i] = items.get(i);
        }
        return res;
    }
}
