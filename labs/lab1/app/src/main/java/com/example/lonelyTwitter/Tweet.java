package com.example.lonelyTwitter;

import java.util.Date;

public abstract class Tweet implements Tweetable { // to show only needed functionality
    public Date date;
    public String message;

    // constructor 1: only message
    public Tweet (String message) {
        this.message = message;
        this.date = new Date(); // default to right now
    }

    // constructor 2: message + date
    public Tweet (String message, Date date) {
        this.message = message;
        this.date = date;
    }

    // getters (required from interface -> these functions are NOW a MUST)
    public Date getDate() {
        return date;
    }
    public String getMessage() {
        return message;
    }

    // setters
    public void setDate(Date date) {
        this.date = date;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    // abstract method: has to be overridden by child classes to have functionality
    public abstract Boolean isImportant();

}
