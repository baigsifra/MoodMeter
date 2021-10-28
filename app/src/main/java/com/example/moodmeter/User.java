package com.example.moodmeter;

public class User {
    private String uid;
    private double money;

    public User() {}

    public User(String uid) {
        this.uid = uid;
        money = 0;
    }

    public User(String uid, double money) {
        this.uid = uid;
        this.money = money;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
