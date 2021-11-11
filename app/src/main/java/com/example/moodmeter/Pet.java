package com.example.moodmeter;
import android.content.Intent;
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

public class Pet extends AppCompatActivity {

    int selectedItemCost = 1;
    // 1,2,3,4,5,6 - 7,8,9,10,11,12 - 13,14,15,16,17,18 for each hat/furniture/scenery
    int selectedItemID = 0;

    private double money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
    }

    public void toStore(View v){
        Intent shopIntent = new Intent(this, Shop.class);
        startActivity(shopIntent);
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
        ImageView[] shopIVs = {findViewById(R.id.hatIV1), findViewById(R.id.hatIV2), findViewById(R.id.hatIV3), findViewById(R.id.hatIV4), findViewById(R.id.hatIV5), findViewById(R.id.hatIV6)};
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

}