package com.example.listyView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // layout for each row (ie. each city) comes from context.xml (ie. TextView), match_parent makes it stretch across the screen with large text and padding
    ListView cityList; // the list on screen
    ArrayAdapter<String> cityAdapter; // translator between data and layout -> the bridge that connects the data to UI
    ArrayList<String> dataList; // the data (ie. city names)
    EditText cityInput;
    Button addBtn, deleteBtn;
    int selectedIndex = -1; // nothing selected
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // the adapter takes each city in datalist, creates a new context.xml for it, finds the TextView and puts the city inside it, adds it to the ListView
        // cities[]  →  ArrayList  →  Adapter  →  content.xml rows  →  ListView
        cityList = findViewById(R.id.city_list); // find reference to listView and assign it cityList
        cityInput = findViewById(R.id.city_input);
        addBtn = findViewById(R.id.add_city_btn);
        deleteBtn = findViewById(R.id.delete_city_btn);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        // convert array to arrayList as it is easier to modify and usually required by the adapter
        dataList = new ArrayList<>(); // will contain data -> the string array of cities
        dataList.addAll(Arrays.asList(cities)); // add the data, now holds all the cities
        // link context.xml via R.layout.content (use layout defined there) to the dataList (city names to show) so that each element is displayed on a separate row in the list
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList); // copies that row layout again and again filling it with cities
        cityList.setAdapter(cityAdapter); // connect listView to the ArrayAdapter which will show each TextView in the form of a scrolling list

        // tap a city to select it
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position; // position is the city you select
            deleteBtn.setEnabled(true);
            Toast.makeText(this, "Selected: " + dataList.get(position), Toast.LENGTH_SHORT).show();
        });

        // ADD CITY button, runs when add city is pressed thru onclick
        addBtn.setOnClickListener(v -> {
            String newCity = cityInput.getText().toString().trim(); // reads text from editText, converts it into String and removes spaces
            // prevent blank cities
            if (newCity.isEmpty()) {
                Toast.makeText(this, "Please type a city name first.", Toast.LENGTH_SHORT).show();
                return;
            }
            dataList.add(newCity);
            cityAdapter.notifyDataSetChanged(); // refresh list -> tells adapter that the list has changed so it redraws it
            cityInput.setText(""); // clear input
            // reset selection
            selectedIndex = -1;
            deleteBtn.setEnabled(false);
        });

        // DELETE CITY button
        deleteBtn.setOnClickListener(v -> {
            // make sure valid index is selected
            if (selectedIndex < 0 || selectedIndex >= dataList.size()) {
                Toast.makeText(this, "Tap a city first to select it.", Toast.LENGTH_SHORT).show();
                return;
            }
            String removed = dataList.remove(selectedIndex); // removes the selected indexed city + 1
            cityAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Deleted: " + removed, Toast.LENGTH_SHORT).show();
            // reset selection after deletion
            selectedIndex = -1;
            deleteBtn.setEnabled(false);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}