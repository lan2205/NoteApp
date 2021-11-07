package com.example.noteapp.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.noteapp.Database.NotesDatabase;
import com.example.noteapp.Entities.Note;
import com.example.noteapp.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNote extends AppCompatActivity {

    private ImageView img_backHome;
    private ImageView img_More;
    private TextView tv_dateTime;
    private EditText inputNoteTitle, inputNoteText;
    private String selectedNoteColor, selectedTextColor;
    private View indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        img_backHome = (ImageView) findViewById(R.id.imageBack);
        img_More = (ImageView) findViewById(R.id.more_actions);
        tv_dateTime = (TextView)findViewById(R.id.textDateTime);
        inputNoteTitle = (EditText)findViewById(R.id.inputNoteTitle);
        inputNoteText = (EditText)findViewById(R.id.inputNote);
        indicator = findViewById(R.id.indicator);

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

        selectedNoteColor = "#CBDFBD";
        selectedTextColor = "#FFFFFF";
        initNote();
        setIndicatorColor();

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
        note.setColor(selectedNoteColor);

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
    private void initNote(){
        final LinearLayout layoutNote = findViewById(R.id.layoutNote);
        final BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(layoutNote);

        layoutNote.findViewById(R.id.imgPull).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        final ImageView imageColor1 = layoutNote.findViewById(R.id.imageColor1);
        final ImageView imageColor2 = layoutNote.findViewById(R.id.imageColor2);
        final ImageView imageColor3 = layoutNote.findViewById(R.id.imageColor3);
        final ImageView imageColor4 = layoutNote.findViewById(R.id.imageColor4);
        final ImageView imageColor5 = layoutNote.findViewById(R.id.imageColor5);
        final ImageView imageTextColor1 = layoutNote.findViewById(R.id.imageTextColor1);
        final ImageView imageTextColor2 = layoutNote.findViewById(R.id.imageTextColor2);
        final ImageView imageTextColor3 = layoutNote.findViewById(R.id.imageTextColor3);
        final ImageView imageTextColor4 = layoutNote.findViewById(R.id.imageTextColor4);
        final ImageView imageTextColor5 = layoutNote.findViewById(R.id.imageTextColor5);

        layoutNote.findViewById(R.id.viewTextColor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTextColor = "#FFFFFFFF";
                imageTextColor1.setImageResource(R.drawable.ic_done2);
                imageTextColor2.setImageResource(0);
                imageTextColor3.setImageResource(0);
                imageTextColor4.setImageResource(0);
                imageTextColor5.setImageResource(0);
                inputNoteText.setTextColor(Color.parseColor(selectedTextColor));
            }
        });
        layoutNote.findViewById(R.id.viewTextColor2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTextColor = "#F8961E";
                imageTextColor2.setImageResource(R.drawable.ic_done2);
                imageTextColor1.setImageResource(0);
                imageTextColor3.setImageResource(0);
                imageTextColor4.setImageResource(0);
                imageTextColor5.setImageResource(0);
                inputNoteText.setTextColor(Color.parseColor(selectedTextColor));
            }
        });
        layoutNote.findViewById(R.id.viewTextColor3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTextColor = "#4F772D";
                imageTextColor3.setImageResource(R.drawable.ic_done2);
                imageTextColor1.setImageResource(0);
                imageTextColor2.setImageResource(0);
                imageTextColor4.setImageResource(0);
                imageTextColor5.setImageResource(0);
                inputNoteText.setTextColor(Color.parseColor(selectedTextColor));
            }
        });
        layoutNote.findViewById(R.id.viewTextColor4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTextColor = "#FFF3B0";
                imageTextColor4.setImageResource(R.drawable.ic_done2);
                imageTextColor1.setImageResource(0);
                imageTextColor2.setImageResource(0);
                imageTextColor3.setImageResource(0);
                imageTextColor5.setImageResource(0);
                inputNoteText.setTextColor(Color.parseColor(selectedTextColor));
            }
        });
        layoutNote.findViewById(R.id.viewTextColor5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTextColor = "#90E0EF";
                imageTextColor5.setImageResource(R.drawable.ic_done2);
                imageTextColor1.setImageResource(0);
                imageTextColor2.setImageResource(0);
                imageTextColor3.setImageResource(0);
                imageTextColor4.setImageResource(0);
                inputNoteText.setTextColor(Color.parseColor(selectedTextColor));

            }
        });
        
        layoutNote.findViewById(R.id.viewColor1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectedNoteColor = "#CBDFBD";
                imageColor1.setImageResource(R.drawable.ic_done);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                setIndicatorColor();
            }
        });

        layoutNote.findViewById(R.id.viewColor2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectedNoteColor = "#FFB703";
                imageColor2.setImageResource(R.drawable.ic_done);
                imageColor1.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                setIndicatorColor();
            }
        });

        layoutNote.findViewById(R.id.viewColor3).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectedNoteColor = "#52b69a";
                imageColor3.setImageResource(R.drawable.ic_done);
                imageColor2.setImageResource(0);
                imageColor1.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                setIndicatorColor();
            }
        });

        layoutNote.findViewById(R.id.viewColor4).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectedNoteColor = "#e76f51";
                imageColor4.setImageResource(R.drawable.ic_done);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor1.setImageResource(0);
                imageColor5.setImageResource(0);
                setIndicatorColor();
            }
        });

        layoutNote.findViewById(R.id.viewColor5).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectedNoteColor = "#80C4C4C4";
                imageColor5.setImageResource(R.drawable.ic_done);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                setIndicatorColor();
            }
        });

    }
    private void setIndicatorColor(){
        GradientDrawable gradientDrawable =(GradientDrawable) indicator.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectedNoteColor));
    }



}