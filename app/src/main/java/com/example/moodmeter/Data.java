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

    String journ = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        dbHelper = new FirestoreHelper();

        Date thisDate = new Date();
        SimpleDateFormat weekNumFormat = new SimpleDateFormat("w");
        int weekNum = Integer.parseInt(weekNumFormat.format(thisDate));

        dbHelper.getWeekIds(new FirestoreHelper.MyWeekIds() {
            @Override
            public void onWeekIdsCallback(ArrayList<Integer> idAL) {
                displayGraph(idAL);
            }
        }, firebaseUser.getEmail());
    }

    public void displayGraph(ArrayList<Integer> idAL)
    {
        Date thisDate = new Date();
        SimpleDateFormat weekNumFormat = new SimpleDateFormat("w");
        int weekNum = Integer.parseInt(weekNumFormat.format(thisDate));
        dbHelper.getWeek(new FirestoreHelper.MyWeek() {
            @Override
            public void onWeekCallback(Week week) {

                getScatterData(week, idAL);
            }
        }, firebaseUser.getEmail(), weekNum);
    }

    public void getScatterData(Week week, ArrayList<Integer> idAL)
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

        ArrayList<String> spinnerArray = new ArrayList<String>();

        for(int i = 0; i < idAL.size(); i++) {
            spinnerArray.add("Week" + (i + 1));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        graphDropdown.setAdapter(adapter);

        graphDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                //TODO Auto-generated method stub
                String ss = graphDropdown.getSelectedItem().toString();
                Toast.makeText(getBaseContext(), spinnerArray.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //TODO Auto-generated method stub
            }
        });

        //GraphView Initialization:
        //source: https://www.geeksforgeeks.org/line-graph-view-in-android-with-example/

        logScatterPlot = findViewById(R.id.graph);

        //Add data to the scatter plot
        PointsGraphSeries<DataPoint> logSeries = new PointsGraphSeries<>(new DataPoint[]{});
        ArrayList<Day> dayAL = week.getDayArray();

        ArrayList<Integer> xPoints = new ArrayList<Integer>();
        ArrayList<Double> yPoints = new ArrayList<Double>();

        for(Day d : dayAL)
        {
            double happiness = d.getHappiness();
            double energy = d.getEnergy();
            double peacefulness = d.getPeacefulness();
            double avgMood = (.45 * happiness) + (.45 * energy) + (.1 * peacefulness);
            int id = (int)(d.getDayNumId());
            xPoints.add(id);
            yPoints.add(avgMood);
        }

        DataPoint[] dpa = new DataPoint[xPoints.size()];
        for(int i = 0; i < xPoints.size(); i++) {
            dpa[i] = new DataPoint(xPoints.get(i), yPoints.get(i));
        }

        logSeries = new PointsGraphSeries<>(dpa);

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
        staticLabelsFormatter.setHorizontalLabels(new String[]{"S", "M", "T", "W", "Th", "F", "Sa"});
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

                double xCoord = dataPoint.getX();
                Day d = new Day();
                for(Day dIW : dayAL) {
                    if(dIW.getDayNumId() == xCoord) {
                        d = new Day(dIW.getDate(), dIW.getJournalEntry(), dIW.getHappiness(), dIW.getEnergy(), dIW.getPeacefulness(), dIW.getDayNumId());
                    }
                }

                journ = d.getJournalEntry();

                //Sets the seekbars and numerical text views to equal the corresponding data value from firebase
                sadHappySliderDisplay.setProgress((int)(d.getHappiness()));
                sadHappyNumDisplay.setText(""+ (int)(d.getHappiness()));
                sadHappySliderDisplay.setEnabled(false);

                lowHighSliderDisplay.setProgress((int)(d.getEnergy()));
                lowHighNumDisplay.setText(""+ (int)(d.getEnergy()));
                lowHighSliderDisplay.setEnabled(false);

                angryCalmSliderDisplay.setProgress((int)(d.getPeacefulness()));
                angryCalmNumDisplay.setText(""+ (int)(d.getPeacefulness()));
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
            journalEntry.setText(journ);
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
