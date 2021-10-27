package com.example.moodmeter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FirestoreHelper {
    User user;

    private final FirebaseFirestore db;     // ref to entire database
    private CollectionReference daysRef;  // ref to collection

    private ArrayList<User> usersArrayList= new ArrayList<User>();  // arrayList of all Days in db

    public FirestoreHelper() {
        db = FirebaseFirestore.getInstance();
    }

    public void addUser(String email, User user) {
        db.collection("Users").document(email).set(user);
        Map<String, ArrayList<String>> docData = new HashMap<>();
        docData.put("hats", new ArrayList<String>());
        docData.put("furniture", new ArrayList<String>());
        docData.put("backgrounds", new ArrayList<String>());
        db.collection("Users").document(email).collection("Inventory").document("inventory").set(docData);
    }

    public void addDay(String email, Day day, String date) {
        db.collection("Users").document(email).collection("Days").document(date).set(day);
    }

}