package com.example.miwok;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
          this code change the laytout direction to be ltr in all languages
         */
//        Configuration config;
//        config = new Configuration(getResources().getConfiguration());
//        config.locale = Locale.ENGLISH;
//        config.setLayoutDirection(new Locale("en"));
//        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // find the TextView of numbers category
        TextView num = findViewById(R.id.numbers);

        // find the TextView of colors category
        TextView col = findViewById(R.id.colors);

        // find the TextView of phrased category
        TextView phs = findViewById(R.id.phrases);

        // find the TextView of family members category
        TextView fml = findViewById(R.id.family);

        //set on click listener for numbers TextView
        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Opens Numbers activity
                startActivity(new Intent(MainActivity.this, NumbersActivity.class));
            }
        });

        //set on click listener for colors TextView
        col.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Opens Colors activity
                startActivity(new Intent(MainActivity.this,ColorsActivity.class));
            }
        });

        //set on click listener for phrases TextView
        phs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Opens Phrases activity
                startActivity(new Intent(MainActivity.this,PhrasesActivity.class));
            }
        });

        //set on click listener for family members TextView
        fml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Opens FamilyMembers activity
                startActivity(new Intent(MainActivity.this,FamilyActivity.class));
            }
        });
    }

}