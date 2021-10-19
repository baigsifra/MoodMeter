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

    String username;
    String email;
    String password;
    String confirmPassword;
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
        EditText emailET = findViewById(R.id.loginEmailET);
        EditText passwordET = findViewById(R.id.loginPasswordET);
        email = emailET.getText().toString();
        password = passwordET.getText().toString();

    }

    public void toSignUp(View V){
        setTitle("Sign Up");
        setContentView(R.layout.sign_up);
        EditText usernameET = findViewById(R.id.signUpUsernameET);
        EditText emailET = findViewById(R.id.signUpEmailET);
        EditText passwordET = findViewById(R.id.signUpPasswordET);
        EditText confirmPasswordET = findViewById(R.id.signUpConfirmPasswordET);
        username = usernameET.getText().toString();
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        confirmPassword = confirmPasswordET.getText().toString();
    }

    public void signUp(View v){
        // add error messages
        if(username != null && email != null && password != null && confirmPassword != null){
            if(password.equals(confirmPassword)){
                mAuth.createUserWithEmailAndPassword(email,password)

                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.i("FirebaseAuth", email + " " + password);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.i("FirebaseAuth", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.i("FirebaseAuth", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Welcome.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        );
            }
        }
    }

    public void login(View v){
    }
}