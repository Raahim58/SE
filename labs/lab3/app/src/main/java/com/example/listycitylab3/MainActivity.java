package com.example.listycitylab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener { // MainActivity agrees to handle addCity, receive city from dialog

    private ArrayList<City> dataList;
    private ListView cityList;
    private ArrayAdapter<City> cityAdapter; // private CityArrayAdapter cityAdapter also works

    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // define data
        String[] cities = {
                "Edmonton", "Vancouver", "Toronto",
//                "Sydney", "Berlin", "Vienna",
//                "Tokyo", "Beijing", "Osaka", "New Delhi"
        };
        String[] provinces = {"AB", "BC", "ON"};
        dataList = new ArrayList<City>();
        // create a list of cities and provinces: City objects
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }
//        dataList.addAll(Arrays.asList(cities));
        cityList = findViewById(R.id.city_list);
//        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter); // adapter of our cityList is CityArrayAdapter
        // add floatingActionButton to trigger the dialog via onClick
        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(view -> { // waits for user to press + button
            AddCityFragment dialog = new AddCityFragment();
            dialog.show(getSupportFragmentManager(), "AddCityFragment");
        });
    }
}