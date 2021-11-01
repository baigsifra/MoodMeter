package com.example.moodmeter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private static ArrayList<Integer> hats = new ArrayList<Integer>();
    private static ArrayList<Integer> furniture = new ArrayList<Integer>();
    private static ArrayList<Integer> backgrounds = new ArrayList<Integer>();


    public FirestoreHelper() {
        db = FirebaseFirestore.getInstance();
    }

    public void addUser(String email, User user) {
        db.collection("Users").document(email).set(user);
        Date thisDate = new Date();
        SimpleDateFormat weekNum = new SimpleDateFormat("w");
        // db.collection("Users").document(email).collection("Weeks").document(weekNum.format(thisDate)).set("");
        Map<String, ArrayList<Integer>> docData = new HashMap<>();
        docData.put("hats", hats);
        docData.put("furniture", furniture);
        docData.put("backgrounds", backgrounds);
        db.collection("Users").document(email).collection("Inventory").document("inventory").set(docData);
    }

    public void addInventory(int itemID){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Map<String, ArrayList<Integer>> docData = new HashMap<>();
        if(itemID <= 6){
            hats.add(itemID);
            docData.put("hats", hats);
            docData.put("furniture", furniture);
            docData.put("backgrounds", backgrounds);
        }
        else if(itemID <= 12){
            furniture.add(itemID);
            docData.put("hats", hats);
            docData.put("furniture", furniture);
            docData.put("backgrounds", backgrounds);
        }
        else if(itemID <= 18){
            backgrounds.add(itemID);
            docData.put("hats", hats);
            docData.put("furniture", furniture);
            docData.put("backgrounds", backgrounds);
        }
        db.collection("Users").document(currentUser.getEmail()).collection("Inventory").document("inventory").set(docData);
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
                            double money = documentSnapshot.getDouble("money");
                            user = new User(uid, money);
                        }
                    }
                });
    }

    public User returnUser(String email) {
        getUser(email);
        Log.d("megan", user.getUid());
        Log.d("megan", "" + user.getMoney());
        return user;
    }

}