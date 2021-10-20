package com.example.moodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void logMoodPage() {
        Intent recordIntent = new Intent(this, Record.class);
        startActivity(recordIntent);
    }

    public void petPage() {
        Intent petIntent = new Intent(this, Pet.class);
        startActivity(petIntent);
    }

    public void dataPage() {
        Intent dataIntent = new Intent(this, Data.class);
        startActivity(dataIntent);
    }

    public void toData(View v){
        dataPage();
    }

    public void toPet(View v){
        petPage();
    }

    public void toRecord(View v){
        logMoodPage();
    }
}