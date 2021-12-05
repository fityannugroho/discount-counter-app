package com.fityan.aplikasimenghitungdiskon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    /* View Elements */
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize View Elements */
        btnStart = findViewById(R.id.btnStart);

        /* When Start Button is clicked */
        btnStart.setOnClickListener(view -> {
            /* Go to home page */
            Intent intentHome = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intentHome);
        });
    }
}