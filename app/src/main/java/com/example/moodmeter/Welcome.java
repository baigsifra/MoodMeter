package com.example.moodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void toLogin(View v){
        setTitle("Log In");
        setContentView(R.layout.login);
        EditText username = findViewById(R.id.loginUsernameET);
        EditText password = findViewById(R.id.loginPasswordET);
    }

    public void toSignUp(View V){
        setTitle("Sign Up");
        setContentView(R.layout.sign_up);
        EditText username = findViewById(R.id.signUpUsernameET);
        EditText email = findViewById(R.id.signUpEmailET);
        EditText password = findViewById(R.id.signUpPasswordET);
        EditText confirmPassword = findViewById(R.id.signUpConfirmPasswordET);

    }
}