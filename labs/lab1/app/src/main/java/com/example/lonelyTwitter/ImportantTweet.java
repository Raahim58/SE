package com.example.lonelyTwitter;

import java.util.Date;

public class ImportantTweet extends Tweet {
    // parent constructors not inherited, must manually define in child class
    public ImportantTweet(String message) {
        super(message); // passes to Tweet class
    }
    public ImportantTweet(String message, Date date) {
        super(message, date);
    }

    @Override // methods in child classes need to be overridden to behave differently
    public Boolean isImportant() {
        return Boolean.TRUE;
    }

}
