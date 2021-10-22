package com.example.moodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirestoreHelper dbHelper;

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

    public void signOut(View v) {
        mAuth.signOut();
        Intent homeIntent = new Intent(getApplicationContext(), Welcome.class);
        startActivity(homeIntent);
    }
}