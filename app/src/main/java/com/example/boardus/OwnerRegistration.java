package com.example.boardus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OwnerRegistration extends AppCompatActivity {


    private Button submit1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_registration);
        submit1 = (Button) findViewById(R.id.Signup1);
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( OwnerRegistration. this, Search.class);
                startActivity(i);

            }
        });


    }
}