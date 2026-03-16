package com.example.lab08;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomListTest {

    @Test
    public void testHasCity() {
        CustomList list = new CustomList();
        City calgary = new City("Calgary", "AB");

        list.addCity(calgary);

        assertTrue(list.hasCity(calgary));
    }

    @Test
    public void testDeleteCity() {
        CustomList list = new CustomList();
        City calgary = new City("Calgary", "AB");

        list.addCity(calgary);
        assertTrue(list.hasCity(calgary));

        list.deleteCity(calgary);
        assertFalse(list.hasCity(calgary));
    }

    @Test
    public void testCountCities() {
        CustomList list = new CustomList();
        City calgary = new City("Calgary", "AB");
        City edmonton = new City("Edmonton", "AB");

        assertEquals(0, list.countCities());

        list.addCity(calgary);
        assertEquals(1, list.countCities());

        list.addCity(edmonton);
        assertEquals(2, list.countCities());
    }
}