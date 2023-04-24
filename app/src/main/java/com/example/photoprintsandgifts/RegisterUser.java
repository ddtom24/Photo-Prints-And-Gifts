package com.example.photoprintsandgifts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.photoprintsandgifts.models.UserAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterUser extends AppCompatActivity {

    EditText name, surname, email, password1, password2;
    Button submit;
    CheckBox checkBox;
    TextView termsConditions;
    ImageView logo;

    FirebaseAuth m_auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        name = findViewById(R.id.et_name_register_user);
        surname = findViewById(R.id.et_surname_register_user);
        email = findViewById(R.id.et_email_register_user);
        password1 = findViewById(R.id.et_password_register_user);
        password2 = findViewById(R.id.et_verify_password_register_user);
        submit = findViewById(R.id.btn_submit_register_user);
        checkBox = findViewById(R.id.cb_terms_and_conditions);
        termsConditions = findViewById(R.id.tv_terms_conditions);
        logo = findViewById(R.id.iv_logo_register_user);

        m_auth = FirebaseAuth.getInstance();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("UserAccount");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(email.getText().toString())

                        && !TextUtils.isEmpty(password1.getText().toString())
                        && !TextUtils.isEmpty(password2.getText().toString())
                        && password1.getText().toString().compareTo(password2.getText().toString()) == 0
                        && password1.getText().toString().length() >= 6
                        && checkBox.isChecked()){
                    m_auth.createUserWithEmailAndPassword(email.getText().toString(), password1.getText().toString())
                            .addOnCompleteListener(task -> {
                                Toast.makeText(RegisterUser.this, "Registration completed", Toast.LENGTH_LONG).show();
                                final String authid = m_auth.getUid();
                                //some people do:
                                m_auth.signInWithEmailAndPassword(email.getText().toString(), password1.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                    }
                                });

                                String keyID = dbRef.push().getKey();
                                String address = "address";
                              //  dbRef.child(authid).setValue(new UserAccount( keyID, email.getText().toString(), name.getText().toString(), surname.getText().toString(), address));
                                dbRef.child(authid).setValue(new UserAccount( keyID, false, email.getText().toString(), name.getText().toString(), surname.getText().toString(), address));
                                m_auth.signOut();
                                Intent i = new Intent(RegisterUser.this, AccountLayout.class);
                                startActivity(i);


                            }).addOnFailureListener(e -> Toast.makeText(RegisterUser.this, "Registration Failed!", Toast.LENGTH_LONG).show());

                }else {
                    Toast.makeText(RegisterUser.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}













  /*      //instantiating dbRef and creating _UserAccount node in FireBase to which it will be linked
       dbRef = DatabaseReference.getInstance().getReference("UserAccount");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

if(email.getText().length() > 6 && name.getText().length() > 0 && surname.getText().length() > 0 && password1.getText().length() > 6 && password1.getText().toString() == password2.getText().toString() ) {


                UserAccount user = new UserAccount(email.getText().toString(), name.getText().toString(), surname.getText().toString(), password1.getText().toString());
 //creating a child node of _UserAccount
                dbRef.child(dbRef.push().getKey()).setValue(user);

            Intent intent = new Intent(RegisterUser.this, MainActivity.class);
            startActivity(intent);
            }
else{
    Toast.makeText(getApplicationContext(), "Something went wrong! Please verify details and try again!", Toast.LENGTH_LONG).show();
}
            }
        });


        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterUser.this, MainActivity.class));
            }
        });





    }
} */