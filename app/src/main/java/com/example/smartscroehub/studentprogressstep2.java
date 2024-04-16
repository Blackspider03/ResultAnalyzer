package com.example.smartscroehub;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class studentprogressstep2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView textView,textView1,textView2,textView3;
    Spinner spinner2;
    Task<Void> databaseReference;
    DatabaseReference db1;
    DatabaseReference db69;

    String[] subject = { "Java","C","C++","python","Sql",};
    String[] computer = { "SUB1","SUB1","SUB1","SUB1","SUB1",};


    String[] sem01 = { "BASIC MATHEMATICS","BASIC SCIENCE","COMMUNICATION SKILL","GRAPHICS","WORKSHOP",};
    String[] sem02 = { "APPLIED MATHMATICS","BASIC ELECTRICAL ENGI","PROGRAMING IN C","LINUX BASICS","WEB DEVLOPMENT",};
    String[] sem03 = { "OBJECT ORIENTED PROGRAMMING","DATA STRUCTURE UISNG C","COMPUTER GRAPHICS", "DATABASE MANAGEMENT SYSTEM","DIGITAL TECHNEQUES",};
    String[] sem04 = { "JAVA PROGRAMMING","SOFTWARE ENGINEERING","DATA COMMUNICATION","MICROPROCESSOR"};
    String[] sem05 = { "ENVIROMENTAL STUDIES","OPERATING SYSTEM","ADVANCED JAVA","SOFTWARE TESTING", "ADVANCE NETWORKING",};
    String[] sem06 = { "MOBILE APPLICATION DEVELOPMENT","PYTHON","NETWORK SECURITY","MANAGEMENT","EMERGING TRENDS",};
    String[] sem07 = { "MOBILE APPLICATION DEVELOPMENT","PYTHON","NETWORK SECURITY","MANAGEMENT","EMERGING TRENDS",};

    String[] sem08 = { "MOBILE APPLICATION DEVELOPMENT","PYTHON","NETWORK SECURITY","MANAGEMENT","EMERGING TRENDS",};


    /////////////










    private List<String> itemList;
    EditText editText,editText1;
    Button button;
    String n1,n2,n3,n4,n5;

    ProgressBar progressBar;

    Button b1,b2;
    EditText editText22;



    RecyclerView mRecyclerView;
    showbelowresultAdpter mAdapter;
    List<student1> mUploads;
     int prog = 0;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentprogressstep2);

        textView = findViewById(R.id.b1);
        textView1 = findViewById(R.id.b2);
        textView2 = findViewById(R.id.b3);
        textView3 = findViewById(R.id.protest);
        progressBar = findViewById(R.id.progressBar);

        editText22 = findViewById(R.id.progressgeter);



        button = findViewById(R.id.markssavebtn);
        spinner2 = findViewById(R.id.spinner7);

        Intent intent = getIntent();
        n1 = intent.getStringExtra("m1");
        n2 = intent.getStringExtra("m2");
        n3 = intent.getStringExtra("m3");
        n4 = intent.getStringExtra("m4");///////branch


        db69 = FirebaseDatabase.getInstance().getReference("SUBJECT");




        textView.setText("Name:- "+n1);
        textView1.setText("Prn Number:- "+n2);
        textView2.setText("Semister:- "+n3);

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
        //////////////////////////////////////////////////////////




































        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String progressdata = editText22.getText().toString();

                if (progressdata.isEmpty()){
                    Toast.makeText(studentprogressstep2.this, "Fill Progress", Toast.LENGTH_SHORT).show();
                }else {
                    updateprogress(progressdata);
                }
            }
        });




    }




    private void updateprogress(String progressdata){
            String prn = n2.toString();
            String sub = spinner2.getSelectedItem().toString();

        progressBar.setProgress(Integer.parseInt(progressdata));
        textView3.setText(progressdata);

        Map<String ,String > map = new HashMap<>();
        map.put("subject",spinner2.getSelectedItem().toString());
        map.put("progress",progressdata.toString());


        databaseReference = FirebaseDatabase.getInstance().getReference("progress")
                .child(prn).child(sub).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(studentprogressstep2.this, "Saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

    private void savemarks(String obt, String max) {
        db1 = FirebaseDatabase.getInstance().getReference("result");


        String departmetn = n4.toString();
        String prn = n2.toString();
        String sem = n3.toString();
        String subject = spinner2.getSelectedItem().toString();



    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}