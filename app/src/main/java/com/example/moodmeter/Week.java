package com.example.moodmeter;

import java.util.ArrayList;

public class Week {
    private ArrayList<Day> dayArray;

    public Week() {
        dayArray = new ArrayList<Day>();
    }

    public Week(ArrayList<Day> dayAL) {
        dayArray = dayAL;
    }

    public void addDay(Day day) {
        dayArray.add(day);
    }

    public ArrayList<Day> getDayArray() {
        return dayArray;
    }

    public void setDayArray(ArrayList<Day> dayArray) {
        this.dayArray = dayArray;
    }

    public String toString() {
        String str = "";
        for(Day d : dayArray) {
            str += d.toString() + "\n";
        }
        return str;
    }
}
