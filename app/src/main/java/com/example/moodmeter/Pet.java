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
        TabHost.TabSpec spec = tabs.newTabSpec("Hats");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Hats");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("Furniture");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Furniture");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("Inventory");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Inventory");
        tabs.addTab(spec);
    }

    public void toStore(View v){
        Intent shopIntent = new Intent(this, Shop.class);
        startActivity(shopIntent);
    }

}