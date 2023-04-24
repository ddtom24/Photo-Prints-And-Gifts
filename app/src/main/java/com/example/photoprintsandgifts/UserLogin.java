package com.example.photoprintsandgifts;



import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.photoprintsandgifts.models.UserAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserLogin extends AppCompatActivity {
    //declaring reference objects
    FirebaseAuth m_auth;
    EditText email, password;
    Button login, signUp;
    TextView resetPass;
    DatabaseReference dbref;
    FirebaseUser m_user;
    FirebaseAuth.AuthStateListener mAuthListener;

    //creating array lists to store input values
    ArrayList<UserAccount> userList= new ArrayList<>();
    ArrayList<String> idList= new ArrayList<>();
    ArrayList<String> emailList= new ArrayList<>();
    ArrayList<String> nameList= new ArrayList<>();
    ArrayList<String> surnameList= new ArrayList<>();
    ArrayList<String> addressList= new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        // Initialising reference objects:
        email = findViewById(R.id.tv_user_login_email);
        password = findViewById(R.id.tv_user_login_pass);
        login = findViewById(R.id.btn_user_sign_in);
        resetPass = findViewById(R.id.tv_user_login_forgot_pass);
        m_auth = FirebaseAuth.getInstance();
        dbref = FirebaseDatabase.getInstance().getReference("UserAccount");
        signUp = findViewById(R.id.btn_user_signup);

        //Initializing value event listener
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //reading data from firebase and adding it to an array list
                for (DataSnapshot dss : snapshot.getChildren()){
                    UserAccount userAccount = dss.getValue(UserAccount.class);
                    userList.add(userAccount);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        //adding a listener to the database reference
        dbref.addListenerForSingleValueEvent(listener);


        //creating methods for clicked "log in" button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempEmail = email.getText().toString();
                String tempPass = password.getText().toString();

                for (int j = 0; j < userList.size(); j++) {
                    idList.add(userList.get(j).getId());
                    nameList.add(userList.get(j).getName());
                    surnameList.add(userList.get(j).getSurname());
                    emailList.add(userList.get(j).getEmail());
                    addressList.add(userList.get(j).getAddress());

                }

                if (email.length() != 0 && password.length() != 0) {
                    {

                        m_auth.signInWithEmailAndPassword(tempEmail, tempPass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                //is successful, store new values in the array lists and attribute them to the matching object

                                int counter1 = emailList.indexOf(tempEmail);
                              //  int counter2 = idList.indexOf(student_id.getText().toString());
                                if (1 == 1) {                                             //counter1 == counter2 && counter1 != -1
                                    //display login successful message
                                    Toast.makeText(UserLogin.this, "Welcome back!", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);

                                  //  i.putExtra("id", idList.get(counter1));
                                  //  i.putExtra(nameList.get(counter1), "name");
                                //    i.putExtra("name",nameList.get(counter1));
                                 //   i.putExtra( "surname", surnameList.get(counter1));
                                //    i.putExtra("email", emailList.get(counter1));
                                 //   i.putExtra("address", addressList.get(counter1));
                                    startActivity(i);

                                } else {

                                    Toast.makeText(UserLogin.this, "Incorrect details, try again!", Toast.LENGTH_LONG).show();

                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UserLogin.this, "Account does not exist, click 'Sign Up' to register!", Toast.LENGTH_LONG).show();

                            }
                        });
                    }
                }else {
                    Toast.makeText(UserLogin.this, "Something went wrong, try again!", Toast.LENGTH_LONG).show();
                }
            }
        });

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        /*   Intent i = new Intent(Login.this, RecoverPassword.class);
           startActivity(i); */
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset your password in just a few steps!");
                passwordResetDialog.setMessage("Enter your email address to receive further instructions: ");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract email and send reset link
                        String mail = resetMail.getText().toString();
                        m_auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UserLogin.this, "Check your email!", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UserLogin.this, "Incorrect email, try again!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });


                passwordResetDialog.create().show();

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserLogin.this, RegisterUser.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    //    m_auth.addAuthStateListener(mAuthListener);
      // FirebaseUser currentUser = m_auth.getCurrentUser();
    }

    @Override
    protected void onStop() {
        super.onStop();
     /*  if (mAuthListener != null){
           m_auth.removeAuthStateListener(mAuthListener);
           m_auth.signOut();
        } */
    }
}
