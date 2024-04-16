package com.example.smartscroehub;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class editprofilepage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button button;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference db;
    FirebaseDatabase database;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    private static final int PROFILE_REQUEST_CODE = 1;
    String ussd="";
    Uri uri;
    String link;
    EditText t1,t2,t3,t4,t5,t6,t7;
    TextView textView;
    ImageView c1,c2,c3,c4,c5,c6;
    Spinner sp1;
    Spinner sp2;
    Spinner spinner3;
    ImageView imageView1,imageView,imageView2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofilepage);

        t1 = findViewById(R.id.vname);
        t2 = findViewById(R.id.vmob);
        t3 = findViewById(R.id.vprn);
        t4 = findViewById(R.id.vdop);
        textView = findViewById(R.id.b1);




//        c1 = findViewById(R.id.ca1);
//        c2 = findViewById(R.id.ca2);
//        c3 = findViewById(R.id.ca3);
//        c4 = findViewById(R.id.ca9);


        imageView = findViewById(R.id.editimg);
        imageView1 = findViewById(R.id.imageView11);
        imageView2 = findViewById(R.id.imageView10);



        auth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance().getReference("staff");
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        assert currentFirebaseUser != null;
        ussd = currentFirebaseUser.getUid();


        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("staff");

//        String value = t1.getText().toString().trim();
//        String bra = t3.getText().toString().trim();








        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PROFILE_REQUEST_CODE);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(editprofilepage.this,staffdashboard.class);
                startActivity(intent);
                finish();
            }
        });


        imageView1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (uri != null) {
                    withprofileimg();
                } else {
                    withoutprofileimg();
                }
            }
        });


        db.child(ussd).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("prof_name").getValue(String.class);
                    String mobile = dataSnapshot.child("prof_mobile").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String dep = dataSnapshot.child("department").getValue(String.class);
                    String uri = dataSnapshot.child("uri").getValue(String.class);
                     link = dataSnapshot.child("uri").getValue(String.class);


                    // Set retrieved data in TextViews
                    t1.setText(name);
                    t2.setText(mobile);
                    t3.setText(email);
                    t4.setText(dep);

                    Picasso.get().load(uri).into(imageView);


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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PROFILE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show();
            imageView.setImageURI(uri);
        }
    }

    private void withprofileimg() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Updating Profile...");
        progressDialog.show();

        StorageReference pdfStorageReference = storageReference.child("Profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child(System.currentTimeMillis() + ".jpeg");

        pdfStorageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                Toast.makeText(editprofilepage.this, "profile Updated successfully", Toast.LENGTH_SHORT).show();
                pdfStorageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Bundle bundle = getIntent().getExtras();
                        auth=FirebaseAuth.getInstance();
                        String uidd = auth.getUid();


                        String email = t3.getText().toString().trim();
                        String Prof_name = t1.getText().toString().trim();
                        String Prof_mobile= t2.getText().toString().trim();
                        String Department = t4.getText().toString().trim();
                        String Uid = uidd.toString();


                        // Create a PDF object with name, description, and URL
                        staff staff = new staff(Prof_name,Prof_mobile,email,Department,uri.toString());


                        databaseReference.child(ussd).setValue(staff).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(editprofilepage.this,staffdashboard.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(editprofilepage.this, "failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                });
            }
        });
    }

    private void withoutprofileimg() {
        Map<String, String> map = new HashMap<>();
        map.put("prof_name",t1.getText().toString());
        map.put("prof_mobile",t2.getText().toString());
        map.put("prof_email",t3.getText().toString());
        map.put("department",t4.getText().toString());
        map.put("uri",link.toString());


        db.child(ussd).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(editprofilepage.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(editprofilepage.this,staffdashboard.class);
                    startActivity(intent);
                    finish();

                }else
                {
                    Toast.makeText(editprofilepage.this, "Faild to Update", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}