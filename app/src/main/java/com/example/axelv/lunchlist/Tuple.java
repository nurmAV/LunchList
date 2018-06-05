package com.example.axelv.lunchlist;

/**
 * Represents a pair of two values
 * @param <T> The first value of the pair
 * @param <E> The second value of the pair
 */
public class Tuple<T,E> {

    private T x;
    private E y;
    public  Tuple(T param1, E param2){

        x = param1;
        y = param2;
    }

    /**
     * @return The first element of the tuple
     */
    public T first() {
        return x;
    }

    /**
     * @return The second element of the tuple
     */
    public E second() {
        return y;
    }
}
