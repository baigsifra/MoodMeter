package com.example.moodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Pet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
    }

    public void toStore(View v){
        Intent storeIntent = new Intent(this, Shop.class);
        startActivity(storeIntent);
    }
}