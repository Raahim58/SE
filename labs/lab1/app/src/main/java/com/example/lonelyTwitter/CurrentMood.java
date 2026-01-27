package com.example.lonelyTwitter;

import java.util.Date;

public abstract class CurrentMood {
    private Date date; // encapsulation
    public CurrentMood() { // default
        this.date = new Date();
    }
    public CurrentMood(Date date) {
        this.date = date;
    }

    // getters and setters
    public Date getDate() {
        return date;
    }
    public void setDate (Date date) {
        this.date = date;
    }
    public abstract String getMoodString(); // abstraction -> returns the string representing the mood
}
