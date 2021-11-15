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
        findViewById(R.id.hat1).setVisibility(View.GONE);
        findViewById(R.id.hat2).setVisibility(View.GONE);
        findViewById(R.id.hat3).setVisibility(View.GONE);
        findViewById(R.id.hat4).setVisibility(View.GONE);
        findViewById(R.id.hat5).setVisibility(View.GONE);
        findViewById(R.id.hat6).setVisibility(View.GONE);
        findViewById(R.id.furniture1).setVisibility(View.GONE);
        findViewById(R.id.furniture2).setVisibility(View.GONE);
        findViewById(R.id.furniture3).setVisibility(View.GONE);
        findViewById(R.id.furniture4).setVisibility(View.GONE);
        findViewById(R.id.furniture5).setVisibility(View.GONE);
        findViewById(R.id.furniture6).setVisibility(View.GONE);


        dbHelper.getUser(new FirestoreHelper.MyCallback() {
            @Override
            public void onCallback(User user) {
                petFinished(user);
            }
        }, firebaseUser.getEmail());

        dbHelper.getInventory(new FirestoreHelper.MyInventory() {
            @Override
            public void onInvCallback(Map<String, Object> data) {
                gotInventory(data);
            }
        }, firebaseUser.getEmail());


    }

    public void petFinished(User user) {
        currentUser = new User(user.getUid(), user.getMoney());
        money = currentUser.getMoney();
        TextView moneyPetTV = findViewById(R.id.moneyPetTV);
        moneyPetTV.setText("$" + (int)money);
    }

    public void gotInventory(Map<String, Object> myData) {
        hats = (ArrayList<Integer>) myData.get("hats");
        furniture = (ArrayList<Integer>) myData.get("furniture");
        backgrounds = (ArrayList<Integer>) myData.get("backgrounds");
        Log.d("megan", "hats " + hats);
//        for(int i = 0; i < hats.size(); i++) {
//            if (hats.get(i).equals(1)) {
//                findViewById(R.id.hat1).setVisibility(View.VISIBLE);
//            } else if (hats.get(i) == 2) {
//                findViewById(R.id.hat2).setVisibility(View.VISIBLE);
//            } else if (hats.get(i) == 3) {
//                findViewById(R.id.hat3).setVisibility(View.VISIBLE);
//            } else if (hats.get(i) == 4) {
//                findViewById(R.id.hat4).setVisibility(View.VISIBLE);
//            } else if (hats.get(i) == 5) {
//                findViewById(R.id.hat5).setVisibility(View.VISIBLE);
//            } else if (hats.get(i) == 6) {
//                findViewById(R.id.hat6).setVisibility(View.VISIBLE);
//            }
//        }
    }

    public void checkOwnership(ArrayList<Integer> items){
        if(items==null){
            return;
        }
        for(int i = 0; i < items.size(); i++){
            if(items.get(i) == 1){
                findViewById(R.id.hat1).setVisibility(View.VISIBLE);
            }
            else if(items.get(i) == 2){
                findViewById(R.id.hat2).setVisibility(View.VISIBLE);
            }
            else if(items.get(i) == 3){
                findViewById(R.id.hat3).setVisibility(View.VISIBLE);
            }
            else if(items.get(i) == 4){
                findViewById(R.id.hat4).setVisibility(View.VISIBLE);
            }
            else if(items.get(i) == 5){
                findViewById(R.id.hat5).setVisibility(View.VISIBLE);
            }
            else if(items.get(i) == 6){
                findViewById(R.id.hat6).setVisibility(View.VISIBLE);
            }

            if(items.get(i) == 7){
                findViewById(R.id.furniture1).setVisibility(View.VISIBLE);
            }
            else if(items.get(i) == 8){
                findViewById(R.id.furniture2).setVisibility(View.VISIBLE);
            }
            else if(items.get(i) == 9){
                findViewById(R.id.furniture3).setVisibility(View.VISIBLE);
            }
            else if(items.get(i) == 10){
                findViewById(R.id.furniture4).setVisibility(View.VISIBLE);
            }
            else if(items.get(i) == 11){
                findViewById(R.id.furniture5).setVisibility(View.VISIBLE);
            }
            else if(items.get(i) == 12){
                findViewById(R.id.furniture6).setVisibility(View.VISIBLE);
            }


        }
    }

    public void toStore(View v){
        Intent shopIntent = new Intent(this, Shop.class);
        startActivity(shopIntent);


    }

    public void itemSelected(View v){
        switch(v.getId()) {
            case R.id.hat1:
                imageBorderShow(findViewById(R.id.hat1));
                ImageView hat1= (ImageView)findViewById(R.id.octopusPet);
                hat1.setImageResource(R.drawable.octopusblue);
                break;
            case R.id.hat2:
                imageBorderShow(findViewById(R.id.hat2));
                ImageView hat2= (ImageView)findViewById(R.id.octopusPet);
                hat2.setImageResource(R.drawable.octopuscowboy);
                break;
            case R.id.hat3:
                imageBorderShow(findViewById(R.id.hat3));
                ImageView hat3= (ImageView)findViewById(R.id.octopusPet);
                hat3.setImageResource(R.drawable.octopuspink);
                break;
            case R.id.hat4:
                imageBorderShow(findViewById(R.id.hat4));
                ImageView hat4= (ImageView)findViewById(R.id.octopusPet);
                hat4.setImageResource(R.drawable.octopussanta);
                break;
            case R.id.hat5:
                imageBorderShow(findViewById(R.id.hat5));
                ImageView hat5= (ImageView)findViewById(R.id.octopusPet);
                hat5.setImageResource(R.drawable.octopuswitch);
                break;
            case R.id.hat6:
                imageBorderShow(findViewById(R.id.hat6));
                ImageView hat6= (ImageView)findViewById(R.id.octopusPet);
                hat6.setImageResource(R.drawable.octopusyellow);
                break;
            case R.id.furniture1:
                imageBorderShow(findViewById(R.id.furniture1));
                ImageView furniture1= (ImageView)findViewById(R.id.bgpet);
                furniture1.setImageResource(R.drawable.bed);
                break;
            case R.id.furniture2:
                imageBorderShow(findViewById(R.id.furniture2));
                ImageView furniture2= (ImageView)findViewById(R.id.bgpet2);
                furniture2.setImageResource(R.drawable.chair);
                break;
            case R.id.furniture3:
                imageBorderShow(findViewById(R.id.furniture3));
                ImageView furniture3= (ImageView)findViewById(R.id.bgpet);
                furniture3.setImageResource(R.drawable.desk1);
                break;
            case R.id.furniture4:
                imageBorderShow(findViewById(R.id.furniture4));
                ImageView furniture4= (ImageView)findViewById(R.id.bgpet2);
                furniture4.setImageResource(R.drawable.desk2);
                break;
            case R.id.furniture5:
                imageBorderShow(findViewById(R.id.furniture5));
                ImageView furniture5= (ImageView)findViewById(R.id.bgpet);
                furniture5.setImageResource(R.drawable.dresser);
                break;
            case R.id.furniture6:
                imageBorderShow(findViewById(R.id.furniture6));
                ImageView furniture6= (ImageView)findViewById(R.id.bgpet2);
                furniture6.setImageResource(R.drawable.sofa);
                break;
        }
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