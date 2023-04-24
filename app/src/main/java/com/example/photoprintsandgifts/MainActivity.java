package com.example.photoprintsandgifts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.photoprintsandgifts.databinding.ActivityMainBinding;
import com.example.photoprintsandgifts.models.UserAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ImageView frames, crafts, gifts, kits;
BottomNavigationView bottomNavigationView;
private FirebaseAuth mAuth;
FirebaseAuth.AuthStateListener listener;
Button goToAccount;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frames = findViewById(R.id.iv_frames);
        crafts = findViewById(R.id.iv_crafts);
        gifts = findViewById(R.id.iv_gifts);
        kits = findViewById(R.id.iv_kits);
        goToAccount = findViewById(R.id.btn_home_account);

        mAuth = FirebaseAuth.getInstance();


        bottomNavigationView = findViewById(R.id.bottom_navigation_main);
        bottomNavigationView.setSelectedItemId(R.id.home);
     //   bottomNavigationView.setVisibility(View.INVISIBLE);

        //noinspection deprecation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                case R.id.drafts:
                startActivity(new Intent(getApplicationContext(), DraftsLayout.class));
                return true;

                case R.id.cart:
                startActivity(new Intent(getApplicationContext(), CartLayout.class));
                return true;

                case R.id.account:

                        startActivity(new Intent(getApplicationContext(), MyProfile.class));

                return true;

            }
                 return false;
            }

        });


        frames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FramesLayout.class);
                startActivity(i);
            }
        });

        crafts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CraftsLayout.class);
                startActivity(i);
            }
        });

        gifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GiftsLayout.class);
                startActivity(i);
            }
        });

        kits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, KitsLayout.class);
                startActivity(i);
            }
        });

    }


}
 /*



*/