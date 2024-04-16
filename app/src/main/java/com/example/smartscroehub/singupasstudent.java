package com.example.smartscroehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class singupasstudent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText editText,editText1,editText2,editText3,editText4,editText5;
    Button button,button1;
    Spinner sp1,sp2;

    FirebaseAuth auth;
    DatabaseReference databaseReference;
    DatabaseReference db1;



    String[] branch = { "SELECT","COMPUTER", "MACHNICAL",
            "ELECTRICAL", "E&TC",
            "CIVIL" };

    String[] year = { "SEM1","SEM2","SEM3","SEM4","SEM5","SEM6","SEM7","SEM8" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singupasstudent);
        editText = findViewById(R.id.sname);
        editText1 = findViewById(R.id.smobile);
        editText2 = findViewById(R.id.sprn);
        editText3 = findViewById(R.id.semail);
        editText4 = findViewById(R.id.spass);
        button = findViewById(R.id.singupbtn);

        sp1 = findViewById(R.id.spinner);
        sp2 = findViewById(R.id.spinner2);


        sp1.setOnItemSelectedListener(singupasstudent.this);
        sp2.setOnItemSelectedListener(singupasstudent.this);


        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                branch);
        ArrayAdapter ad1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                year);

        ad.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(ad);

        ad1.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(ad1);



        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("student");
        db1 = FirebaseDatabase.getInstance().getReference("studentdata");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                String mobile = editText1.getText().toString();
                String prn = editText2.getText().toString();
                String email = editText3.getText().toString();
                String pass = editText4.getText().toString();


                if (name.isEmpty()||mobile.isEmpty()||prn.isEmpty()||email.isEmpty()||pass.isEmpty()){
                    Toast.makeText(singupasstudent.this, "Fill all the Details", Toast.LENGTH_SHORT).show();
                }else{
                    regis(email,pass,name,mobile,prn);
                }

            }
        });





    }

    private void regis(String email, String pass, String name, String mobile, String prn) {


        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    String udi =  auth.getUid();
                    studdatabase(udi);
                }
            }
        });
    }

    private void studdatabase(String udi) {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("'Date\n'dd-MM-yyyy '\n\nand\n\nTime\n'HH:mm:ss z");
        String currentDateAndTime = sdf.format(new Date());
        String datetime = currentDateAndTime.toString();


        Map<String ,String > map = new HashMap<>();
        map.put("student_name",editText.getText().toString());
        map.put("student_mobile",editText1.getText().toString());
        map.put("student_prn",editText2.getText().toString());
        map.put("student_email",editText3.getText().toString());
        map.put("student_uid",udi.toString());
        map.put("student_branch",sp1.getSelectedItem().toString());
        map.put("student_year",sp2.getSelectedItem().toString());
        map.put("date",datetime.toString());

        databaseReference.child(udi).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                        finaldatabase(udi,datetime);
                }else {
                    Toast.makeText(singupasstudent.this, "Error", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }

    private void finaldatabase(String udi,String datetime) {
        Map<String ,String > map = new HashMap<>();
        map.put("student_name",editText.getText().toString());
        map.put("student_mobile",editText1.getText().toString());
        map.put("student_prn",editText2.getText().toString());
        map.put("student_email",editText3.getText().toString());
        map.put("student_uid",udi.toString());
        map.put("student_branch",sp1.getSelectedItem().toString());
        map.put("student_year",sp2.getSelectedItem().toString());
        map.put("date",datetime.toString());

        db1.child(sp1.getSelectedItem().toString()).child(sp2.getSelectedItem().toString()).child(editText2.getText().toString()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(singupasstudent.this, "Account Created", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(singupasstudent.this, "Error", Toast.LENGTH_SHORT).show();
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