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

    private ArrayList<Day> daysArrayList= new ArrayList<>();  // arrayList of all Days in db

    public FirestoreHelper() {
        db = FirebaseFirestore.getInstance();
        daysRef = db.collection("Users");

        // This listener will listen for whenever the data is updated.  When that occurs, the arraylist
        // is cleared out and it is refreshed with the updated data.

        daysRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                // clear out the array list so that none of the events are duplicated in the display
                daysArrayList.clear();

                // this for each loop will get each Document Snapshot from the query, and one at a time,
                // convert them to an object of the Event class and then add them to the array list

                for (QueryDocumentSnapshot doc: value) {
                    Day day = doc.toObject(Day.class);
                    daysArrayList.add(day);
                }
            }
        });
    }

    public void addUser(String email, User user) {
        db.collection("Users").document(email).set(user);
        Map<String, ArrayList<String>> docData = new HashMap<>();
        docData.put("hats", new ArrayList<String>());
        docData.put("furniture", new ArrayList<String>());
        docData.put("backgrounds", new ArrayList<String>());
        db.collection("Users").document(email).collection("Inventory").document("inventory").set(docData);
    }

    public User retrieveUser(String email) {
        DocumentReference docRef = db.collection("Users").document(email);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
            }
        });
        return user;
    }

}

// Ifra: change the db to users, and grab everything below it. figure out how to add one day in the right spot