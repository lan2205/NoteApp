package com.example.noteapp.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.noteapp.Adapters.NotesApdapter;
import com.example.noteapp.Database.NotesDatabase;
import com.example.noteapp.Entities.Note;
import com.example.noteapp.R;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_NOTE = 1;

    private RecyclerView notesRecyclerView;
    private List<Note> noteList;
    private NotesApdapter notesApdapter;

    private ImageView img_NewNote;
    private ImageView img_Search;
    private ImageView ic_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        img_NewNote = (ImageView) findViewById(R.id.newNote);
        img_NewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, CreateNote.class);
                startActivity(intent);
            }
        });


        notesRecyclerView = findViewById(R.id.notesRecyclerView);
        notesRecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        );

        noteList = new ArrayList<>();
        notesApdapter = new NotesApdapter(noteList);
        notesRecyclerView.setAdapter(notesApdapter);

        getNotes();

        img_Search = (ImageView) findViewById(R.id.Search);
        img_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Home.this, Search.class);
                startActivity(intent2);
            }
        });

            ic_menu = (ImageView) findViewById(R.id.ic_menu);
        ic_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Home.this, Settings.class);
                startActivity(intent3);
            }
        });
    }
    private void getNotes(){
        @SuppressLint("StaticFieldLeak")
        class GetNotesTask extends AsyncTask<Void, Void, List<Note>>{

            @Override
            protected List<Note> doInBackground(Void... voids) {
                return NotesDatabase
                        .getDatabase(getApplicationContext())
                        .noteDao().getAllNotes();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
                if(noteList.size() == 0){
                    noteList.addAll(notes);
                    notesApdapter.notifyDataSetChanged();
                }
                else {
                    noteList.add(0,notes.get(0));
                    notesApdapter.notifyItemInserted(0);
                }
                notesRecyclerView.smoothScrollToPosition(0);
            }
        }
        new GetNotesTask().execute();
    }
}