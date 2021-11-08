package com.example.moodmeter;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Pet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
    }

    public void toStore(View v){
        Intent shopIntent = new Intent(this, Shop.class);
        startActivity(shopIntent);
    }

}