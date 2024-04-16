package com.example.smartscroehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class studentdashboard extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView textView1,textView2,textView3;
    ImageView imageView;
    FirebaseAuth auth;
    DatabaseReference db;
    DatabaseReference db69;
    String ussd;
    String[] type = { "Weekly Test","Unit Test 1","Unit Test 2" };
    Spinner spinner69;
    Button vbutton;
    String staffname;
    //////////////////////////////////////
    RecyclerView mRecyclerView;
    progressAdpter mAdapter;
    List<progress> mUploads;
    ///////////////////////////////////
    RecyclerView mRecyclerView2;
    showbelowresultAdpter mAdapter1;
    List<student1> mUploads1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentdashboard);

        textView1 = findViewById(R.id.stext);
        textView2 = findViewById(R.id.stext2);
        textView3 = findViewById(R.id.stext3);
        spinner69 = findViewById(R.id.spinner8);
        vbutton = findViewById(R.id.button3);

        imageView = findViewById(R.id.imageView5);

        mRecyclerView = findViewById(R.id.rs);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        spinner69.setOnItemSelectedListener(studentdashboard.this);

        ArrayAdapter<String> ad1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                type);
        ad1.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner69.setAdapter(ad1);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(studentdashboard.this,setting.class);
                startActivity(intent);
            }
        });

        auth = FirebaseAuth.getInstance();

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
                      staffname = dataSnapshot.child("student_prn").getValue(String.class);

                    // Set retrieved data in TextViews
                    textView1.setText("Name:- " + em);
                    textView2.setText("Department:-  " + stmob);
                    textView3.setText("Prn Num:- " + staffname);

                    showprogress(staffname);



                } else {
                    Log.d("Realtime Database", "No such document");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Realtime Database", "onCancelled: " + databaseError.getMessage());
            }

        });




        vbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = spinner69.getSelectedItem().toString();
                String prn1 = staffname.toString();

                checkreuslt(prn1,type,ussd);


            }
        });































    }

    private void checkreuslt(String prn1, String type, String ussd) {
        db69= FirebaseDatabase.getInstance().getReference("earlyaccess");

        db69.child(prn1).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String em = dataSnapshot.child("test").getValue(String.class);

                    if (type.equals(em)){
                        Intent intent = new Intent(studentdashboard.this,
                                resultpage.class);
                        intent.putExtra("m1",ussd);
                        intent.putExtra("m2",prn1);
                        intent.putExtra("m3",em);


                        startActivity(intent);
                    }else {
                        Toast.makeText(studentdashboard.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                    // Set retrieved data in TextViews

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


    private void showprogress(String staffname) {
        mUploads = new ArrayList<>();
        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().
                getReference("progress").child(staffname);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    progress progress = postSnapshot.getValue(progress.class);
                    mUploads.add(progress);

                }
                mAdapter = new progressAdpter(studentdashboard.this, mUploads);
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
}