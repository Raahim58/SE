package com.example.listycitylab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

// has to be properly initialized for the parent class to hold our context and all the cities, an adapter connects your data (City objects) to your UI (rows on screen)
public class CityArrayAdapter extends ArrayAdapter<City> { // type is city
    public CityArrayAdapter(Context context, ArrayList<City> cities) { // context is where the app is running
        super(context, 0, cities); // 0 is a placeholder for the layout resource, parent doesn't need to know the actual layout we will use
    }

    // position is the row num used to get the correct city
    // convertVIew is an old row view that can be reused, can be used to save memory and speed up the app
    // parent is the parent view group, mainly used for layout inflation
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) { // if no old view exists, inflate a new one
            view = LayoutInflater.from(getContext()).inflate(R.layout.content, parent, false);
        } else {
            view = convertView;
        }
        City city = getItem(position); // get the city at the given position in the list
        TextView cityName = view.findViewById(R.id.city_text);
        TextView provinceName = view.findViewById(R.id.province_text);
        // set their text using the city data retrieved from TextView
        cityName.setText(city.getCity());
        provinceName.setText(city.getProvince());
        return view;
    }
}
