package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddItemsToList extends AppCompatActivity {

    Toolbar tb;
    EditText date, task;
    Button addtask;
    SharedPreferences sp, sp1;
    int i = 0;
    String num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items_to_list);

        tb = findViewById(R.id.fragtb);
        tb.setTitle("Add Task");
        setSupportActionBar(tb);

        date = findViewById(R.id.date);
        task = findViewById(R.id.task);
        addtask = findViewById(R.id.addtask);

        sp = getSharedPreferences("Id",MODE_PRIVATE);
        i = sp.getInt("i",0);

        sp1 = getSharedPreferences("login", Context.MODE_PRIVATE);
        num = sp1.getString("num","");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Task").child(num);
        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = date.getText().toString();
                String s2 = task.getText().toString();
                if(s1.equals("") || s2.equals("")) {
                    Toast.makeText(AddItemsToList.this,"Text Box Cannot Be Empty",Toast.LENGTH_LONG).show();
                }
                else {
                    i++;
                    HashMap<String,Object> hm = new HashMap<>();
                    hm.put("Date",s1);
                    hm.put("Task",s2);
                    hm.put("Key",String.valueOf(i));
                    ref.child(String.valueOf(i)).updateChildren(hm).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            date.setText("");
                            task.setText("");
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putInt("i",i);
                            editor.apply();
                            Toast.makeText(AddItemsToList.this,"Task Added Successfully",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}