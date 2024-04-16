package com.example.smartscroehub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class singupasstaff extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText editText1,editText2,editText3,editText4,editText5;
    Spinner spino,spino1;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    String uiid;
    Uri uri;
    StorageReference storageReference;

    String[] courses = { "SELECT","COMPUTER", "MACHNICAL",
            "ELECTRICAL", "E&TC",
            "CIVIL" };
    ImageView imageView;
    final int PROFILE_REQUEST_CODE=01;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singupasstaff);

        editText1 = findViewById(R.id.staffemail);
        editText2 = findViewById(R.id.staffpass);
        editText3 = findViewById(R.id.staffname);
        editText4 = findViewById(R.id.staffmob);
        editText5 = findViewById(R.id.staffsecuritycode);

        button = findViewById(R.id.registerbtn);
        imageView = findViewById(R.id.profile_image);
        //////////////////////////////////////////////////

        spino = findViewById(R.id.spinner);
        spino.setOnItemSelectedListener(singupasstaff.this);

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                courses);

        ad.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spino.setAdapter(ad);
/////////////////////////////////////////////////////////////////////////
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("staff");

        storageReference = FirebaseStorage.getInstance().getReference();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String semail = editText1.getText().toString();
                String spass = editText2.getText().toString();
                String sname = editText3.getText().toString();
                String smob = editText4.getText().toString();
                String sec = editText5.getText().toString();



                if (semail.isEmpty()||spass.isEmpty()||sname.isEmpty()||smob.isEmpty()||sec.isEmpty()){
                    Toast.makeText(singupasstaff.this, "Fill All the Input", Toast.LENGTH_SHORT).show();
                }
                else {
                    singupss(semail,spass,sname,smob,sec);
                }









            }
        });



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PROFILE_REQUEST_CODE);

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PROFILE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show();
            imageView.setImageURI(uri);
        }
    }


    private void singupss(String semail, String spass, String sname, String smob, String sec) {

        if (sec.equals("7620")){
            auth.createUserWithEmailAndPassword(semail,spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                       String uid = auth.getUid();
                        uplode(uid);
                    }
                }
            });
        }
        else {
            Toast.makeText(this, "Invalid Security code", Toast.LENGTH_SHORT).show();
        }
    }
    private void uplode(String uid) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating Profile...");
        progressDialog.show();

        StorageReference pdfStorageReference = storageReference.child("StaffProfile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child(System.currentTimeMillis() + ".jpeg");

        pdfStorageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                Toast.makeText(singupasstaff.this, "profile uploaded successfully", Toast.LENGTH_SHORT).show();
                pdfStorageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Bundle bundle = getIntent().getExtras();
                        imageView.setImageURI(uri);

                        String email = editText1.getText().toString().trim();
                        String Prof_name = editText2.getText().toString().trim();
                        String Prof_mobile = editText3.getText().toString().trim();
                        String department= spino.getSelectedItem().toString();

                        // Create a PDF object with name, description, and URL
                        staff staff = new staff(Prof_mobile,Prof_name,email,department,uri.toString());

                        databaseReference.child(uid).setValue(staff).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(singupasstaff.this,login.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(singupasstaff.this, "failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                });
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