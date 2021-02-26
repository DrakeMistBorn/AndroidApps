package com.alivelife;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserItem {
    private int id;
    private String email;


    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public UserItem(int id, String email){
        this.id = id;
        if (validEmail(email)) {
            this.email = email;
        }
    }

    public UserItem(String email){
        if (validEmail(email)) {
            this.email = email;
        }
    }

    private boolean validEmail(String email){
        if (email == null) {
            return false;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    @Override
    public String toString(){ //convert to string just with the email
        return email;
    }
}
