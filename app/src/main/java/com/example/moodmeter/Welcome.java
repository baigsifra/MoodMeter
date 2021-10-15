package com.example.moodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void toLogin(View v){
        setTitle("Log In");
        setContentView(R.layout.login);
    }

    public void toSignUp(View V){
        setTitle("Sign Up");
        setContentView(R.layout.sign_up);
    }
}