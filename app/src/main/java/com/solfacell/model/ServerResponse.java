package com.solfacell.model;

/**
 * Created by Ratri on 9/23/2016.
 */
public class ServerResponse {
    private String result;
    private String message;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    private User user;
}
