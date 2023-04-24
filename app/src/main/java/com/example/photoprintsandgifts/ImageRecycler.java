package com.example.photoprintsandgifts;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoprintsandgifts.adapters.ImageAdapter;
import com.example.photoprintsandgifts.models.Theme;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ImageRecycler extends AppCompatActivity implements ImageAdapter.ImageHolder.onImageClicked{
    private RecyclerView rv;
    private ImageAdapter adaptor;
    private ArrayList<Theme> list = new ArrayList<>();
    private DatabaseReference dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_recycler);
        rv = findViewById(R.id.rv_image_recycler);
        dbref = FirebaseDatabase.getInstance().getReference("Themes");
       // rv.setLayoutManager(new LinearLayoutManager(ImageRecycler.this)); //vertical

        rv.setLayoutManager(new LinearLayoutManager(ImageRecycler.this, LinearLayoutManager.HORIZONTAL, false));
        //horizontal:
        //   rv.setLayoutManager(new LinearLayoutManager(Recycler.this, LinearLayoutManager.HORIZONTAL, true));
        // rv.setLayoutManager(new GridLayoutManager(Recycler.this, 3));
        // if ("text" == "Chats")

        //else if ("text" == "Reviews")
        //    dbref = FirebaseDatabase.getInstance().getReference("_person_");
        dbref.addListenerForSingleValueEvent(listener);
    }
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss: snapshot.getChildren()){
                list.add(dss.getValue(Theme.class));
            }
            adaptor = new ImageAdapter(list, ImageRecycler.this);
            rv.setAdapter(adaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    public void onImageClick(int pos) {
       /* Intent i = new Intent(this, Recycler.class);
        i.putExtra("Book", (Parcelable) list.get(pos));
        startActivity(i); */
    }

}