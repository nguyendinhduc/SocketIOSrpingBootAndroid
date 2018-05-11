package com.t3h.chatandroid;

/**
 * Created by ducnd on 5/12/18.
 */

public class ChatObject {
    private String userName;
    private String message;

    public ChatObject(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public ChatObject() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
