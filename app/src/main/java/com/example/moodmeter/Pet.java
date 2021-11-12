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

    int selectedItemID = 0;


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
        switch(v.getId()) {
            case R.id.hat1:
                imageBorderShow(findViewById(R.id.hat1));
                selectedItemID = 1;
                selectedItemCost = 50;
                break;
            case R.id.hat2:
                imageBorderShow(findViewById(R.id.hat2));
                selectedItemID = 2;
                selectedItemCost = 100;
                break;
            case R.id.hat3:
                imageBorderShow(findViewById(R.id.hat3));
                selectedItemID = 3;
                selectedItemCost = 200;
                break;
            case R.id.hat4:
                imageBorderShow(findViewById(R.id.hat4));
                selectedItemID = 4;
                selectedItemCost = 200;
                break;
            case R.id.hat5:
                imageBorderShow(findViewById(R.id.hat5));
                selectedItemID = 5;
                selectedItemCost = 300;
                break;
            case R.id.hat6:
                imageBorderShow(findViewById(R.id.hat6));
                selectedItemID = 6;
                selectedItemCost = 300;
                break;
        }
    }

    public void imageBorderShow(ImageView itemChosen){
        itemChosen.setBackgroundColor(getResources().getColor(R.color.darkblue));
        ImageView[] shopIVs = {findViewById(R.id.hat1), findViewById(R.id.hat2), findViewById(R.id.hat3), findViewById(R.id.hat4), findViewById(R.id.hat5), findViewById(R.id.hat6)};
        for(int i = 0; i < shopIVs.length; i++){
            if(shopIVs[i] != itemChosen){
                shopIVs[i].setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
    }

}