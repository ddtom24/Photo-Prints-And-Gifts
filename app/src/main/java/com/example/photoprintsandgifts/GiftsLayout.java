package com.example.photoprintsandgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GiftsLayout extends AppCompatActivity {

   private ImageView logo, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifts);

        logo = findViewById(R.id.iv_logo_gifts);
        back = findViewById(R.id.iv_back_gifts);

        //enabling navigation to home page
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GiftsLayout.this, MainActivity.class));
            }
        });

//enabling navigation to previous activity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiftsLayout.super.onBackPressed();
            }
        });
    }
}