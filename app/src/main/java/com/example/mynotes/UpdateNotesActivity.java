package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateNotesActivity extends AppCompatActivity {
    TextView title, description;
    Button editNotes, copyNotes, shareNotes;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        editNotes = findViewById(R.id.editBtn);
        copyNotes = findViewById(R.id.copyBtn);
        shareNotes = findViewById(R.id.shareBtn);


        Intent i = getIntent();
        title.setText(i.getStringExtra("title"));
        description.setText(i.getStringExtra("description"));
        id = i.getStringExtra("id");
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;

        editNotes = (Button) findViewById(R.id.editBtn);

        editNotes.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("noteId", intent.getStringExtra("id"));
                    startActivity(intent);

            }
        });
        copyNotes = (Button) findViewById(R.id.copyBtn);
        copyNotes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String getstring = title.getText().toString();
                String getstring2 = description.getText().toString();
                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", getstring+getstring2);
                clipboard.setPrimaryClip(clip);
                Toast toast = Toast.makeText(getApplicationContext(), "Text copied to clipboard!", Toast.LENGTH_LONG);

                toast.show();


            }

        });
        shareNotes = (Button) findViewById(R.id.shareBtn);
        shareNotes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

            }
        });




    }


}