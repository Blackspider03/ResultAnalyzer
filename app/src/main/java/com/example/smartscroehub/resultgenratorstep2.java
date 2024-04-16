package com.example.smartscroehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class resultgenratorstep2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView textView,textView1,textView2;
    Spinner spinner1,spinner2;
    ValueEventListener databaseReference;
    DatabaseReference db1;
    DatabaseReference edb1;

    String[] year = { "Weekly Test","Unit Test 1","Unit Test 2" };
    String[] subject = { "Java","C","C++","python","Sql",};

    private List<String> itemList;
    EditText editText,editText1;
    Button button;
    String n1,n2,n3,n4,n5;

    String[] subject1 = { "Java","C","C++","python","Sql",};
    String[] computer = { "SUB1","SUB1","SUB1","SUB1","SUB1",};


    String[] sem01 = { "BASIC MATHEMATICS","BASIC SCIENCE","COMMUNICATION SKILL","GRAPHICS","WORKSHOP",};
    String[] sem02 = { "APPLIED MATHMATICS","BASIC ELECTRICAL ENGI","PROGRAMING IN C","LINUX BASICS","WEB DEVLOPMENT",};
    String[] sem03 = { "OBJECT ORIENTED PROGRAMMING","DATA STRUCTURE UISNG C","COMPUTER GRAPHICS", "DATABASE MANAGEMENT SYSTEM","DIGITAL TECHNEQUES",};
    String[] sem04 = { "JAVA PROGRAMMING","SOFTWARE ENGINEERING","DATA COMMUNICATION","MICROPROCESSOR"};
    String[] sem05 = { "ENVIROMENTAL STUDIES","OPERATING SYSTEM","ADVANCED JAVA","SOFTWARE TESTING", "ADVANCE NETWORKING",};
    String[] sem06 = { "MOBILE APPLICATION DEVELOPMENT","PYTHON","NETWORK SECURITY","MANAGEMENT","EMERGING TRENDS",};
    String[] sem07 = { "MOBILE APPLICATION DEVELOPMENT","PYTHON","NETWORK SECURITY","MANAGEMENT","EMERGING TRENDS",};

    String[] sem08 = { "MOBILE APPLICATION DEVELOPMENT","PYTHON","NETWORK SECURITY","MANAGEMENT","EMERGING TRENDS",};




    RecyclerView mRecyclerView;
    showbelowresultAdpter mAdapter;
    List<student1> mUploads;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultgenratorstep2);

        textView = findViewById(R.id.b1);
        textView1 = findViewById(R.id.b2);
        textView2 = findViewById(R.id.b3);

        editText = findViewById(R.id.obtaninedmarks);
        editText1 = findViewById(R.id.maximummarks);
        button = findViewById(R.id.markssavebtn);

        mRecyclerView = findViewById(R.id.cycle123);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        spinner1 = findViewById(R.id.spinner6);
        spinner2 = findViewById(R.id.spinner7);

        Intent intent = getIntent();
         n1 = intent.getStringExtra("m1");
         n2 = intent.getStringExtra("m2");
         n3 = intent.getStringExtra("m3");
         n4 = intent.getStringExtra("m4");///////branch


        textView.setText("Name:- "+n1);
        textView1.setText("Prn Number:- "+n2);
        textView2.setText("Semister:- "+n3);

        spinner1.setOnItemSelectedListener(resultgenratorstep2.this);

        ArrayAdapter<String> ad1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                year);






        ad1.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(ad1);






        if (n4.equals("COMPUTER")&&n3.equals("SEM7")){
            ArrayAdapter<String> ad2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                    subject);
            ad2.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(ad2);
        }

        else if  (n4.equals("COMPUTER")&&n3.equals("SEM1")) {
            ArrayAdapter<String> ad2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                    sem01);
            ad2.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(ad2);
        }

        else if  (n4.equals("COMPUTER")&&n3.equals("SEM2")) {
            ArrayAdapter<String> ad2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                    sem02);
            ad2.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(ad2);
        }
        else if  (n4.equals("COMPUTER")&&n3.equals("SEM3")) {
            ArrayAdapter<String> ad2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                    sem03);
            ad2.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(ad2);
        }
        else if  (n4.equals("COMPUTER")&&n3.equals("SEM4")) {
            ArrayAdapter<String> ad2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                    sem04);
            ad2.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(ad2);
        }
        else if  (n4.equals("COMPUTER")&&n3.equals("SEM5")) {
            ArrayAdapter<String> ad2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                    sem05);
            ad2.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(ad2);
        }
        else if  (n4.equals("COMPUTER")&&n3.equals("SEM6")) {
            ArrayAdapter<String> ad2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                    sem06);
            ad2.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(ad2);

        }
        else if  (n4.equals("COMPUTER")&&n3.equals("SEM7")) {
            ArrayAdapter<String> ad2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                    sem07);
            ad2.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(ad2);
        }
        else if  (n4.equals("COMPUTER")&&n3.equals("SEM8")) {
            ArrayAdapter<String> ad2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                    sem08);
            ad2.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(ad2);
        }





































        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String obt = editText.getText().toString();
                String max = editText1.getText().toString();

                if (obt.isEmpty()||max.isEmpty()){
                    Toast.makeText(resultgenratorstep2.this, "Fill Marks", Toast.LENGTH_SHORT).show();
                }else {
                    savemarks(obt,max);
                }
            }
        });




















    }

    private void savemarks(String obt, String max) {
        db1 = FirebaseDatabase.getInstance().getReference("result");


        String departmetn = n4.toString();
        String prn = n2.toString();
        String sem = n3.toString();
        String test = spinner1.getSelectedItem().toString();
        String subject = spinner2.getSelectedItem().toString();

        Map<String , String > map = new HashMap<>();
        map.put("obtained_marks",editText.getText().toString());
        map.put("maximum_marks",editText1.getText().toString());
        map.put("subject",subject.toString());




        db1.child(departmetn).child(prn).child(test).child(subject).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(resultgenratorstep2.this, "Saved", Toast.LENGTH_SHORT).show();
                    showresult12(departmetn,sem,prn,test);
                    ///////////////////////
                    edb1 = FirebaseDatabase.getInstance().getReference("earlyaccess");
                    String test = spinner1.getSelectedItem().toString();
                    String prn = n2.toString();

                    Map<String , String > map1 = new HashMap<>();
                    map1.put("test",test.toString());


                    edb1.child(prn).setValue(map1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(resultgenratorstep2.this, "Saved", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(resultgenratorstep2.this, "Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });





                }else{
                    Toast.makeText(resultgenratorstep2.this, "error", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private void showresult12(String departmetn, String sem, String prn, String test) {
        mUploads = new ArrayList<>();
        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("result")
                .child(departmetn).child(prn).child(test);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    student1 student1 = postSnapshot.getValue(student1.class);
                    mUploads.add(student1);
                }
                mAdapter = new showbelowresultAdpter(resultgenratorstep2.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);

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