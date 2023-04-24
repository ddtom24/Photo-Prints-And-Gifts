package com.example.photoprintsandgifts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class CartLayout extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation_cart);
        bottomNavigationView.setSelectedItemId(R.id.cart);
        //noinspection deprecation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        return true;
                    case R.id.drafts:
                        startActivity(new Intent(getApplicationContext(), DraftsLayout.class));

                        return true;
                    case R.id.account:
                       FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        if (mAuth.getCurrentUser() != null) {
                            // User is signed in
                            startActivity(new Intent(getApplicationContext(), MyProfile.class));


                        } else {
                            // No user is signed in
                            startActivity(new Intent(getApplicationContext(), AccountLayout.class));

                        }
                        return true;
                }
                return false;
            }
        });
    }
}