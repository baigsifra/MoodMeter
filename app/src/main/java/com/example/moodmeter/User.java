package com.example.moodmeter;

public class User {
    private String userEmail;
    private String uid;
    private int money;

    public User() {}

    public User(String userEmail, String uid) {
        this.userEmail = userEmail;
        this.uid = uid;
        money = 0;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
