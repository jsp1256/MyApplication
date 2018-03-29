package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {

    private TextView location_now;
    private String Location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Location="后街";
        location_now=(TextView)findViewById(R.id.tv_location_now);
        location_now.setText("当前位置:"+Location);
    }
}
