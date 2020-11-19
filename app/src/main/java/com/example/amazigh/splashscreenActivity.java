package com.example.amazigh;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class splashscreenActivity extends AppCompatActivity {

    int SPLASH_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        //Bovenste bar weghalen
getSupportActionBar().hide();
        //start een timer van 3 seconden en gaat daarna naar de volgende pagina
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //navigatie naar het menu
                Intent splashscreen = new Intent(splashscreenActivity.this, MainActivity.class);
                startActivity(splashscreen);


            }
        }, SPLASH_TIME);
    }

}