package com.example.smartscroehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class staffdashboard extends AppCompatActivity {
    Button button;
    FirebaseAuth auth;
    TextView t1,t2,t3,t4;
    DatabaseReference db;
    String ussd;
    CardView cardView,cardView2,cardView3,cardView6,cardView7;
    ImageView imageView;
    String department1;

    TextView cancel_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffdashboard);
        button = findViewById(R.id.logoutbtn);
        t1 = findViewById(R.id.textView6);
        t2 = findViewById(R.id.textView5);
        t3 = findViewById(R.id.textView4);
        imageView = findViewById(R.id.imageView2);

        cardView = findViewById(R.id.c1);
        cardView2 = findViewById(R.id.c2);
        cardView3 = findViewById(R.id.c3);
        cardView7 = findViewById(R.id.c4);
        cardView6 = findViewById(R.id.c6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(staffdashboard.this,login.class);
                startActivity(intent );
                finish();
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(staffdashboard.this,viewstudentprofile.class);
                startActivity(intent );

            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(staffdashboard.this,editprofilepage.class);
                startActivity(intent );


            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(staffdashboard.this,studentresultgenrate.class);
                startActivity(intent );

            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showaboutus();
            }
        });
        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(staffdashboard.this,setstudentprogress.class);
                startActivity(intent );

            }
        });
        auth = FirebaseAuth.getInstance();

        db= FirebaseDatabase.getInstance().getReference("staff");
        ussd = auth.getUid();
        assert ussd != null;
        db.child(ussd).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("prof_name").getValue(String.class);
                    String mobile = dataSnapshot.child("prof_mobile").getValue(String.class);
                     department1 = dataSnapshot.child("department").getValue(String.class);

                    String link = dataSnapshot.child("uri").getValue(String.class);





                    // Set retrieved data in TextViews
                    t1.setText("Name:- " + name);
                    t2.setText("Mobile:- " + mobile);
                    t3.setText("Dep :-" + department1);

                    Picasso.get().load(link).into(imageView);





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

    private void showaboutus() {
        Dialog dialog = new Dialog(staffdashboard.this);

        dialog.setContentView(R.layout.aboutus69);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        cancel_text = dialog.findViewById(R.id.cancel_text);



        cancel_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(staffdashboard.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}