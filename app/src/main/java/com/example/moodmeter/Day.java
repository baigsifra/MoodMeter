package com.example.moodmeter;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.ParseException;

public class Day implements Parcelable, Comparable<Day>
{
    private String date;
    private String journalEntry;
    private double happiness;
    private double energy;
    private double peacefulness;
    private double dayNumId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(String journalEntry) {
        this.journalEntry = journalEntry;
    }

    public double getHappiness() {
        return happiness;
    }

    public void setHappiness(double happiness) {
        this.happiness = happiness;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getPeacefulness() {
        return peacefulness;
    }

    public void setPeacefulness(double peacefulness) {
        this.peacefulness = peacefulness;
    }

    public double getDayNumId() {
        return dayNumId;
    }

    public void setDayNumId(double dayNumId) {
        this.dayNumId = dayNumId;
    }

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

    public Day(){ }

    /** This is a "constructor" of sorts that is needed with the Parcelable interface to
     * tell the intent how to create an Event object when it is received from the intent
     * basically it is setting each instance variable as a String or Double
     * if the instance variables were objects themselves you would need to do more complex code
     *
     * @param parcel    the parcel that is received from the intent
     */

    public Day(Parcel parcel) {
        date = parcel.readString();
        journalEntry = parcel.readString();
        happiness = parcel.readDouble();
        energy = parcel.readDouble();
        peacefulness = parcel.readDouble();
    }

    /**
     * This is the regular constructor used in the traditional sense
     * We use this one when we do not know the unique Firebase key yet for the Event
     * @param date - date in MM-DD-YYYY format
     * @param journalEntry - journalEntry
     * @param happiness - happiness
     * @param energy - energy
     * @param peacefulness - peacefulness
     * @param dayNumId - day of the week (1=Sunday, 2=Monday...)
     */

    public Day(String date, String journalEntry, double happiness, double energy, double peacefulness, double dayNumId) {
        this.date = date;
        this.journalEntry = journalEntry;
        this.happiness = happiness;
        this.energy = energy;
        this.peacefulness = peacefulness;
        this.dayNumId = dayNumId;
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

    /**
     * This method is required for Parcelable interface.  As of now, this method is in the default state
     * and doesn't really do anything.
     *
     * If your Parcelable class will have child classes, you'll need to
     *          * take some extra care with the describeContents() method. This will
     *          * let you identify the specific child class that should be created by
     *          * the Parcelable.Creator. You can read more about how this works on
     *          * Stack Overflow.
     *          *
     *          * https://stackoverflow.com/questions/4778834/purpose-of-describecontents-of-parcelable-interface
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    public int compareTo(Day other) {
        if((int)this.getHappiness() == (int)other.getHappiness())
            return (int)this.getHappiness() - (int)other.getHappiness();
        else
            return (int)this.getHappiness() - (int)other.getHappiness();
    }

    @Override
    public String toString() {
        return "Date: " + date + " Journal Entry: " + journalEntry + " Happiness: " + happiness + " Energy: " + energy + " Peacefulness: " + peacefulness + " dayId: " + dayNumId;
    }
}
