package com.example.moodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void logMoodPage(View v) {
        Intent intent = new Intent(this, Record.class);
        startActivity(intent);
    }

    public void petPage(View v) {
        Intent intent = new Intent(this, Pet.class);
        startActivity(intent);
    }

    public void dataPage(View v) {
        Intent intent = new Intent(this, Data.class);
        startActivity(intent);
    }
}