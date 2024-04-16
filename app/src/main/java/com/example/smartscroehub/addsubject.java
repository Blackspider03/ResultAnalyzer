package com.example.smartscroehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class addsubject extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] year = {"SEM1", "SEM2", "SEM3", "SEM4", "SEM5", "SEM6", "SEM7", "SEM8"};

    Spinner sp1, sp2;
    DatabaseReference databaseReference;
    DatabaseReference db;
    FirebaseAuth auth;
    String ussd;
    String department;
    EditText editText1, editText2;
    Button button;
    String n1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsubject);
        editText1 = findViewById(R.id.defaultdepartment);
        editText2 = findViewById(R.id.subjectName);
        button = findViewById(R.id.savebtn);
        sp1 = findViewById(R.id.spinner4);

        Intent intent = getIntent();
        n1 = intent.getStringExtra("m69");


        sp1.setOnItemSelectedListener(addsubject.this);
        editText1.setText(n1);
        editText1.setFocusable(false);


        ArrayAdapter ad1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                year);


        ad1.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(ad1);


        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference("subject");
        ussd = auth.getUid();
        assert ussd != null;


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = editText2.getText().toString();
                if (subject.isEmpty()) {
                    Toast.makeText(addsubject.this, "Please Enter The Subject", Toast.LENGTH_SHORT).show();
                } else {
                    addsub(subject, department);
                }
            }
        });


    }

    private void addsub(String subject, String department) {

        databaseReference = FirebaseDatabase.getInstance().getReference("SUBJECT");
        String sem = sp1.getSelectedItem().toString();
        Map<String, String> map = new HashMap<>();
        map.put("subject", editText2.getText().toString());

        databaseReference.child(editText1.getText().toString()).child(sem).push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(addsubject.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(addsubject.this, "Error", Toast.LENGTH_SHORT).show();
                }
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