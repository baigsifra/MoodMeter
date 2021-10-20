package com.example.moodmeter;

import android.os.Parcel;
import android.os.Parcelable;

public class Day implements Parcelable, Comparable<Day>
{
    private String date;
    private String journalEntry;
    private double happiness;
    private double energy;
    private double peacefulness;

    // needed  for the Parcelable code to work
    public static final Parcelable.Creator<Day> CREATOR = new Parcelable.Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel parcel) {
            return new Day(parcel);
        }
        @Override
        public Day[] newArray(int size) {
            return new Day[0];
        }
    };

    /** This is a "constructor" of sorts that is needed with the Parcelable interface to
     * tell the intent how to create an Event object when it is received from the intent
     * basically it is setting each instance variable as a String or Double
     * if the instance variables were objects themselves you would need to do more complex code
     *
     * @param parcel    the parcel that is received from the intent
     */

    public Event(Parcel parcel) {
        date = parcel.readString();
        journalEntry = parcel.readString();
        happiness = parcel.readDouble();
        energy = parcel.readDouble();
        peacefulness = parcel.readDouble();
    }

    /**
     * This is the regular constructor used in the traditional sense
     * We use this one when we do not know the unique Firebase key yet for the Event
     * @param date
     * @param journalEntry
     * @param happiness
     * @param energy
     * @param peacefulness
     */

    public Day(String date, String journalEntry, double happiness, double energy, double peacefulness) {
        this.date = date;
        this.journalEntry = journalEntry;
        this.happiness = happiness;
        this.energy = energy;
        this.peacefulness = peacefulness;
    }

    @Override
    /**
     * This is what is used when we send the Event object through an intent
     * It is also a method that is part of the Parcelable interface and is needed
     * to set up the object that is being sent.  Then, when it is received, the
     * other Event constructor that accepts a Parcel reference can "unpack it"
     *
     */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(journalEntry);
        dest.writeDouble(happiness);
        dest.writeDouble(energy);
        dest.writeDouble(peacefulness);
    }
}
