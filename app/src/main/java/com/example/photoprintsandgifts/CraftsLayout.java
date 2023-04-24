package com.example.photoprintsandgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CraftsLayout extends AppCompatActivity {

    private ImageView logo, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crafts);

        logo = findViewById(R.id.iv_logo_crafts);
        back = findViewById(R.id.iv_back_crafts);

        //enabling navigation to home page
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CraftsLayout.this, MainActivity.class));
            }
        });

//enabling navigation to previous activity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CraftsLayout.super.onBackPressed();
            }
        });

    }

}