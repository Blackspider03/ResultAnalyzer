package com.example.smartscroehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class setting extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    FirebaseAuth auth;
    String ussd;
    DatabaseReference db;
    CardView cd1,cd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        textView = findViewById(R.id.textView45);
        imageView = findViewById(R.id.imageView12);
        auth = FirebaseAuth.getInstance();
        cd1 = findViewById(R.id.c1);

        cd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(setting.this,editstudentprofle.class);
                startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(setting.this,login.class);
                startActivity(intent);
                finish();
            }
        });

        db= FirebaseDatabase.getInstance().getReference("student");
        ussd = auth.getUid();
        assert ussd != null;
        db.child(ussd).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String em = dataSnapshot.child("student_name").getValue(String.class);
                    String stmob = dataSnapshot.child("student_branch").getValue(String.class);
                    String staffname = dataSnapshot.child("student_prn").getValue(String.class);

                    // Set retrieved data in TextViews
                    textView.setText("Name:- " + em);
                } else {
                    Log.d("Realtime Database", "No such document");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Realtime Database", "onCancelled: " + databaseError.getMessage());
            }

        });


    }
}