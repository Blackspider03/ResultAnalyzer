package com.example.smartscroehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class resultpage extends AppCompatActivity {
    TextView textView,textView1,textView2,textView3,textView4,textView5,textView6,textView7;
    DatabaseReference db;
    DatabaseReference db21;
    private List<Integer> numbers = new ArrayList<>();


    RecyclerView mRecyclerView;
    showbelowresultAdpter mAdapter;
    List<student1> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultpage);
        textView = findViewById(R.id.textView48);//////////schoolnamne
        textView2 = findViewById(R.id.textView02);/////////exam name
        textView3 = findViewById(R.id.textView04);////////student name
        textView4 = findViewById(R.id.textView05);//////////prn
        textView5 = findViewById(R.id.textView54);////////total score
        textView6 = findViewById(R.id.textView55);////////final result
        Intent intent = getIntent();
        String n1 = intent.getStringExtra("m1");//////uid
        String n2 = intent.getStringExtra("m2");////////prn
        String n3 = intent.getStringExtra("m3");////////test

        textView2.setText(n3);
        textView4.setText(n2);

        mRecyclerView = findViewById(R.id.scycle123);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        db= FirebaseDatabase.getInstance().getReference("student");
        db.child(n1).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String em = dataSnapshot.child("student_name").getValue(String.class);
                    String dp = dataSnapshot.child("student_branch").getValue(String.class);
                    String pr = dataSnapshot.child("student_prn").getValue(String.class);

                    textView3.setText(em);

                    if (em!=null && dp!=null){
                        fetchresult(em,dp,pr);
                    }


                } else {
                    Log.d("Realtime Database", "No such document");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Realtime Database", "onCancelled: " + databaseError.getMessage());
            }
        });

        //////////////////////

        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureScreen();
            }
        });















    }

    private void fetchresult(String em, String dp, String pr) {
        mUploads = new ArrayList<>();
        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("result")
                .child(dp).child(pr).child(textView2.getText().toString());
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    student1 student1 = postSnapshot.getValue(student1.class);
                    mUploads.add(student1);

                    if (mUploads.isEmpty()){
                        Toast.makeText(resultpage.this, "No data found", Toast.LENGTH_SHORT).show();
                    }else{

                    }

                }
                mAdapter = new showbelowresultAdpter(resultpage.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });





    }
    public void captureScreen() {
        View rootView = getWindow().getDecorView().getRootView();
        rootView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);

        // Save screenshot to external storage
        saveScreenshot(bitmap);
    }

    // Method to save screenshot to external storage
    private void saveScreenshot(Bitmap bitmap) {
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File file = new File(directory, "result.png");

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            Toast.makeText(this, "Result saved to " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save Result", Toast.LENGTH_SHORT).show();
        }
    }
}