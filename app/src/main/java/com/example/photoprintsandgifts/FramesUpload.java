package com.example.photoprintsandgifts;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.photoprintsandgifts.adapters.ThemesAdapter;
import com.example.photoprintsandgifts.models.Frames;
import com.example.photoprintsandgifts.models.Theme;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//public class FramesUpload extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ThemesAdapter.ThemesHolder.onThemeClicked {
public class FramesUpload extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ThemesAdapter.ThemesHolder.onThemesClicked {
    EditText frameID, name, price;
    Spinner colourSpinner;
    RecyclerView themesRV;
    Button submit;
    DatabaseReference dbRef, dbRefThemes;
    ImageView frameImage;
    ArrayList<Theme> themesList = new ArrayList<>();
    String frameColour, frameTheme;
    Uri imagePath;

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null && result.getResultCode() == RESULT_OK && result.getData() != null) {

                frameImage.setVisibility(View.VISIBLE);
                Picasso.get().load(String.valueOf(result.getData().getData())).fit().into(frameImage);

                imagePath = result.getData().getData();
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frames_upload);

        frameID = findViewById(R.id.et_id_frames);
        name = findViewById(R.id.et_name_frames);
        price = findViewById(R.id.et_price_frames);
        colourSpinner = findViewById(R.id.spinner_colour);
        themesRV = findViewById(R.id.rv_themes_frames);
        themesRV.setLayoutManager(new LinearLayoutManager(FramesUpload.this, LinearLayoutManager.HORIZONTAL, false));
        //  themesRV.setLayoutManager(new LinearLayoutManager(FramesUpload.this));
        submit = findViewById(R.id.btn_submit_frames);
        frameImage = findViewById(R.id.iv_image_frames);
        dbRef = FirebaseDatabase.getInstance().getReference("FrameTemplates");
        StorageReference stRef = FirebaseStorage.getInstance().getReference();

        //implementation for RecyclerView
        dbRefThemes = FirebaseDatabase.getInstance().getReference("Themes");
        dbRefThemes.addListenerForSingleValueEvent(listener);
        themesRV.setLayoutManager(new LinearLayoutManager(FramesUpload.this, LinearLayoutManager.HORIZONTAL, false));


        //implementation for Spinner
        ArrayAdapter<CharSequence> colourAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.frames_colours_array, android.R.layout.simple_spinner_item);

        colourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colourSpinner.setAdapter(colourAdapter);

        colourSpinner.setOnItemSelectedListener(this);

        frameImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                //noinspection deprecation
                startActivityForResult(i, 100);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("FrameTemplates");
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
                                Intent i = new Intent(FramesUpload.this, FramesUpload.class);

                                frameColour = colourSpinner.getSelectedItem().toString();
                                Frames frame = new Frames(frameID.getText().toString(),name.getText().toString(),
                                        price.getText().toString(), frameColour, frameTheme, url);

                                dbRef.child(dbRef.push().getKey()).setValue(frame);
                                Toast.makeText(FramesUpload.this,
                                        "Product Uploaded Successfully!", Toast.LENGTH_LONG).show();


                                dbRef.child(dbRef.push().getKey()).setValue(frame);
                                i.putExtra("ID", id);
                                i.putExtra("URL",url );
                                i.putExtra("NAME", frame.getProductName());
                                startActivity(i);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //making sure any empty/unsuccessful records are not stored
                                reference.delete();
                                Toast.makeText(FramesUpload.this, "Failed to Upload Image", Toast.LENGTH_LONG).show();
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
    private String getExtension(Uri path) {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        //Strips the extension and returns it as a string
        return map.getExtensionFromMimeType(resolver.getType(path));
    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot ds: snapshot.getChildren()){
                //  themesList.add(ds.getValue(Theme.class));
                Theme theme = ds.getValue(Theme.class);
                themesList.add(theme);

            }
            ThemesAdapter adapter = new ThemesAdapter(themesList, FramesUpload.this);
            themesRV.setAdapter(adapter);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    public void onThemesClick(int pos) {
      //  Intent i = new Intent(this, FramesUpload.class);
      //  Intent i = new Intent();
     //   i.putExtra("Theme", themesList.get(pos).getName());
      //  FramesUpload.this.sendBroadcast(i);
        // startActivity(i);
       frameTheme = themesList.get(pos).getName();


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      //  String colour = colourSpinner.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
