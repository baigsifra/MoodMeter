package com.example.moodmeter;

import android.os.Bundle;
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

    private GraphView logScatterPlot;
    private SeekBar sadHappySliderDisplay;
    private TextView sadHappyNumDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        sadHappySliderDisplay = findViewById(R.id.sadHappySlider);
        sadHappyNumDisplay = findViewById(R.id.sadHappyNum);
        //sadHappyNumDisplay.setText("50");
        //source: https://www.geeksforgeeks.org/line-graph-view-in-android-with-example/
        //initialize graph view
        logScatterPlot = findViewById(R.id.graph);
        // on below line we are adding data to our graph view.
        PointsGraphSeries<DataPoint> logSeries = new PointsGraphSeries<DataPoint>(new DataPoint[]{
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
                Toast.makeText(Data.this, "Series1: On Data Point clicked: " + dataPoint, Toast.LENGTH_SHORT).show();
                //Change code to set num equal to y-axis value
                //sadHappySliderDisplay.setProgress(50);
                //sadHappySliderDisplay.setEnabled(false);
                //sadHappyNumDisplay.setText("50");

            }
        });

}
}