package com.example.moodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Record extends AppCompatActivity {

    private SeekBar sadHappySlider;
    private TextView sadHappyNum;
    private SeekBar lowHighSlider;
    private TextView lowHighNum;
    private SeekBar angryCalmSlider;
    private TextView angryCalmNum;
    private FirestoreHelper dbHelper;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        sadHappySlider = findViewById(R.id.sadHappySlider);
        sadHappySlider.setProgress(50);
        sadHappyNum = findViewById(R.id.sadHappyNum);
        dbHelper = new FirestoreHelper();
        sadHappySlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sadHappyNum.setText("" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        lowHighSlider = findViewById(R.id.lowHighSlider);
        lowHighSlider.setProgress(50);
        lowHighNum = findViewById(R.id.lowHighNum);
        lowHighSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                lowHighNum.setText("" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        angryCalmSlider = findViewById(R.id.angryCalmSlider);
        angryCalmSlider.setProgress(50);
        angryCalmNum = findViewById(R.id.angryCalmNum);
        angryCalmSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                angryCalmNum.setText("" + i);
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
    public void submitRecord(View v) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, false);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);


        // ifra and pranav use this and store in firebase hee hee :)
        Date thisDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        double sadHappyVal = sadHappySlider.getProgress();
        double lowHighVal = lowHighSlider.getProgress();
        double angryCalmVal = angryCalmSlider.getProgress();
        EditText journalET = findViewById(R.id.journalET);
        String journalEntry = journalET.getText().toString();
        double avgMood = (0.45 * sadHappyVal) + (0.45 * lowHighVal) + (0.1 * angryCalmVal);

        /*
        * https://www.javatpoint.com/java-get-current-date
        * Link to how to format date
        * */
        Day day = new Day(dateFormat.format(thisDate), journalEntry, sadHappyVal, lowHighVal, angryCalmVal);
        dbHelper.addDay(currentUser.getEmail(), day, dateFormat.format(thisDate));
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