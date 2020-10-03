package com.example.boardus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.boardus.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private Button loginbtn,registerbtn;
    private EditText phoneno,password;
    private ProgressDialog loadingbar;
    private String ParentDbname ="Customers";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerbtn = (Button) findViewById(R.id.Submit1);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( Login. this, CustomerRegistration.class);
                startActivity(i);

            }
        });
        loginbtn = (Button) findViewById(R.id.Login3);
        phoneno = (EditText) findViewById(R.id.editTextPhoneno3);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        loadingbar = new ProgressDialog(this);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String cphone = phoneno.getText().toString();
        String cpassword = password.getText().toString();

         if(TextUtils.isEmpty(cphone)){
            Toast.makeText(this,"Insert phone number",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cpassword)){
            Toast.makeText(this,"Insert password",Toast.LENGTH_SHORT).show();
        }
        else{
             loadingbar.setTitle("Login Account");
             loadingbar.setMessage("Please wait..");
             loadingbar.setCanceledOnTouchOutside(false);
             loadingbar.show();

             AllowAccessToAccount(cphone,cpassword);
         }

    }

    private void AllowAccessToAccount(final String phone, final String cpassword) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("ParentDbname").child(phone).exists())
                {
                    Users userdata = snapshot.child(ParentDbname).child(phone).getValue(Users.class);
                    if (userdata.getPhone().equals(phone))
                    {
                        if (userdata.getPassword().equals(cpassword)){
                            Toast.makeText(Login.this,"Logged in successfuly...",Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                            Intent i = new Intent(Login.this, HomePage.class);
                            startActivity(i);
                        }
                    }
                }
                else
                {
                    Toast.makeText(Login.this,"Account with this "+phone+"does not exists",Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                    Toast.makeText(Login.this,"You need to create a new account",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}