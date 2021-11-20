package com.example.convertit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;

public class Volume extends AppCompatActivity {


    private EditText editMilliliter;
    private EditText editLiter;
    private EditText editGallon;
    private EditText editPint;
    private EditText editOunce;
    private String value;
    private View.OnFocusChangeListener onFocusChangeListener;
    private int focusedViewId;
    private TextWatcher textWatcher;



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
        setContentView(R.layout.activity_volume);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_calculator:
                        startActivity(new Intent(getApplicationContext(), Calculator.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        editMilliliter = findViewById(R.id.milliliter);
        editLiter = findViewById(R.id.liter);
        editGallon = findViewById(R.id.gallon);
        editPint = findViewById(R.id.pint);
        editOunce = findViewById(R.id.ounce);

        ImageButton buttonClear = findViewById(R.id.clearBtn);
        buttonClear.setOnClickListener(v -> clearFields());

        ImageButton backButton = findViewById(R.id.backBtn);
        backButton.setOnClickListener(v -> onBackPressed());



        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                value = ((EditText) findViewById(focusedViewId)).getText().toString().trim();

                if (value.length() > 0) {
                    convert();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        onFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if (hasFocus){
                    focusedViewId = v.getId();
                    ((EditText) findViewById(focusedViewId)).addTextChangedListener(textWatcher);
                    if(focusedViewId == R.id.milliliter){
                        v.setBackgroundResource(R.drawable.field);
                    }
                }
                else{

                    ((EditText) findViewById(focusedViewId)).removeTextChangedListener(textWatcher);
                    if(focusedViewId != R.id.milliliter){
                        v.setBackgroundResource(R.drawable.field);
                    }
                }
                v.setBackgroundResource(R.drawable.field);
            }
        };

        editMilliliter.setOnFocusChangeListener(onFocusChangeListener);
        editLiter.setOnFocusChangeListener(onFocusChangeListener);
        editGallon.setOnFocusChangeListener(onFocusChangeListener);
        editPint.setOnFocusChangeListener(onFocusChangeListener);
        editOunce.setOnFocusChangeListener(onFocusChangeListener);


    }


    private void clearFields(){
        editMilliliter.setText("");
        editLiter.setText("");
        editGallon.setText("");
        editPint.setText("");
        editOunce.setText("");
    }

    private void convert() {
        try {
            double num = 0;
            DecimalFormat dform = new DecimalFormat("###,###,###,###,###,###.#######");
            switch (focusedViewId){

                case R.id.milliliter:
                    num = Double.parseDouble(value);

                    editLiter.setText(dform.format((num/1000)));
                    editGallon.setText(dform.format((num*0.000264)));
                    editPint.setText(dform.format((num/473)));
                    editOunce.setText(dform.format((num/29.574)));
                    break;

                case R.id.liter:
                    num = Double.parseDouble(value);

                    editMilliliter.setText(dform.format((num*1000)));
                    editGallon.setText(dform.format((num/3.785)));
                    editPint.setText(dform.format((num*2.113)));
                    editOunce.setText(dform.format((num*33.814)));
                    break;

                case R.id.gallon:
                    num = Double.parseDouble(value);

                    editMilliliter.setText(dform.format((num*3785)));
                    editLiter.setText(dform.format((num*3.785)));
                    editPint.setText(dform.format((num*8)));
                    editOunce.setText(dform.format((num*128)));
                    break;

                case R.id.pint:
                    num = Double.parseDouble(value);

                    editMilliliter.setText(dform.format((num*473.176473)));
                    editLiter.setText(dform.format((num*0.473176)));
                    editGallon.setText(dform.format((num/8)));
                    editOunce.setText(dform.format((num*16)));
                    break;

                case R.id.ounce:
                    num = Double.parseDouble(value);

                    editMilliliter.setText(dform.format((num*29.57353)));
                    editLiter.setText(dform.format((num/33.814)));
                    editGallon.setText(dform.format((num/128)));
                    editPint.setText(dform.format((num/16)));
                    break;
            }

        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}