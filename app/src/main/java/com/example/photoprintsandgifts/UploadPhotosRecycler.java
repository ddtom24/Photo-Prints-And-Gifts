package com.example.photoprintsandgifts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.photoprintsandgifts.adapters.UploadPhotosAdapter;

import java.util.ArrayList;

public class UploadPhotosRecycler extends AppCompatActivity implements UploadPhotosAdapter.UpdatedImageCount{
    RecyclerView rv;
    Button upload;
    ImageView pick;

    ArrayList<Uri> uri = new ArrayList<>();
    UploadPhotosAdapter adapter;

    private static final int Read_Permission = 101;
    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photos_recycler);
        rv = findViewById(R.id.rv_upload_photos);
        upload = findViewById(R.id.btn_upload_photos_recycler);
        pick = findViewById(R.id.iv_add_photos_upload);

        adapter = new UploadPhotosAdapter(uri, getApplicationContext(),this);
        rv.setLayoutManager(new GridLayoutManager(UploadPhotosRecycler.this, 3));
        rv.setAdapter(adapter);



        pick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(UploadPhotosRecycler.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(UploadPhotosRecycler.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Read_Permission);
                    return;
                }

               Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

                intent.setType("image/*");
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
              }

                intent.setAction(Intent.ACTION_GET_CONTENT);
                //noinspection deprecation
                startActivityForResult(Intent.createChooser(intent, "Select Photos"), PICK_IMAGE);

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && null != data) {
            if(data.getClipData() != null){

                //selecting multiple photos
                int imageCount = data.getClipData().getItemCount();
                for (int i=0;i<imageCount;i++){
                    //specifying maximum number of photos for the project and limiting selection
                    //     if(uri.size() <= 10){
                    Uri imageURI = data.getClipData().getItemAt(i).getUri();
                    //adding each image path to the array
                    uri.add(imageURI);
                    //       } else {
                    //       Toast.makeText(UploadPhotosRecycler.this,
                    //                 "You can only pick 10 photos for this project.",
                    //                 Toast.LENGTH_LONG).show();
                    //  }


                }
                //notifying the adapter
                adapter.notifyDataSetChanged();
                upload.setText("Upload " + uri.size() +" photo(s)");

            }else {
                //   if(uri.size() <= 10) {
                //selecting one photo at the time
                Uri imageURI = data.getData();
                //adding image path to array
                uri.add(imageURI);
                //   }else {
                //      Toast.makeText(UploadPhotosRecycler.this,
                //              "You can only pick 10 photos for this project.",
                //               Toast.LENGTH_LONG).show();
                //    }

            }
            //notifying the adapter
            adapter.notifyDataSetChanged();
            upload.setText("Upload " + uri.size() +" photo(s)");
        }else {
            //when no photo has been selected
            Toast.makeText(this, "No photos have been selected", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void clicked(int getSize) {
        upload.setText("Upload " + uri.size() +" photo(s)");
    }
}


  /*       if(requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && null != data){
            if(data.getClipData() != null){
                int x = data.getClipData().getItemCount();

                for(int i=0;i<x;i++){
                    uri.add(data.getClipData().getItemAt(i).getUri());
                }
                adapter.notifyDataSetChanged();
                upload.setText("Upload " + uri.size() +" photos");

            } else if(data.getData() != null){

               String imageURL = data.getData().getPath();
               uri.add(Uri.parse(imageURL));
             //  uri.add(data.getData());
            }
            adapter.notifyDataSetChanged();
            upload.setText("Upload " + uri.size() +" photos");
        }

        */