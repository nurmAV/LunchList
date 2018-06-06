package com.example.axelv.lunchlist;

import com.example.axelv.lunchlist.model.Restaurant;

public interface ResultHandler {

    void changeText(Restaurant[] restaurants);
    void updateProgress(int i);
}
