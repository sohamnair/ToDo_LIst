package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegPage extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    Button reg;
    SharedPreferences sp;

    Toolbar tb;

    EditText name, contact, age, email, pass, dob;
    String strname, strage, strcontact, stremail, strpass, strdob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_page);

        reg = findViewById(R.id.reg);
        tb = findViewById(R.id.tb);
        tb.setTitle("Registration Page");
        setSupportActionBar(tb);

        sp = getSharedPreferences("login", MODE_PRIVATE);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        dob = findViewById(R.id.dob);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strname = name.getText().toString();
                strcontact = contact.getText().toString();
                strage = age.getText().toString();
                stremail = email.getText().toString();
                strpass = pass.getText().toString();
                strdob = dob.getText().toString();

                if(strname.equals("") || strcontact.equals("") || strage.equals("") || stremail.equals("") || strpass.equals("") || strdob.equals("")) {
                    Toast.makeText(RegPage.this,"Text Box Cannot Be Empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.createUserWithEmailAndPassword(stremail,strpass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        HashMap<String, Object> hm = new HashMap<>();
                                        hm.put("Name",strname);
                                        hm.put("Age",strage);
                                        hm.put("Dob",strdob);
                                        hm.put("Email",stremail);
                                        hm.put("Pass",strpass);
                                        hm.put("Contact",strcontact);
                                        Toast.makeText(RegPage.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("UserData").child(strcontact);
                                        ref.updateChildren(hm).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                SharedPreferences.Editor editor = sp.edit();
                                                editor.putString("num", strcontact);
                                                editor.putBoolean("log",true);
                                                editor.apply();
                                                Intent i = new Intent(RegPage.this,MainActivity.class);
                                                startActivity(i);
                                            }
                                        });
                                    }
                                }
                    });

                }

            }
        });

    }
}