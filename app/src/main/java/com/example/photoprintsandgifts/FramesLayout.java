package com.example.photoprintsandgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FramesLayout extends AppCompatActivity {

   private ImageView logo, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frames);

        logo = findViewById(R.id.iv_logo_frames);
        back = findViewById(R.id.iv_back_frames);

//enabling navigation to home page
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FramesLayout.this, MainActivity.class));
            }
        });

//enabling navigation to previous activity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FramesLayout.super.onBackPressed();
            }
        });
    }



}