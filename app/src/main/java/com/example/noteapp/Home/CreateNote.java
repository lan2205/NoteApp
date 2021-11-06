package com.example.noteapp.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.noteapp.Database.NotesDatabase;
import com.example.noteapp.Entities.Note;
import com.example.noteapp.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNote extends AppCompatActivity {

    private ImageView img_backHome;
    private ImageView img_More;
    private TextView tv_dateTime;
    private EditText inputNoteTitle, inputNoteText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        img_backHome = (ImageView) findViewById(R.id.imageBack);
        img_More = (ImageView) findViewById(R.id.more_actions);
        tv_dateTime = (TextView)findViewById(R.id.textDateTime);
        inputNoteTitle = (EditText)findViewById(R.id.inputNoteTitle);
        inputNoteText = (EditText)findViewById(R.id.inputNote);

        img_backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!inputNoteText.getText().toString().isEmpty())
                {
                    saveNote();
                    if(!inputNoteTitle.getText().toString().isEmpty())
                    {
                        Intent intent = new Intent(CreateNote.this, Home.class);
                        startActivity(intent);
                    }
                }
                else
                {
                    if(!inputNoteTitle.getText().toString().isEmpty())
                    {
                        saveNote();
                        Intent intent = new Intent(CreateNote.this, Home.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent(CreateNote.this, Home.class);
                        startActivity(intent);
                    }
                }
            }
        });
        img_More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateNote.this, CreateNote.class);
                startActivity(intent);
            }
        });
        tv_dateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a",Locale.getDefault())
                .format(new Date())
        );
    }
    private void saveNote(){
        if(inputNoteTitle.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"Note title can't be empty!",Toast.LENGTH_SHORT).show();
            return;
        }
//        else if(inputNoteText.getText().toString().trim().isEmpty()){
//            Toast.makeText(this,"Note can't be empty!",Toast.LENGTH_SHORT).show();
//            return;
//        }
        final Note note = new Note();
        note.setTitle(inputNoteTitle.getText().toString());
        note.setDateTime(tv_dateTime.getText().toString());
        note.setNoteText(inputNoteText.getText().toString());

        @SuppressLint("StaticFieldLeak")
        class SaveNoteTask extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                NotesDatabase.getDatabase(getApplicationContext()).noteDao().insertNote(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        }
        new SaveNoteTask().execute();
    }
}