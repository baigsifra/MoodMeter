package com.example.moodmeter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FirestoreHelper {

    private final FirebaseFirestore db;     // ref to entire database
    private CollectionReference daysRef;  // ref to collection

    private User user;

    public FirestoreHelper() {
        db = FirebaseFirestore.getInstance();
    }

    public void addUser(String email, User user) {
        db.collection("Users").document(email).set(user);
        Date thisDate = new Date();
        SimpleDateFormat weekNum = new SimpleDateFormat("w");
        // db.collection("Users").document(email).collection("Weeks").document(weekNum.format(thisDate)).set("");
        Map<String, ArrayList<String>> docData = new HashMap<>();
        docData.put("hats", new ArrayList<String>());
        docData.put("furniture", new ArrayList<String>());
        docData.put("backgrounds", new ArrayList<String>());
        db.collection("Users").document(email).collection("Inventory").document("inventory").set(docData);
    }

    public void addDay(String email, Day day, String date) {
        db.collection("Users").document(email).collection("Days").document(date).set(day);
    }

    public void getUser(String email) {
        db.collection("Users").document(email).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String uid = documentSnapshot.getString("uid");
                            String money = documentSnapshot.getString("money");
                            user = new User(uid, Double.parseDouble(money));
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Error: ", e.toString());
            }
        });
    }

    public User returnUser(String email) {
        getUser(email);
        Log.d("User", user.getUid());
        return user;
    }

}