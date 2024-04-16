package com.example.smartscroehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class studentresultgenrate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] year = { "SEM1","SEM2","SEM3","SEM4","SEM5","SEM6","SEM7","SEM8" };
    Spinner sp1,sp2;




    RecyclerView mRecyclerView;
    ResultGenrateAdpter mAdapter;
    List<basicstud> mUploads;
    DatabaseReference db;
    String ussd;
    FirebaseAuth auth;
    TextView textView;
    String department;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentresultgenrate);

        sp1 = findViewById(R.id.spinner5);
        textView = findViewById(R.id.textView26);
        mRecyclerView = findViewById(R.id.recycle2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        sp1.setOnItemSelectedListener(studentresultgenrate.this);
        auth = FirebaseAuth.getInstance();


        ArrayAdapter ad1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                year);



        ad1.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(ad1);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchstudent(department);
            }
        });


        db= FirebaseDatabase.getInstance().getReference("staff");
        ussd = auth.getUid();
        assert ussd != null;
        db.child(ussd).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    department = dataSnapshot.child("department").getValue(String.class);
                    fetchstudent(department);

                } else {
                    Log.d("Realtime Database", "No such document");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Realtime Database", "onCancelled: " + databaseError.getMessage());
            }

        });

        //////////////////////////////////////





    }

    private void fetchstudent(String department) {
        String semister = sp1.getSelectedItem().toString();
        mUploads = new ArrayList<>();
        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("studentdata")
                .child(department).child(semister);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    basicstud basicstud = postSnapshot.getValue(basicstud.class);
                    mUploads.add(basicstud);
                }
                mAdapter = new ResultGenrateAdpter(studentresultgenrate.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);
                // mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });









    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}

