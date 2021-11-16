package com.example.moodmeter;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Pet extends AppCompatActivity {

    private FirestoreHelper dbHelper;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    private Map<String, Object> inventory;

    private User currentUser;

    private double money;

    ArrayList<Integer> hats;
    ArrayList<Integer> furniture;
    ArrayList<Integer> backgrounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        dbHelper = new FirestoreHelper();



        dbHelper.getUser(new FirestoreHelper.MyCallback() {
            @Override
            public void onCallback(User user) {
                petFinished(user);
            }
        }, firebaseUser.getEmail());

        dbHelper.getInventory(new FirestoreHelper.MyInventory() {
            @Override
            public void onInvCallback(Map<String, Object> data) {
                hats = (ArrayList<Integer>) data.get("hats");
                furniture = (ArrayList<Integer>) data.get("furniture");
                backgrounds = (ArrayList<Integer>) data.get("backgrounds");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("megan", "hats in on create" + hats);
//                gotInventory(hats);
            }
        }, firebaseUser.getEmail());


    }

    public void petFinished(User user) {
        currentUser = new User(user.getUid(), user.getMoney());
        money = currentUser.getMoney();

    }

//    public void gotInventory(ArrayList<Integer> items) {
//        Log.i("megan", "items in gotInventory" + items);
//        Log.i("megan", "size " + items.size());
//
//        for(int i = 0; i < items.size(); i++) {
//            Log.i("megan", "index " + i);
//            Log.i("megan", "index got " + items.get(i));
//
//            if (items.get(i) == (1)) {
//                findViewById(R.id.hat1).setVisibility(View.VISIBLE);
//            } else if (items.get(i) == 2) {
//                findViewById(R.id.hat2).setVisibility(View.VISIBLE);
//            } else if (items.get(i) == 3) {
//                findViewById(R.id.hat3).setVisibility(View.VISIBLE);
//            } else if (items.get(i) == 4) {
//                findViewById(R.id.hat4).setVisibility(View.VISIBLE);
//            } else if (items.get(i) == 5) {
//                findViewById(R.id.hat5).setVisibility(View.VISIBLE);
//            } else if (items.get(i) == 6) {
//                findViewById(R.id.hat6).setVisibility(View.VISIBLE);
//            }
//        }
//    }


    public void toStore(View v){
        Intent shopIntent = new Intent(this, Shop.class);
        startActivity(shopIntent);


    }

    public void itemSelected(View v){
        Log.i("megan", "Hats " + hats + "     Furniture " + furniture);
        ImageView octopus = (ImageView)findViewById(R.id.octopusPet);
        ImageView bgOne = (ImageView) findViewById(R.id.bgpet);
        ImageView bgTwo = (ImageView) findViewById(R.id.bgpet2);

        switch(v.getId()) {
            case R.id.hat1:
                if(1 == 1){
                    Log.i("megan", "inside if");
                    Log.i("megan", "hat val " + hats.get(0));
                    Log.i("megan", "hat contains " + hats.contains(1));
                    imageBorderShow(findViewById(R.id.hat1));
                    octopus.setImageResource(R.drawable.octopusblue);
                }
                else{
                    dontOwn();
                }
                break;
            case R.id.hat2:
                if(hats.contains(2)) {
                    imageBorderShow(findViewById(R.id.hat2));
                    octopus.setImageResource(R.drawable.octopuscowboy);
                }
                else{
                    dontOwn();
                }
                break;
            case R.id.hat3:
                if(hats.contains(3)) {
                    imageBorderShow(findViewById(R.id.hat3));
                    octopus.setImageResource(R.drawable.octopuspink);
                }
                else{
                    dontOwn();
                }
                break;
            case R.id.hat4:
                if(hats.contains(4)) {
                    imageBorderShow(findViewById(R.id.hat4));
                    octopus.setImageResource(R.drawable.octopussanta);
                }
                else{
                    dontOwn();
                }
                break;
            case R.id.hat5:
                if(hats.contains(5)) {
                    imageBorderShow(findViewById(R.id.hat5));
                    octopus.setImageResource(R.drawable.octopuswitch);
                }
                else{
                    dontOwn();
                }
                break;
            case R.id.hat6:
                if(hats.contains(6)) {
                    imageBorderShow(findViewById(R.id.hat6));
                    octopus.setImageResource(R.drawable.octopusyellow);
                }
                else{
                    dontOwn();
                }
                break;
            case R.id.furniture1:
                if(furniture.contains(1)) {
                    imageBorderShow(findViewById(R.id.furniture1));
                    bgOne.setImageResource(R.drawable.bed);
                }
                else{
                    dontOwn();
                }
                break;
            case R.id.furniture2:
                if(furniture.contains(2)) {
                    imageBorderShow(findViewById(R.id.furniture2));
                    bgTwo.setImageResource(R.drawable.chair);
                }
                else{
                    dontOwn();
                }
                break;
            case R.id.furniture3:
                if(furniture.contains(3)) {
                    imageBorderShow(findViewById(R.id.furniture3));
                    bgOne.setImageResource(R.drawable.desk1);
                }
                else{
                    dontOwn();
                }
                break;
            case R.id.furniture4:
                if(furniture.contains(4)) {
                    imageBorderShow(findViewById(R.id.furniture4));
                    bgTwo.setImageResource(R.drawable.desk2);
                }
                else{
                    dontOwn();
                }
                break;
            case R.id.furniture5:
                if(furniture.contains(5)) {
                    imageBorderShow(findViewById(R.id.furniture5));
                    bgOne.setImageResource(R.drawable.dresser);
                }
                else{
                    dontOwn();
                }
                break;
            case R.id.furniture6:
                if(furniture.contains(6)) {
                    imageBorderShow(findViewById(R.id.furniture6));
                    bgTwo.setImageResource(R.drawable.sofa);
                }
                else{
                    dontOwn();
                }
                break;
        }
    }

    public void dontOwn(){
        Log.i("megan", "dont own");
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View dontOwnview = inflater.inflate(R.layout.unable_buy, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(dontOwnview, width, height);

        TextView errorMessage = dontOwnview.findViewById(R.id.unableBuyTV);
        errorMessage.setText("Sorry! You don't yet own this item!");
        Button okBtn = dontOwnview.findViewById(R.id.okBtn);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAtLocation(dontOwnview, Gravity.CENTER, 0, 0);
        dontOwnview.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    return true;
                }
            });
        }


    public void imageBorderShow(ImageView itemChosen){
        itemChosen.setBackgroundColor(getResources().getColor(R.color.darkblue));
        ImageView[] shopIVs = {findViewById(R.id.hat1), findViewById(R.id.hat2), findViewById(R.id.hat3), findViewById(R.id.hat4), findViewById(R.id.hat5), findViewById(R.id.hat6), findViewById(R.id.furniture1), findViewById(R.id.furniture2), findViewById(R.id.furniture3), findViewById(R.id.furniture4), findViewById(R.id.furniture5), findViewById(R.id.furniture6)};
        for(int i = 0; i < shopIVs.length; i++){
            if(shopIVs[i] != itemChosen){
                shopIVs[i].setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
    }

}