package com.example.moodmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            // Take any action needed here when screen loads and a user is logged in
//        }
//        else {
//            // Take any action needed here when screen loads and a user is NOT logged in
//        }
    }

    public void toLogin(View v){
        setTitle("Log In");
        setContentView(R.layout.login);
    }

    public void toSignUp(View V){
        setTitle("Sign Up");
        setContentView(R.layout.sign_up);

    }

    public void accessAccount(View v){
        switch(v.getId()){
            case R.id.submitSignUpBtn:
                EditText usernameET = findViewById(R.id.signUpUsernameET);
                EditText signUpEmailET = findViewById(R.id.signUpEmailET);
                EditText signUpPasswordET = findViewById(R.id.signUpPasswordET);
                EditText confirmPasswordET = findViewById(R.id.signUpConfirmPasswordET);
                String signUpEmail = signUpEmailET.getText().toString();
                String signUpPassword = signUpPasswordET.getText().toString();
                signUp(signUpEmail, signUpPassword);
                break;
            case R.id.submitLoginBtn:
                EditText loginEmailET = findViewById(R.id.loginEmailET);
                EditText loginPasswordET = findViewById(R.id.loginPasswordET);
                String loginEmail = loginEmailET.getText().toString();
                String loginPassword = loginPasswordET.getText().toString();
                break;
        }
    }

    public void signUp(String email, String password) {

        // If the email and password passed in are not null, then try to create a User
        if (email != null && password != null) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign up success, update UI with the signed-in user's information
                                Log.i("Megan", "createUserWithEmail:success");
//                                FirebaseUser user = mAuth.getCurrentUser();
                            }
                            else {
                                // If sign up fails, display a message to the user.
                                Log.i("Megan", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Welcome.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


}