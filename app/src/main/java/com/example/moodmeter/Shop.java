package com.example.moodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Map;


public class Shop extends AppCompatActivity {

    private FirestoreHelper dbHelper;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    private Map<String, Object> inventory;

    private User currentUser;

    private double money;

    int selectedItemCost = 1;
    // 1,2,3,4,5,6 - 7,8,9,10,11,12 - 13,14,15,16,17,18 for each hat/furniture/scenery
    int selectedItemID = 0;

    ArrayList<Integer> hats;
    ArrayList<Integer> furniture;
    ArrayList<Integer> backgrounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        dbHelper = new FirestoreHelper();

        dbHelper.getUser(new FirestoreHelper.MyCallback() {
            @Override
            public void onCallback(User user) {
                finished(user);
            }
        }, firebaseUser.getEmail());

        dbHelper.getInventory(new FirestoreHelper.MyInventory() {
            @Override
            public void onInvCallback(Map<String, Object> data) {
                gotInventory(data);
            }
        }, firebaseUser.getEmail());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void finished(User user) {
        currentUser = new User(user.getUid(), user.getMoney());
        money = currentUser.getMoney();
        TextView moneyTV = findViewById(R.id.moneyTV);
        moneyTV.setText("$" + (int)money);
    }

    public void gotInventory(Map<String, Object> myData){
        hats = (ArrayList<Integer>)myData.get("hats");
        furniture = (ArrayList<Integer>)myData.get("furniture");
        backgrounds = (ArrayList<Integer>)myData.get("backgrounds");

    }

    public void itemSelected(View v){
       Button buyButton = findViewById(R.id.buyButton);
       buyButton.setVisibility(View.VISIBLE);

        switch(v.getId()) {
            case R.id.hatIV1:
                imageBorderShow(findViewById(R.id.hatIV1));
                selectedItemID = 1;
                hats.add(selectedItemID);
                selectedItemCost = 50;
                break;
            case R.id.hatIV2:
                imageBorderShow(findViewById(R.id.hatIV2));
                selectedItemID = 2;
                hats.add(selectedItemID);
                selectedItemCost = 100;
                break;
            case R.id.hatIV3:
                imageBorderShow(findViewById(R.id.hatIV3));
                selectedItemID = 3;
                hats.add(selectedItemID);
                selectedItemCost = 200;
                break;
            case R.id.hatIV4:
                imageBorderShow(findViewById(R.id.hatIV4));
                selectedItemID = 4;
                hats.add(selectedItemID);
                selectedItemCost = 200;
                break;
            case R.id.hatIV5:
                imageBorderShow(findViewById(R.id.hatIV5));
                selectedItemID = 5;
                hats.add(selectedItemID);
                selectedItemCost = 300;
                break;
            case R.id.hatIV6:
                imageBorderShow(findViewById(R.id.hatIV6));
                selectedItemID = 6;
                hats.add(selectedItemID);
                selectedItemCost = 300;
                break;
            case R.id.furniture1:
                imageBorderShow(findViewById(R.id.furniture1));
                selectedItemID = 7;
                furniture.add(selectedItemID);
                selectedItemCost = 300;
                break;
            case R.id.furniture2:
                imageBorderShow(findViewById(R.id.furniture2));
                selectedItemID = 8;
                furniture.add(selectedItemID);
                selectedItemCost = 300;
                break;
            case R.id.furniture3:
                imageBorderShow(findViewById(R.id.furniture3));
                selectedItemID = 9;
                furniture.add(selectedItemID);
                selectedItemCost = 300;
                break;
            case R.id.furniture4:
                imageBorderShow(findViewById(R.id.furniture4));
                selectedItemID = 10;
                furniture.add(selectedItemID);
                selectedItemCost = 300;
                break;
            case R.id.furniture5:
                imageBorderShow(findViewById(R.id.furniture5));
                selectedItemID = 11;
                furniture.add(selectedItemID);
                selectedItemCost = 300;
                break;
            case R.id.furniture6:
                imageBorderShow(findViewById(R.id.furniture6));
                selectedItemID = 12;
                furniture.add(selectedItemID);
                selectedItemCost = 300;
                break;

        }
        buyButton.setText("Buy for " + selectedItemCost + " coins?");

    }

    public void imageBorderShow(ImageView itemChosen){
        itemChosen.setBackgroundColor(getResources().getColor(R.color.darkblue));
        ImageView[] shopIVs = {findViewById(R.id.hatIV1), findViewById(R.id.hatIV2), findViewById(R.id.hatIV3), findViewById(R.id.hatIV4), findViewById(R.id.hatIV5), findViewById(R.id.hatIV6), findViewById(R.id.furniture1), findViewById(R.id.furniture2), findViewById(R.id.furniture3), findViewById(R.id.furniture4), findViewById(R.id.furniture5), findViewById(R.id.furniture6)};
        for(int i = 0; i < shopIVs.length; i++){
            if(shopIVs[i] != itemChosen){
                shopIVs[i].setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
    }


    public void askBuy(View v){
        if(selectedItemCost > money){
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.unable_buy, null);

            // create the popup window
            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

            // show the popup window
            // which view you pass in doesn't matter, it is only used for the window token
            popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
            Button okBtn = popupView.findViewById(R.id.okBtn);
            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

            // dismiss the popup window when touched
            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    return true;
                }
            });
            return;
        }
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.buy_pop_up, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    public void confirmBuy(View v){
        money -= selectedItemCost;
        if(selectedItemID <= 6){
            dbHelper.addInventory(selectedItemID, hats, furniture);
        }
        else{
            dbHelper.addInventory(selectedItemID, furniture, hats);
        }
        TextView moneyTV = findViewById(R.id.moneyTV);
        moneyTV.setText("$" + (int)money);
        Intent petIntent = new Intent(this, Pet.class);
        startActivity(petIntent);
        dbHelper.addMoney(firebaseUser.getEmail(), money);
    }

}
