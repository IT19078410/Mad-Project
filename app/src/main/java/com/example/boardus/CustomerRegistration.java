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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CustomerRegistration extends AppCompatActivity {
    private Button btnSignIn,insertbtn;
    private EditText cname, cphoneno, cpassword, cconfirmpassword;
    private ProgressDialog loadingbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        btnSignIn = (Button) findViewById(R.id.SignIn1);
        cname = (EditText) findViewById(R.id.editTextTextPersonName);
        cphoneno = (EditText) findViewById(R.id.editTextPhoneNumber1);
        cpassword = (EditText) findViewById(R.id.editTextTextPassword2);
        cconfirmpassword = (EditText) findViewById(R.id.editTextTextPassword3);
        loadingbar = new ProgressDialog(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();

            }
        });
        insertbtn = (Button) findViewById(R.id.photo1);
        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CustomerRegistration.this, InsertPhotos.class);
                startActivity(i);

            }
        });
    }

    private void CreateAccount() {
        String name = cname.getText().toString();
        String phoneno = cphoneno.getText().toString();
        String password = cpassword.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Insert name",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phoneno)){
            Toast.makeText(this,"Insert phone number",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Insert password",Toast.LENGTH_SHORT).show();
        }else{
            loadingbar.setTitle("Create Account");
            loadingbar.setMessage("Please wait..");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();

            ValidatePhone(name, phoneno, password);

        }
    }

    private void ValidatePhone(final String name, final String phoneno, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("Customers").child(phoneno).exists())){
                    HashMap<String, Object> customerdatamap = new HashMap<>();
                    customerdatamap.put("phoneno",phoneno);
                    customerdatamap.put("name",name);
                    customerdatamap.put("password",password);

                    RootRef.child("Customers").child(phoneno).updateChildren(customerdatamap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(CustomerRegistration.this,"Acoount created succesfully",Toast.LENGTH_SHORT).show();
                                        loadingbar.dismiss();

                                        Intent i = new Intent(CustomerRegistration.this, HomePage.class);
                                        startActivity(i);
                                    }
                                    else{
                                        loadingbar.dismiss();
                                        Toast.makeText(CustomerRegistration.this,"Please try again later",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(CustomerRegistration.this,"This phone number already exists",Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();

                    Intent i = new Intent(CustomerRegistration.this, MainActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}