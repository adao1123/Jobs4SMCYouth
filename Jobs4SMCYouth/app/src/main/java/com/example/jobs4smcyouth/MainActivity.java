package com.example.jobs4smcyouth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

private LinearLayout drawerLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLinearLayout = (LinearLayout)findViewById(R.id.drawer_linear_layout_id);
        
    }
}
