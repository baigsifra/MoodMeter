package com.example.moodmeter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Data extends AppCompatActivity
{
    //the graph view widget
    GraphView logScatterPlot;

    //all seekbars and corresponding numerical output widgets
    SeekBar sadHappySliderDisplay;
    TextView sadHappyNumDisplay;
    SeekBar lowHighSliderDisplay;
    TextView lowHighNumDisplay;
    SeekBar angryCalmSliderDisplay;
    TextView angryCalmNumDisplay;

    //All text widgets displaying ranges
    TextView Sad;
    TextView Happy;
    TextView lowEnergy;
    TextView highEnergy;
    TextView Angry;
    TextView Calm;

    //All journal entry widgets inside card view widget
    TextView journalEntry;
    CardView cardView;
    Button exitJournal;
    boolean isBtnClicked;

    //firestore variables
    private FirestoreHelper dbHelper;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        dbHelper = new FirestoreHelper();

        Date thisDate = new Date();
        SimpleDateFormat weekNum = new SimpleDateFormat("w");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        dbHelper.getWeek(new FirestoreHelper.MyWeek() {
            @Override
            public void onWeekCallback(Week week) {
                Log.d("pranav", week.toString());

                getScatterData(week);
            }
        }, firebaseUser.getEmail(), 43);



    }
    public void getScatterData(Week week)
    {
        //Assign XML elements to variables created above
        sadHappyNumDisplay = findViewById(R.id.sadHappyNumDisplay);
        sadHappySliderDisplay = findViewById(R.id.sadHappySliderDisplay);
        lowHighSliderDisplay = findViewById(R.id.lowHighSliderDisplay);
        lowHighNumDisplay = findViewById(R.id.lowHighNumDisplay);
        angryCalmSliderDisplay = findViewById(R.id.angryCalmSliderDisplay);
        angryCalmNumDisplay = findViewById(R.id.angryCalmNumDisplay);
        exitJournal = findViewById(R.id.exitJournalBtn);

        Sad = findViewById(R.id.Sad);
        Happy = findViewById(R.id.Happy);
        lowEnergy = findViewById(R.id.lowEnergy);
        highEnergy = findViewById(R.id.highEnergy);
        Angry = findViewById(R.id.Angry);
        Calm = findViewById(R.id.Calm);

        journalEntry = findViewById(R.id.journalEntry);
        cardView = findViewById(R.id.cardView);
        isBtnClicked = false;

        Spinner graphDropdown = findViewById(R.id.graphDropdown);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.graphs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        graphDropdown.setAdapter(adapter);

        //GraphView Initialization:
        //source: https://www.geeksforgeeks.org/line-graph-view-in-android-with-example/

        logScatterPlot = findViewById(R.id.graph);

        //Add data to the scatter plot
        PointsGraphSeries<DataPoint> logSeries = new PointsGraphSeries<>(new DataPoint[]{});
        ArrayList<Day> dayAL = week.getDayArray();

        ArrayList<Double> yPoints = new ArrayList<Double>();

        for(Day d : dayAL)
        {
            double happiness = d.getHappiness();
            double energy = d.getEnergy();
            double peacefulness = d.getPeacefulness();
            double avgMood = (.45 * happiness) + (.45 * energy) + (.1 * peacefulness);
            yPoints.add(avgMood);
        }

        logSeries = new PointsGraphSeries<>(new DataPoint[]
                {
                        new DataPoint(1, yPoints.get(0)),
                        new DataPoint(2, yPoints.get(1)),
                        new DataPoint(3, yPoints.get(2))
                });

        //Need to create a series (above) and add it too the scatterplot widget
        logScatterPlot.addSeries(logSeries);

        //Create viewport --> is essential
        Viewport vp = logScatterPlot.getViewport();

        //Set x-axis, y-axis, ranges, names, etc.
        vp.setXAxisBoundsManual(true);
        vp.setMinX(1);
        vp.setMaxX(7);
        vp.setYAxisBoundsManual(true);
        vp.setMinY(0);
        vp.setMaxY(100);
        logScatterPlot.setTitle("Mood Graph: Weekly Summary");
        logScatterPlot.setTitleTextSize(50);

        //Change the x-axis to literal elements rather than numerical values, and then render
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(logScatterPlot);
        staticLabelsFormatter.setHorizontalLabels(new String[]{"Day 1", "Day 2", "Day 3", "Day 4", "Day 5", "Day 6", "Day 7"});
        logScatterPlot.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        //Function to check if a point on scatter plot is clicked
        logSeries.setOnDataPointTapListener(new OnDataPointTapListener()
        {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint)
            {
                // Change color of the data point
                //Sets all elements visible in XML
                sadHappySliderDisplay.setVisibility(View.VISIBLE);
                sadHappyNumDisplay.setVisibility(View.VISIBLE);
                lowHighSliderDisplay.setVisibility(View.VISIBLE);
                lowHighNumDisplay.setVisibility(View.VISIBLE);
                angryCalmSliderDisplay.setVisibility(View.VISIBLE);
                angryCalmNumDisplay.setVisibility(View.VISIBLE);

                Sad.setVisibility(View.VISIBLE);
                Happy.setVisibility(View.VISIBLE);
                lowEnergy.setVisibility(View.VISIBLE);
                highEnergy.setVisibility(View.VISIBLE);
                Angry.setVisibility(View.VISIBLE);
                Calm.setVisibility(View.VISIBLE);

                //Sets the seekbars and numerical text views to equal the corresponding data value from firebase
                sadHappySliderDisplay.setProgress(50);
                sadHappyNumDisplay.setText(""+ 50);
                sadHappySliderDisplay.setEnabled(false);

                lowHighSliderDisplay.setProgress(50);
                lowHighNumDisplay.setText(""+ 50);
                lowHighSliderDisplay.setEnabled(false);

                angryCalmSliderDisplay.setProgress(50);
                angryCalmNumDisplay.setText(""+ 50);
                angryCalmSliderDisplay.setEnabled(false);

                //Checks if a point has been clicked in order to see if the journal button is applicable
                isBtnClicked = true;
            }
        });
    }

    public void showJournal(View v)
    {
        //Need to see is a point has even been clicked
        if(isBtnClicked)
        {
            journalEntry.setText("Hi This is my journal for today");
            cardView.setVisibility(View.VISIBLE);
            journalEntry.setVisibility(View.VISIBLE);
            exitJournal.setVisibility(View.VISIBLE);
        }
    }

    public void closeJournal(View v)
    {
        journalEntry.setText("");
        cardView.setVisibility(View.INVISIBLE);
        isBtnClicked = false;
    }
}

