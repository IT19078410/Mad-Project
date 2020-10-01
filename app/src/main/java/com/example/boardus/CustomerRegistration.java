package com.example.boardus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerRegistration extends AppCompatActivity {
    private Button btnSignIn,insertbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        btnSignIn = (Button) findViewById(R.id.SignIn1);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CustomerRegistration.this, HomePage.class);
                startActivity(i);

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
}