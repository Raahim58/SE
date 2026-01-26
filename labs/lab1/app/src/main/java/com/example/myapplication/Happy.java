package com.example.myapplication;

import java.util.Date;

public class Happy extends CurrentMood {
    public Happy() {
        super(); // call parent's default constructor
    }

    public Happy(Date date) {
        super(date); // call parent's parametrized constructor
    }

    // implementation of abstract method
    @Override
    public String getMoodString() {
        return "Happy";
    }
}
