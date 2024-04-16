package com.example.smartscroehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class editstudentprofle extends AppCompatActivity {
    public EditText editText1,editText2,editText3,editText4,editText5,editText6;
    FirebaseAuth auth;
    DatabaseReference db;
    DatabaseReference db1;
    String ussd;
    ImageView imageView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editstudentprofle);

        editText1 = findViewById(R.id.n1);
        editText2 = findViewById(R.id.n2);
        editText3 = findViewById(R.id.n3);
        editText4 = findViewById(R.id.n4);
        editText5 = findViewById(R.id.n5);
        editText6 = findViewById(R.id.n6);
        button = findViewById(R.id.button);

        editText5.setFocusable(false);
        editText2.setFocusable(false);
        editText6.setFocusable(false);
        auth = FirebaseAuth.getInstance();

        db= FirebaseDatabase.getInstance().getReference("student");
        db1= FirebaseDatabase.getInstance().getReference("student");

        ussd = auth.getUid();
        assert ussd != null;





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateprofile();
            }
        });















        db.child(ussd).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String a = dataSnapshot.child("student_name").getValue(String.class);
                    String b = dataSnapshot.child("student_branch").getValue(String.class);
                    String c = dataSnapshot.child("student_prn").getValue(String.class);
                    String d = dataSnapshot.child("student_mobile").getValue(String.class);
                    String e = dataSnapshot.child("student_email").getValue(String.class);
                    String f = dataSnapshot.child("student_year").getValue(String.class);

                    // Set retrieved data in TextViews
                    editText1.setText( a);
                    editText2.setText(b);
                    editText3.setText(c);
                    editText4.setText(d);
                    editText5.setText(e);
                    editText6.setText(f);
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

    private void updateprofile() {

        Map<String, String> map = new HashMap<>();
        map.put("student_name",editText1.getText().toString());
        map.put("student_branch",editText2.getText().toString());
        map.put("student_prn",editText3.getText().toString());
        map.put("student_mobile",editText4.getText().toString());
        map.put("student_email",editText5.getText().toString());
        map.put("student_year",editText6.getText().toString());
        db1.child(ussd).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(editstudentprofle.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }else
                {
                    Toast.makeText(editstudentprofle.this, "Faild to Update", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}