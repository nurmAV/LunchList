package com.example.axelv.lunchlist.parsers;

import com.example.axelv.lunchlist.model.Restaurant;

public abstract class Parser {

    /**
     * @param json The JSON string  containing the restaurants information
     * @return A restaurant instance with the data
     * */

    public abstract Restaurant parse(String json);
}
