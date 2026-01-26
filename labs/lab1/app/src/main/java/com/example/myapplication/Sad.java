package com.example.myapplication;

import java.util.Date;

public class Sad extends CurrentMood {
    public Sad() {
        super();
    }

    public Sad(Date date) {
        super(date);
    }

    @Override
    public String getMoodString() {
        return "Sad";
    }
}
