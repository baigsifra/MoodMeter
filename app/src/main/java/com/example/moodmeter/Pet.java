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