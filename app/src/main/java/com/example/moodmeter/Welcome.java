package com.example.moodmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private FirestoreHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mAuth = FirebaseAuth.getInstance();
        dbHelper = new FirestoreHelper();
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent homeIntent = new Intent(getApplicationContext(), Home.class);
            startActivity(homeIntent);
        }
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
                String signUpUsername = usernameET.getText().toString();
                String signUpEmail = signUpEmailET.getText().toString();
                String signUpPassword = signUpPasswordET.getText().toString();
                String confirmPassword = confirmPasswordET.getText().toString();
                if(confirmPassword.equals(signUpPassword)) {
                    signUp(signUpEmail, signUpPassword, signUpUsername);
                    // add way to store password
                }
                break;
            case R.id.submitLoginBtn:
                EditText loginEmailET = findViewById(R.id.loginEmailET);
                EditText loginPasswordET = findViewById(R.id.loginPasswordET);
                String loginEmail = loginEmailET.getText().toString();
                String loginPassword = loginPasswordET.getText().toString();
                login(loginEmail,loginPassword);
                break;
        }
    }

    public void signUp(String email, String password, String uid) {

        if (email != null && password != null) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.i("Megan", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent homeIntent = new Intent(getApplicationContext(), Home.class);
                                startActivity(homeIntent);
                                dbHelper.addUser(email, uid);
                            }
                            // add more specific error messages
                            else {
                                Log.i("Megan", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Welcome.this, "Sign In Failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent homeIntent = new Intent(getApplicationContext(), Home.class);
                            startActivity(homeIntent);

                        }
                        // add more specific error messages
                        else {
                            Toast.makeText(Welcome.this, "Login Failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}