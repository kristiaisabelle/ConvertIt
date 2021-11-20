package com.example.convertit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        View decor_View = getWindow().getDecorView();
        int ui_Options = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decor_View.setSystemUiVisibility(ui_Options);
        setContentView(R.layout.activity_dashboard);


        ImageButton lengthButton = findViewById(R.id.lengthBtn);
        ImageButton volumeButton = findViewById(R.id.volumeBtn);
        ImageButton massButton = findViewById(R.id.massBtn);
        ImageButton tempButton = findViewById(R.id.areaBtn);
        ImageButton timeButton = findViewById(R.id.timeBtn);

        lengthButton.setOnClickListener(v -> startActivity(new Intent(Dashboard.this,Length.class))
        );
        volumeButton.setOnClickListener(v -> startActivity(new Intent(Dashboard.this,Volume.class))
        );
        massButton.setOnClickListener(v -> startActivity(new Intent(Dashboard.this,Mass.class))
        );
        tempButton.setOnClickListener(v -> startActivity(new Intent(Dashboard.this, Area.class))
        );
        timeButton.setOnClickListener(v -> startActivity(new Intent(Dashboard.this,Time.class))
        );

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.nav_home:
                        return true;

                    case R.id.nav_calculator:
                        startActivity(new Intent(getApplicationContext(), Calculator.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

    }
}