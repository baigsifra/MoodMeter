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

    private User user;
    private static ArrayList<Integer> hats = new ArrayList<Integer>();
    private static ArrayList<Integer> furniture = new ArrayList<Integer>();
    private static ArrayList<Integer> backgrounds = new ArrayList<Integer>();


    public FirestoreHelper() {
        db = FirebaseFirestore.getInstance();
    }

    public void addUser(String email, User user) {
        db.collection("Users").document(email).set(user);
        // Date thisDate = new Date();
        // SimpleDateFormat weekNum = new SimpleDateFormat("w");
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
        Date thisDate = new Date();
        SimpleDateFormat weekNum = new SimpleDateFormat("w");
        db.collection("Users").document(email).collection("Weeks").document(weekNum.format(thisDate)).collection("Days").document(date).set(day);
        // db.collection("Users").document(email).collection("Days").document(date).set(day);
    }

    public void getUser(MyCallback myCallback, String email) {

        db.collection("Users").document(email).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String uid = documentSnapshot.getString("uid");
                            double money = documentSnapshot.getDouble("money");

                            user = new User(uid, money);
                            myCallback.onCallback(user);
                        }
                    }
                });
    }

    public void getInventory(MyInventory myInventory, String email) {

        db.document("Users/"+email+"/Inventory/inventory").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            ArrayList<String> items;
                            Map<String, Object> dataToLoad = (HashMap<String, Object>) documentSnapshot.getData();

                            myInventory.onInvCallback(dataToLoad);
                        }
                    }
                });

    }

    public void getDay(MyDay myDay, String email, int weekNum, String date)  {

        db.document("Users/"+email+"/Weeks/"+weekNum+"/Days/"+date).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String journalEntry = documentSnapshot.getString("journalEntry");
                            double energy = documentSnapshot.getDouble("energy");
                            double happiness = documentSnapshot.getDouble("happiness");
                            double peacefulness = documentSnapshot.getDouble("peacefulness");
                            Day day = new Day(date, journalEntry, happiness, energy, peacefulness);

                            myDay.onDayCallback(day);
                        }
                    }
                });

    }

    public void getWeek(MyWeek myWeek, String email, int weekNum) {
        db.collection("Users/"+email+"/Weeks/"+ weekNum + "/Days").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()) {
                            ArrayList<Day> dayAL = new ArrayList<Day>();
                            for(QueryDocumentSnapshot qdr: queryDocumentSnapshots) {
                                String journalEntry = qdr.getString("journalEntry");
                                String date = qdr.getString("date");
                                double energy = qdr.getDouble("energy");
                                double happiness = qdr.getDouble("happiness");
                                double peacefulness = qdr.getDouble("peacefulness");
                                Day day = new Day(date, journalEntry, happiness, energy, peacefulness);
                                dayAL.add(day);
                            }
                            Week week = new Week(dayAL);
                            myWeek.onWeekCallback(week);
                        }
                    }
                });
    }

    public interface MyCallback {
        void onCallback(User user);
    }

    public interface MyInventory {
        void onInvCallback(Map<String, Object> data);
    }

    public interface MyDay {
        void onDayCallback(Day day);
    }

    public interface MyWeek {
        void onWeekCallback(Week week);
    }
}