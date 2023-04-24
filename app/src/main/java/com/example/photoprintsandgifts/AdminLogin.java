package com.example.photoprintsandgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

//Importing Firebase Database libraries:
//import com.google.firebase.database.Query;
//import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {
//Declaring variables:
    private Button submit;
    private TextView forgotPass;
    private EditText id, email, pass;
    private ImageView logo;
  //  private FirebaseAuth fbAuth;
   // private Query dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
//Initializing variables:
        submit = findViewById(R.id.btn_admin_sign_in);
        id = findViewById(R.id.et_admin_login_id);
        email = findViewById(R.id.et_admin_login_email);
        pass = findViewById(R.id.et_admin_login_pass);
        forgotPass = findViewById(R.id.tv_admin_forgot_pass);
        logo = findViewById(R.id.iv_logo_user_login);
//Initializing the Firebase authentication instance:
      //  fbAuth = FirebaseAuth.getInstance();







//enabling the user to navigate back to Home page when clicking the logo
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLogin.this, MainActivity.class));
            }
        });

    }
}