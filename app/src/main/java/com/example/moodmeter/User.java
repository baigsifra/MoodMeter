package com.example.moodmeter;

public class User {
    private String uid;
    private int money;

    public User() {}

    public User(String uid) {
        this.uid = uid;
        money = 0;
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
