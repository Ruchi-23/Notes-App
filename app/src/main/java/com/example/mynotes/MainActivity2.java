package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.DatabaseClass;
import com.example.mynotes.MainActivity;

public class MainActivity2 extends AppCompatActivity {

    EditText title,description;
    Button updateNotes;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        title=findViewById(R.id.title);
        description=findViewById(R.id.description);
        updateNotes=findViewById(R.id.updateNote);

        Intent i =getIntent();
        title.setText(i.getStringExtra("title"));
        description.setText(i.getStringExtra("description"));
        id=i.getStringExtra("id");

        updateNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString()))
                {

                    DatabaseClass db = new DatabaseClass(com.example.mynotes.MainActivity2.this);
                    db.updateNotes(title.getText().toString(),description.getText().toString(),id);

                    Intent i=new Intent(com.example.mynotes.MainActivity2.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();


                }
                else
                {
                    Toast.makeText(com.example.mynotes.MainActivity2.this, "Both Fields Required", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }}
