package com.example.photoprintsandgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.photoprintsandgifts.models.Stickers;
import com.google.firebase.storage.FirebaseStorage;

import java.net.URI;

public class StickersUpload extends AppCompatActivity {
ImageView themePreview;
TextView themeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stickers_upload);
        themePreview = findViewById(R.id.iv_theme_preview_stickers_upload);
        themeName = findViewById(R.id.tv_theme_name_stickers_upload);

        Intent i = getIntent();
        String themeID = i.getStringExtra("ID");
        String themeURL = i.getStringExtra("URI");
        String name = i.getStringExtra("NAME");
        Stickers sticker = new Stickers(themeID);



        themeName.setText(name);
    }
}