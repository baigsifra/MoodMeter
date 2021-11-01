package com.example.moodmeter;

public class User {
    private String uid;
    private double money;
    private String pet;

    public User() {}

    public User(String uid) {
        this.uid = uid;
        money = 0;
    }

    public User(String uid, double money) {
        this.uid = uid;
        this.money = money;
    }

    public User(String uid, double money, String pet) {
        this.uid = uid;
        this.money = money;
        this.pet = pet;
    }

    public User(String uid, String pet) {
        this.uid = uid;
        money = 0;
        this.pet = pet;
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

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }
}
