package com.andyland.firebasedemo.vo;

import com.andyland.firebasedemo.common.util.Constants;

/**
 * Created by mamata.gelanee on 6/17/2016.
 */
public class FeedbackVO {
    private String username = Constants.DEFAULT_STRING;
    private String userEmail = Constants.DEFAULT_STRING;
    private String userContact = Constants.DEFAULT_STRING;
    private float rating = Constants.DEFAULT_FLOAT;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username != null)
            this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        if (userEmail != null)
            this.userEmail = userEmail;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        if (userContact != null)
            this.userContact = userContact;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "FeedbackVO{" +
                "username='" + username + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userContact='" + userContact + '\'' +
                ", rating=" + rating +
                '}';
    }
}
