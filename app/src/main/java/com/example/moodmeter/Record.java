package com.example.moodmeter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public class Record extends AppCompatActivity {

    private SeekBar sadHappySlider;
    private TextView sadHappyNum;
    private SeekBar lowHighSlider;
    private TextView lowHighNum;
    private SeekBar angryCalmSlider;
    private TextView angryCalmNum;
    private FirestoreHelper dbHelper;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        sadHappySlider = findViewById(R.id.sadHappySlider);
        sadHappyNum = findViewById(R.id.sadHappyNum);
        dbHelper = new FirestoreHelper();
        sliderFunction(sadHappySlider,sadHappyNum);

        lowHighSlider = findViewById(R.id.lowHighSlider);
        lowHighNum = findViewById(R.id.lowHighNum);
        sliderFunction(lowHighSlider, lowHighNum);

        angryCalmSlider = findViewById(R.id.angryCalmSlider);
        angryCalmNum = findViewById(R.id.angryCalmNum);
        sliderFunction(angryCalmSlider, angryCalmNum);
    }

    public void sliderFunction(SeekBar SB, TextView TV){
        SB.setProgress(50);
        SB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TV.setText("" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }



    //https://stackoverflow.com/questions/5944987/how-to-create-a-popup-window-popupwindow-in-android
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void submitRecord(View v) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, false);
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

        Date thisDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat dayIdStr = new SimpleDateFormat("EEEE");
        double dayId;
        if(dayIdStr.format(thisDate).equals("Sunday")) { dayId = 1; }
        else if (dayIdStr.format(thisDate).equals("Monday")){ dayId = 2; }
        else if (dayIdStr.format(thisDate).equals("Tuesday")){ dayId = 3; }
        else if (dayIdStr.format(thisDate).equals("Wednesday")){ dayId = 4; }
        else if (dayIdStr.format(thisDate).equals("Thursday")){ dayId = 5; }
        else if (dayIdStr.format(thisDate).equals("Friday")){ dayId = 6; }
        else { dayId = 7; }

        double sadHappyVal = sadHappySlider.getProgress();
        double lowHighVal = lowHighSlider.getProgress();
        double angryCalmVal = angryCalmSlider.getProgress();
        EditText journalET = findViewById(R.id.journalET);
        String journalEntry = journalET.getText().toString();

        /*
        * https://www.javatpoint.com/java-get-current-date
        * Link to how to format date
        * */
        Day day = new Day(dateFormat.format(thisDate), journalEntry, sadHappyVal, lowHighVal, angryCalmVal, dayId);
        dbHelper.addDay(firebaseUser.getEmail(), day, dateFormat.format(thisDate));

        double coinsGained = 10;
    }

    public void toPet(View v){
        Intent petIntent = new Intent(this, Pet.class);
        startActivity(petIntent);
    }

    public void toData(View v){
        Intent dataIntent = new Intent(this, Data.class);
        startActivity(dataIntent);
    }

}