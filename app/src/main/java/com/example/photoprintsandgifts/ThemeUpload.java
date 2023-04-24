package com.example.photoprintsandgifts;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.photoprintsandgifts.models.Theme;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ThemeUpload extends AppCompatActivity {

EditText name;
Button submit;
DatabaseReference dbRef;
ImageView themeImage;
Uri imagePath;
StorageReference stRef;




    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null && result.getResultCode() == RESULT_OK && result.getData() != null) {

                themeImage.setVisibility(View.VISIBLE);
                Picasso.get().load(String.valueOf(result.getData().getData())).fit().into(themeImage);

                imagePath = result.getData().getData();

            }
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_upload);
        themeImage = findViewById(R.id.iv_image_theme_upload);
        name = findViewById(R.id.et_theme_name_theme_upload);
        submit = findViewById(R.id.btn_upload_theme);


        dbRef = FirebaseDatabase.getInstance().getReference("Themes");
        stRef = FirebaseStorage.getInstance().getReference("Themes");
        //.child("Theme Previews");




        themeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*"); //Filters out all files and retrieves only images
                intent.setAction(Intent.ACTION_GET_CONTENT); //tell the intent to bring in selected content
                new ActivityResultContracts.StartActivityForResult();
              //  startActivityForResult(intent, 100); Method deprecated
                startForResult.launch(intent);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("Themes");
                String  id = dRef.push().getKey();

                StorageReference reference = stRef.child(id +"."+getExtension(imagePath));
                reference.putFile(imagePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                          //  Intent i = new Intent(ThemeUpload.this, ThemeUpload.class);
                         //   startActivity(i);
                              String url = uri.toString();
                        Intent i = new Intent(ThemeUpload.this, ThemeUpload.class);

                          Theme theme = new Theme(name.getText().toString(), url);
                            dbRef.child(dbRef.push().getKey()).setValue(theme);
                            i.putExtra("ID", id);
                            i.putExtra("URL",url );
                            i.putExtra("NAME", theme.getName());
                            startActivity(i);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //making sure any empty/unsuccessful records are not stored
                            reference.delete();
                            Toast.makeText(ThemeUpload.this, "Failed to Upload Image", Toast.LENGTH_LONG).show();
                        }
                    });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });
    }


    private String getExtension(Uri path){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        //Strips the extension and returns it as a string
        return map.getExtensionFromMimeType(resolver.getType(path));

    }
}


