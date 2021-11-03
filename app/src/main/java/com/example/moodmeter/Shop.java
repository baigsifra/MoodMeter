package com.example.moodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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


public class Shop extends AppCompatActivity {

    private FirestoreHelper dbHelper;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    private User currentUser;

    private double money;

    int selectedItemCost = 1;
    // 1,2,3,4,5,6 - 7,8,9,10,11,12 - 13,14,15,16,17,18 for each hat/furniture/scenery
    int selectedItemID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        dbHelper = new FirestoreHelper();

        dbHelper.readData(new FirestoreHelper.MyCallback() {
            @Override
            public void onCallback(User user) {
                Log.d("pranav", user.toString());
                finished(user);
            }
        }, firebaseUser.getEmail());

//        money = dbHelper.returnUser(currentUser.getEmail()).getMoney();
    }

    public void finished(User user) {
        currentUser = new User(user.getUid(), user.getMoney());
        money = currentUser.getMoney();
        TextView moneyTV = findViewById(R.id.moneyTV);
        moneyTV.setText("$" + money);
    }

    public void itemSelected(View v){
       Button buyButton = findViewById(R.id.buyButton);
       buyButton.setVisibility(View.VISIBLE);

        switch(v.getId()) {
            case R.id.hatIV1:
                imageBorderShow(findViewById(R.id.hatIV1));
                selectedItemID = 1;
                selectedItemCost = 50;
                break;
            case R.id.hatIV2:
                imageBorderShow(findViewById(R.id.hatIV2));
                selectedItemID = 2;
                selectedItemCost = 100;
                break;
            case R.id.hatIV3:
                imageBorderShow(findViewById(R.id.hatIV3));
                selectedItemID = 3;
                selectedItemCost = 200;
                break;
            case R.id.hatIV4:
                imageBorderShow(findViewById(R.id.hatIV4));
                selectedItemID = 4;
                selectedItemCost = 200;
                break;
            case R.id.hatIV5:
                imageBorderShow(findViewById(R.id.hatIV5));
                selectedItemID = 5;
                selectedItemCost = 300;
                break;
            case R.id.hatIV6:
                imageBorderShow(findViewById(R.id.hatIV6));
                selectedItemID = 6;
                selectedItemCost = 300;
                break;
        }
    }

    public void imageBorderShow(ImageView itemChosen){
        itemChosen.setBackgroundColor(getResources().getColor(R.color.darkblue));
        Log.i("megan", "" + itemChosen);
        ImageView[] shopIVs = {findViewById(R.id.hatIV1), findViewById(R.id.hatIV2), findViewById(R.id.hatIV3), findViewById(R.id.hatIV4), findViewById(R.id.hatIV5), findViewById(R.id.hatIV6)};
        for(int i = 0; i < shopIVs.length; i++){
            if(shopIVs[i] != itemChosen){
                shopIVs[i].setBackgroundColor(Color.TRANSPARENT);
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
        dbHelper.addInventory(selectedItemID);
        TextView moneyTV = findViewById(R.id.moneyTV);
        moneyTV.setText("$" + money);
        // add selectedItemID to firebase inventory
    }

}