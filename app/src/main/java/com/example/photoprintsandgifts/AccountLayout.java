package com.example.photoprintsandgifts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class AccountLayout extends AppCompatActivity {

BottomNavigationView bottomNavigationView;
private ImageView logo;
private TextView welcome;
private Button signIn, signUp;
private ListView listView;
private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_layout);
        logo = findViewById(R.id.iv_logo_account);
        welcome = findViewById(R.id.tv_account_welcome);
        signIn = findViewById(R.id.btn_sign_in_account);
        signUp = findViewById(R.id.btn_sign_up_account);
        firebaseAuth = FirebaseAuth.getInstance();

        listView  = findViewById(R.id.lv_account);
        listView = findViewById(R.id.lv_account);
        List<String> list = new ArrayList<>();
        list.add("FAQ");
        list.add("Contact Us");
        list.add("About Us");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                   startActivity(new Intent(getApplicationContext(), FaqActivity.class));
                }else if (i == 1) {
                    startActivity(new Intent(getApplicationContext(), ContactUs.class));
                }
                    else {
                    startActivity(new Intent(getApplicationContext(), AboutUs.class));
                    }
                }

        });

        bottomNavigationView = findViewById(R.id.bottom_navigation_account);
        bottomNavigationView.setSelectedItemId(R.id.account);
        //noinspection deprecation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.home:

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        return true;
                    case R.id.drafts:
                        startActivity(new Intent(getApplicationContext(), DraftsLayout.class));

                        return true;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(), CartLayout.class));

                        return true;
                }
                return false;
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountLayout.this, UserLogin.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountLayout.this, RegisterUser.class));
            }
        });



 /*       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            signIn.setVisibility(View.GONE);
            signUp.setVisibility(View.GONE);
            signOut.setVisibility(View.VISIBLE);

            signOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firebaseAuth.signOut();
                    startActivity(new Intent(AccountLayout.this, MainActivity.class));
                }
            });

            // User is signed in
        } else {
            // No user is signed in
            signIn.setVisibility(View.VISIBLE);
            signUp.setVisibility(View.VISIBLE);
            signOut.setVisibility(View.GONE);
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(AccountLayout.this, UserLogin.class));
                }
            });

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(AccountLayout.this, RegisterUser.class));
                }
            });

         finish();
               }
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(AccountLayout.this, MainActivity.class));
                }
            });
*/
        }


    }

