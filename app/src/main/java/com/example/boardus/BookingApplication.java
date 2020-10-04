package com.example.boardus;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class BookingApplication extends AppCompatActivity {
    private RadioGroup rg;
    private RadioButton male, female;
    private EditText FName, LName, PhoneNum, email, BOD, NumOfRooms;
    private Button Submit;
    private ProgressDialog loadingbar3;
    Bookings bookings;
    FirebaseDatabase database;
    DatabaseReference reference;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_application);
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        male = (RadioButton) findViewById(R.id.radioButton5);
        female = (RadioButton) findViewById(R.id.radioButton6);
        FName = (EditText) findViewById(R.id.editTextTextPersonName);
        LName = (EditText) findViewById(R.id.editTextTextPersonName2);
        PhoneNum = (EditText) findViewById(R.id.editTextPhone);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        BOD = (EditText) findViewById(R.id.editTextDate);
        NumOfRooms = (EditText) findViewById(R.id.editTextTextPersonName5);
        Submit = (Button) findViewById(R.id.submit3);
        loadingbar3 = new ProgressDialog(this);
        bookings = new Bookings();
        reference = database.getInstance().getReference().child("Booking");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    i = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CreateBooking();

            }
        });


    }

    private void CreateBooking() {
        String FirstName = FName.getText().toString();
        String LastName = LName.getText().toString();
        String PhoneNumber = PhoneNum.getText().toString();
        String Email = email.getText().toString();
        String Birthday = BOD.getText().toString();
        String NumberOfRooms = NumOfRooms.getText().toString();
        String Male = male.getText().toString();
        String Female = female.getText().toString();


        if (TextUtils.isEmpty(FirstName)) {
            Toast.makeText(this, "Insert first name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(LastName)) {
            Toast.makeText(this, "Insert last name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(PhoneNumber)) {
            Toast.makeText(this, "Insert map link", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Email)) {
            Toast.makeText(this, "Insert number of beds", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Birthday)) {
            Toast.makeText(this, "Insert Birthday", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(NumberOfRooms)) {
            Toast.makeText(this, "Insert description", Toast.LENGTH_SHORT).show();
        }

        else {
            loadingbar3.setTitle("Book Now");
            loadingbar3.setMessage("Please wait..");
            loadingbar3.setCanceledOnTouchOutside(false);
            loadingbar3.show();
        }

        if (male.isChecked()) {
            bookings.setGender(Male);
            reference.child(String.valueOf(i + 1)).setValue(bookings);
        } else if (female.isChecked()) {
            bookings.setGender(Female);
            reference.child(String.valueOf(i + 1)).setValue(bookings);
        } else {
            Toast.makeText(this, "Please select your Gender", Toast.LENGTH_SHORT).show();
        }
        bookings.setFname(FirstName);
        bookings.setLname(LastName);
        bookings.setPhoneno(PhoneNumber);
        bookings.setEmail(Email);
        bookings.setBirthday(Birthday);
        bookings.setNofrooms(NumberOfRooms);

    }

}


