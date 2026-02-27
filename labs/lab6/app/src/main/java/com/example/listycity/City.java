package com.example.listycity;

/**
 * The City class represents a city with a name and a province.
 * It implements Comparable so City objects can be sorted lexicographically by city name.
 */
public class City implements Comparable<City> {

    /**
     * The name of the city.
     */
    private String city;

    /**
     * The name of the province.
     */
    private String province;

    /**
     * Constructs a City object with a specified city name and province.
     *
     * @param city The name of the city.
     * @param province The name of the province.
     */
    public City(String city, String province){
        this.city = city;
        this.province = province;
    }

    /**
     * Returns the name of the city.
     *
     * @return the city name
     */
    public String getCityName(){
        return this.city;
    }

    /**
     * Returns the name of the province.
     *
     * @return the province name
     */
    public String getProvinceName(){
        return this.province;
    }

    /**
     * Compares this city with another city lexicographically
     * based on city name.
     *
     * @param other The other City to compare to
     * @return negative if this less than other,
     *         zero if equal,
     *         positive if this greater than other
     */
    @Override
    public int compareTo(City other) {
        return this.city.compareTo(other.city);
    }
}