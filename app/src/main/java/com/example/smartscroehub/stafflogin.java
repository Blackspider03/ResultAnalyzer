package com.example.smartscroehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class stafflogin extends AppCompatActivity {
    Button btn;
    EditText editText,editText1,editText2;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stafflogin);
        btn = findViewById(R.id.stafflogbtn);
        editText = findViewById(R.id.stafflogemail);
        editText1 = findViewById(R.id.stafflogpass);
        editText2= findViewById(R.id.stafflogsec);
        auth = FirebaseAuth.getInstance();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String email = editText.getText().toString();
                String pass = editText1.getText().toString();
                String sec = editText2.getText().toString();
                if (email.isEmpty()||pass.isEmpty()||sec.isEmpty()){
                    Toast.makeText(stafflogin.this, "Please Fill all Inpute", Toast.LENGTH_SHORT).show();
                }
                else {
                    loginn(email,pass,sec);
                }




            }
        });
    }

    private void loginn(String email, String pass, String sec) {
        if (sec.equals("7620")){
            auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(stafflogin.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(stafflogin.this,staffdashboard.class);
                        startActivity(intent );
                    }else {
                        Toast.makeText(stafflogin.this, "email or Passowrd is Incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(this, "Invalid Security Code", Toast.LENGTH_SHORT).show();
        }


    }

    }
