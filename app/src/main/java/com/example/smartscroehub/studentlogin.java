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

public class studentlogin extends AppCompatActivity {
    EditText editText,editText1,editText2;
    FirebaseAuth auth;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);
        editText = findViewById(R.id.logemail12);
        editText1 = findViewById(R.id.logpass11);
        button = findViewById(R.id.logbtn);

        auth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText.getText().toString();
                String pass = editText1.getText().toString();
                if (email.isEmpty()||pass.isEmpty()||pass.isEmpty()){
                    Toast.makeText(studentlogin.this, "Please Fill all Inpute", Toast.LENGTH_SHORT).show();
                }
                else {
                    loginn(email,pass);
                }
            }
        });

    }

    private void loginn(String email, String pass) {
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(studentlogin.this, "Login Succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(studentlogin.this,
                            studentdashboard.class);
                    startActivity(intent );
                }else {
                    Toast.makeText(studentlogin.this, "Faild to Login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}