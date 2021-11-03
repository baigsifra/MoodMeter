package com.example.moodmeter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

public class Data extends AppCompatActivity {

    GraphView logScatterPlot;

    SeekBar sadHappySliderDisplay;
    TextView sadHappyNumDisplay;
    SeekBar lowHighSliderDisplay;
    TextView lowHighNumDisplay;
    SeekBar angryCalmSliderDisplay;
    TextView angryCalmNumDisplay;
    TextView Sad;
    TextView Happy;
    TextView lowEnergy;
    TextView highEnergy;
    TextView Angry;
    TextView Calm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        sadHappyNumDisplay = findViewById(R.id.sadHappyNumDisplay);
        sadHappySliderDisplay = findViewById(R.id.sadHappySliderDisplay);
        lowHighSliderDisplay = findViewById(R.id.lowHighSliderDisplay);
        lowHighNumDisplay = findViewById(R.id.lowHighNumDisplay);
        angryCalmSliderDisplay = findViewById(R.id.angryCalmSliderDisplay);
        angryCalmNumDisplay = findViewById(R.id.angryCalmNumDisplay);
        Sad = findViewById(R.id.Sad);
        Happy = findViewById(R.id.Happy);
        lowEnergy = findViewById(R.id.lowEnergy);
        highEnergy = findViewById(R.id.highEnergy);
        Angry = findViewById(R.id.Angry);
        Calm = findViewById(R.id.Calm);


        //source: https://www.geeksforgeeks.org/line-graph-view-in-android-with-example/
        //initialize graph view
        logScatterPlot = findViewById(R.id.graph);
        // on below line we are adding data to our graph view.
        PointsGraphSeries<DataPoint> logSeries = new PointsGraphSeries<>(new DataPoint[]{
                new DataPoint(1, 1),
                new DataPoint(2, 3),
                new DataPoint(3, 20),
                new DataPoint(4, 6),
                new DataPoint(5, 3),
                new DataPoint(6, 6),
                new DataPoint(7, 10)
        });
        logScatterPlot.addSeries(logSeries);
        Viewport vp = logScatterPlot.getViewport();
        vp.setXAxisBoundsManual(true);
        vp.setMinX(1);
        vp.setMaxX(7);
        vp.setYAxisBoundsManual(true);
        vp.setMinY(0);
        vp.setMaxY(100);
        logScatterPlot.setTitle("Mood Graph: Weekly Summary");
        logScatterPlot.setTitleTextSize(50);


        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(logScatterPlot);
        staticLabelsFormatter.setHorizontalLabels(new String[]{"Day 1", "Day 2", "Day 3", "Day 4", "Day 5", "Day 6", "Day 7"});
        logScatterPlot.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        //change instead of toast to make it show data below

        logSeries.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
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

                //Toast.makeText(Data.this, "Series1: On Data Point clicked: " + dataPoint, Toast.LENGTH_SHORT).show();
                //set to y-axis var
                sadHappySliderDisplay.setProgress(50);
                sadHappyNumDisplay.setText(""+ 50);
                sadHappySliderDisplay.setEnabled(false);

                lowHighSliderDisplay.setProgress(50);
                lowHighNumDisplay.setText(""+ 50);
                lowHighSliderDisplay.setEnabled(false);

                angryCalmSliderDisplay.setProgress(50);
                angryCalmNumDisplay.setText(""+ 50);
                angryCalmSliderDisplay.setEnabled(false);
            }
        });

    }
    public void showJournal(View v){

    }
}