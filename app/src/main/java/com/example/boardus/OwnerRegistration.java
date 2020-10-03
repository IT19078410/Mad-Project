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

public class OwnerRegistration extends AppCompatActivity {

    private Button submit1;
    private EditText oname,ophoneno,map,nofbeds,price,description,opassword,ocpassword;
    private ProgressDialog loadingbar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_registration);
        submit1 = (Button) findViewById(R.id.Register1);
        oname=(EditText) findViewById(R.id.editTextTextPersonName);
        ophoneno=(EditText) findViewById(R.id.phoneno);
        map=(EditText) findViewById(R.id.map1);
        nofbeds=(EditText) findViewById(R.id.editTextTextPersonName7);
        price=(EditText) findViewById(R.id.editTextTextPersonName3);
        description=(EditText) findViewById(R.id.editTextTextPersonName6);
        opassword=(EditText) findViewById(R.id.editTextTextPassword);
        ocpassword=(EditText) findViewById(R.id.editTextTextPassword2);
        loadingbar2 = new ProgressDialog(this);

        submit1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CreateOwnerAccount();

            }
        });
    }

    private void CreateOwnerAccount() {

        {
            String ownername = oname.getText().toString();
            String ownerphoneno = ophoneno.getText().toString();
            String ownermap = map.getText().toString();
            String ownernofbeds = nofbeds.getText().toString();
            String ownerprice = price.getText().toString();
            String ownerdescription = description.getText().toString();
            String ownerpassword = opassword.getText().toString();

            if (TextUtils.isEmpty(ownername)) {
                Toast.makeText(this, "Insert name", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(ownerphoneno)) {
                Toast.makeText(this, "Insert phone number", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(ownermap)) {
                Toast.makeText(this, "Insert map link", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(ownernofbeds)) {
                Toast.makeText(this, "Insert number of beds", Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(ownerprice)) {
                Toast.makeText(this, "Insert price", Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(ownerdescription)) {
                Toast.makeText(this, "Insert description", Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(ownerpassword)) {
                Toast.makeText(this, "Insert password", Toast.LENGTH_SHORT).show();
            }

            else {
                loadingbar2.setTitle("Create Owner Account");
                loadingbar2.setMessage("Please wait..");
                loadingbar2.setCanceledOnTouchOutside(false);
                loadingbar2.show();

              //  ValidateOwnerPhone(ownername, ownerphoneno, ownermap, ownernofbeds, ownerprice, ownerdescription, ownerpassword);

            }
        }


    }

    private void ValidateOwnerPhone(final String ownername, final String ownerphoneno, final String ownermap, final String ownernofbeds, final String ownerprice, final String ownerdescription, final String ownerpassword) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("Owners").child(ownerphoneno).exists())){
                    HashMap<String, Object> ownerdatamap = new HashMap<>();
                    ownerdatamap.put("name",ownername);
                    ownerdatamap.put("phoneNo",ownerphoneno);
                    ownerdatamap.put("maplink",ownermap);
                    ownerdatamap.put("nofbeds",ownernofbeds);
                    ownerdatamap.put("price",ownerprice);
                    ownerdatamap.put("description",ownerdescription);
                    ownerdatamap.put("password",ownerpassword);

                    RootRef.child("Owners").child(ownerphoneno).updateChildren(ownerdatamap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(OwnerRegistration.this,"Acoount created succesfully",Toast.LENGTH_SHORT).show();
                                        loadingbar2.dismiss();

                                        Intent i = new Intent(OwnerRegistration.this, HomePage.class);
                                        startActivity(i);
                                    }
                                    else{
                                        loadingbar2.dismiss();
                                        Toast.makeText(OwnerRegistration.this,"Please try again later",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(OwnerRegistration.this,"This phone number already exists",Toast.LENGTH_SHORT).show();
                    loadingbar2.dismiss();

                    Intent i = new Intent(OwnerRegistration.this, OwnerPage.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}