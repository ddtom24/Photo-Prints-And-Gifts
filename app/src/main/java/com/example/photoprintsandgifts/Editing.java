package com.example.photoprintsandgifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.photoprintsandgifts.models.BitmapClass;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.util.List;

import ja.burhanrashid52.photoeditor.OnSaveBitmap;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;

public class Editing extends AppCompatActivity implements AddFrameListener, AddStickersListener {

    PhotoEditorView photoEditorView;
    PhotoEditor photoEditor;
    ConstraintLayout constraintLayout;


    Button save, addFrames, addStickers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing);

        photoEditorView = findViewById(R.id.photo_editor_view_editing);
        photoEditorView.getSource().setImageURI(getIntent().getData());
        constraintLayout = findViewById(R.id.cl_editing);

        photoEditor = new PhotoEditor.Builder(this, photoEditorView)
                .setPinchTextScalable(true).build();

        addFrames = findViewById(R.id.btn_editing_add_frame);
        addStickers = findViewById(R.id.btn_editing_add_sticker);
        save = findViewById(R.id.btn_editing_save_changes);


        addFrames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            FrameFragment frameFragment = FrameFragment.getInstance();
            frameFragment.setListener(Editing.this);
            frameFragment.show(getSupportFragmentManager(), frameFragment.getTag());


            }
        });

        addStickers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StickersFragment stickersFragment = StickersFragment.getInstance();
                stickersFragment.setListener(Editing.this);
                stickersFragment.show(getSupportFragmentManager(), stickersFragment.getTag());
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage();
            }
        });
    }


    private void saveImage() {
        Dexter.withContext(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener(){

                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                        if (multiplePermissionsReport.areAllPermissionsGranted()){
                            photoEditor.saveAsBitmap(new OnSaveBitmap() {
                                @Override
                                public void onBitmapReady(Bitmap saveBitmap) {
                                    photoEditorView.getSource().setImageBitmap(saveBitmap);
                                    try {
                                        final String path = BitmapClass.insertImage(getContentResolver(), saveBitmap,
                                                System.currentTimeMillis() + "_profile.jpg", null);
                                        if(!TextUtils.isEmpty(path)){
                                            Snackbar snackbar = Snackbar.make(constraintLayout, "Image saved to your gallery",
                                                    Snackbar.LENGTH_LONG).setAction("OPEN", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    openImageLocation(path);
                                                }
                                            });
                                            snackbar.show();
                                        }else{
                                            Snackbar snackbar = Snackbar.make(constraintLayout, "Try again",
                                                    Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(Exception e) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();
    }

    private void openImageLocation(String path) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(path), "image/*");
        startActivity(intent);
    }

    @Override
    public void onAddFrame(int frame) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), frame);
        photoEditor.addImage(bitmap);
    }

    @Override
    public void onAddStickers(int stickers) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), stickers);
        photoEditor.addImage(bitmap);
    }
}