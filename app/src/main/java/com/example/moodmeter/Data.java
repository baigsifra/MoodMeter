package com.example.moodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Data extends AppCompatActivity {

    GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        //source: https://www.geeksforgeeks.org/line-graph-view-in-android-with-example/
        //initialize graph view
        graphView = findViewById(R.id.graph);
        // on below line we are adding data to our graph view.
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(3, 20),
                new DataPoint(4, 6),
                new DataPoint(5, 3),
                new DataPoint(8, 6)
        });
        graphView.setTitle("Example Graph");
        graphView.setTitleTextSize(50);
        graphView.addSeries(series);
}
}