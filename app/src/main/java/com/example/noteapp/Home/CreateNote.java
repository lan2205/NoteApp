package com.example.noteapp.Home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
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
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNote extends AppCompatActivity {

    private ImageView img_backHome,img_Note;
    private ImageView img_More;
    private TextView tv_dateTime;
    private EditText inputNoteTitle, inputNoteText;
    private String selectedNoteColor;
    private String selectedImagePath;

    private Note alreadyAvailableNote;

    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;

    private String selectedTextColor;
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
        img_Note = (ImageView)findViewById(R.id.imageNote);
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
                Intent intent = new Intent(CreateNote.this, Actions.class);
                startActivity(intent);
            }
        });
        tv_dateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a",Locale.getDefault())
                .format(new Date())
        );

        if(getIntent().getBooleanExtra("isVieworUpdate",false)){
            alreadyAvailableNote = (Note) getIntent().getSerializableExtra("note");
            setVieworUpdate();
        }
        selectedTextColor = "#FFFFFF";
        selectedNoteColor = "#CBDFBD";
        selectedImagePath="";
        initNote();
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
        note.setImagePath(selectedImagePath);

        if(alreadyAvailableNote != null){
            note.setId(alreadyAvailableNote.getId());
        }

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

//        if(alreadyAvailableNote != null && alreadyAvailableNote.getColor() != null && !alreadyAvailableNote.getColor().trim().isEmpty()){
//            switch (alreadyAvailableNote.getColor()){
//                case "#CBDFBD":
//                    layoutNote.findViewById(R.id.viewColor2).performClick();
//                    break;
//                case "#90E0EF":
//                    layoutNote.findViewById(R.id.viewColor3).performClick();
//                    break;
//                case "#e76f51":
//                    layoutNote.findViewById(R.id.viewColor4).performClick();
//                    break;
//                case "#FFB703":
//                    layoutNote.findViewById(R.id.viewColor5).performClick();
//                    break;
//            }
//        }
        layoutNote.findViewById(R.id.layoutAddCover).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                if(ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(
                            CreateNote.this,
                            new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_STORAGE_PERMISSION
                    );
                }
                else {
                    selectImage();
                }
            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
        }
    }

    private void setIndicatorColor(){
        GradientDrawable gradientDrawable = (GradientDrawable) indicator.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectedNoteColor));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0){
            selectImage();
        }
        else {
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK){
            if(data != null){
                Uri selectedImageUri = data.getData();
                if(selectedImageUri != null){
                    try{
                        InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        img_Note.setImageBitmap(bitmap);
                        img_Note.setVisibility(View.VISIBLE);

                        selectedImagePath = getPathFromUri(selectedImageUri);
                    }
                    catch (Exception e) {
                        Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private String getPathFromUri(Uri contentUri){
        String filepath;
        Cursor cursor = getContentResolver()
                .query(contentUri,null,null,null,null);
        if(cursor == null){
            filepath = contentUri.getPath();
        }
        else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filepath = cursor.getString(index);
            cursor.close();
        }
        return filepath;
    }

    private void setVieworUpdate(){
        inputNoteTitle.setText(alreadyAvailableNote.getTitle());
        inputNoteText.setText(alreadyAvailableNote.getNoteText());
        tv_dateTime.setText(alreadyAvailableNote.getDateTime());

        if(alreadyAvailableNote.getImagePath() != null && !alreadyAvailableNote.getImagePath().trim().isEmpty()){
            img_Note.setImageBitmap(BitmapFactory.decodeFile(alreadyAvailableNote.getImagePath()));
            img_Note.setVisibility(View.VISIBLE);
            selectedImagePath = alreadyAvailableNote.getImagePath();
        }
        if(alreadyAvailableNote.getWebLink() != null && !alreadyAvailableNote.getWebLink().trim().isEmpty()){

        }
    }
}