package com.example.photoprintsandgifts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.photoprintsandgifts.models.UserAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyProfile extends AppCompatActivity {
private Button signOut;
private ListView listView;
private TextView welcome;
private FirebaseAuth firebaseAuth;
private DatabaseReference dbRef;
private Query query;
private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        signOut = findViewById(R.id.btn_sign_out_profile);
        welcome = findViewById(R.id.tv_profile_welcome);
        bottomNavigationView = findViewById(R.id.bottom_navigation_profile);
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

        listView = findViewById(R.id.lv_profile);
        List<String> list = new ArrayList<>();
        list.add("My Orders");
        list.add("My Addresses");
        list.add("FAQ");
        list.add("Contact Us");
        list.add("About Us");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            if (i == 0) {
                startActivity(new Intent(getApplicationContext(), MyOrders.class));
             }else if(i == 1) {
                startActivity(new Intent(getApplicationContext(), MyAddresses.class));
            } else if(i == 2){
                startActivity(new Intent(getApplicationContext(), FaqActivity.class));
                }else if (i == 3) {
                startActivity(new Intent(getApplicationContext(), ContactUs.class));
                }else {
                startActivity(new Intent(getApplicationContext(), AboutUs.class));
                }
            }

        });

        firebaseAuth = FirebaseAuth.getInstance();

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                firebaseAuth.signOut();
                startActivity(new Intent(MyProfile.this, MainActivity.class));
            }
        });


    }

    @Override
    protected void onStart() {
        if(firebaseAuth.getCurrentUser() != null){

        }else{
            startActivity(new Intent(getApplicationContext(), AccountLayout.class));
            finish();
        }
        super.onStart();
    }

/*    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss: snapshot.getChildren()){
                // if (dss)
                list.add(dss.getValue(UserAccount.class));
            }
            for(int i =0;i<list.size();i++){
                if(list.get(i).getEmail() == firebaseAuth.getCurrentUser().getEmail()){
                    String userName = list.get(i).getName();
                }

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    */

}