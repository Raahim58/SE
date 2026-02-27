package com.example.listycity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a class that keeps a list of city objects
 */
public class CityList {
    private List<City> cities = new ArrayList<>();
    /**
     * This adds a city to the list if the city does not exist
     * @param city
     * This is a candidate city to add
     */
    public void add(City city) {
        if (cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.add(city);
    }

    /**
     * This returns a sorted list of cities
     * @return
     * Return the sorted list
     */
    public List<City> getCities() {
        // to sort an Object by its property, we have to make the Object implement the Comparable interface and
        //override the compareTo() method. Lists (and arrays) of objects that implement Comparable interface can be
        // sorted automatically by Collections.sort(), therefore we also need to implement the compareTo() method.
        List<City> list = cities;
        Collections.sort(list);
        return list;
    }
}
