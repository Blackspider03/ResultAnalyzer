package com.example.smartscroehub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class detailedstudentprofile extends AppCompatActivity {
    TextView textView,textView1,textView2,textView3,textView4,textView5,textView7,textView8;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedstudentprofile);

        textView = findViewById(R.id.tt1);
        textView1 = findViewById(R.id.tt2);
        textView2 = findViewById(R.id.tt3);
        textView3 = findViewById(R.id.tt4);
        textView4 = findViewById(R.id.tt5);
        textView5 = findViewById(R.id.tt6);
        textView7 = findViewById(R.id.textView20);
        textView8 = findViewById(R.id.textView22);


        Intent intent = getIntent();
        String n1 = intent.getStringExtra("m1");
        String n2 = intent.getStringExtra("m2");
        String n3 = intent.getStringExtra("m3");
        String n4 = intent.getStringExtra("m4");
        String n5 = intent.getStringExtra("m5");
        String n6 = intent.getStringExtra("m6");


        textView.setText(n1);
        textView1.setText(n2);
        textView2.setText(n3);
        textView3.setText(n4);
        textView4.setText(n5);
        textView5.setText(n6);
        textView7.setText(n1);
        textView8.setText(n3);










    }
}