package com.avoid.playtolearn.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.common.Session;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Session.currentContext = SettingsActivity.this;
    }
}
