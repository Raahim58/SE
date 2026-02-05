package com.example.listycitylab3;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

// The dialog collects city data, then calls addCity(city) on the Activity so the Activity can update the list.
// User types → presses Add → dialog creates City → calls addCity(city) → Activity updates list.
public class AddCityFragment extends DialogFragment { // a small popup text box where the user will enter the city, it's a dialog not a full screen
    // dialog creates the city, sends it back to the main activity using addCity, dialog does not add the city itself
    interface AddCityDialogListener {
        void addCity(City city);
    }

    private AddCityDialogListener listener; // stores reference to the MainActivity
    // connects the MainActivity to the dialog
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) { // does Activity implement AddCityDialogListener? without the listener the dialog can't return the data
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    // creates the dialog UI
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null); // loads fragment_add_city.xml and turns it into a usable View
        EditText editCityName = view.findViewById(R.id.edit_text_city_text); // user types city name
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text); // user types city province
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()); // dialog builder
        return builder
                .setView(view)
                .setTitle("Add a city")
                .setNegativeButton("Cancel", null) // closes the dialog with no action
                .setPositiveButton("Add", (dialog, which) -> { // runs when the user presses Add
                    // reads user name, creates a City object and sends it to the MainActivity
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    listener.addCity(new City(cityName, provinceName)); // listener is actually MainActivity, runs inside MainActivity
                    // dialog auto closed by Android after this
                })
                .create();

    }
}

// Dialog = collects data
// Activity = owns the list
// Interface = safe phone line between them