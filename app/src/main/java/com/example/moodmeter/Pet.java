package com.example.moodmeter;
import android.content.Intent;
import android.view.View;
import android.widget.TabHost;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
public class Pet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        TabHost tabs = (TabHost) findViewById(R.id.tabHostPet);
        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("First");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("second");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("tag3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("third");
        tabs.addTab(spec);
    }

    public void toStore(View v){
        Intent shopIntent = new Intent(getApplicationContext(), Shop.class);
        startActivity(shopIntent);
    }
}