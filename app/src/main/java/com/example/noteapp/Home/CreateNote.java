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

    private ImageView img_backHome;
    private ImageView img_More;
    private TextView tv_dateTime;
    private EditText inputNoteTitle, inputNoteText;
    private String selectedNoteColor,selectedImagePath;
    private  ImageView imageCover;

    public static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    public static final int REQUEST_CODE_SELECT_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        img_backHome = (ImageView) findViewById(R.id.imageBack);
        img_More = (ImageView) findViewById(R.id.more_actions);
        tv_dateTime = (TextView)findViewById(R.id.textDateTime);
        inputNoteTitle = (EditText)findViewById(R.id.inputNoteTitle);
        inputNoteText = (EditText)findViewById(R.id.inputNote);
        imageCover = findViewById(R.id.imageNote);



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
        selectedImagePath = "";
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

        layoutNote.findViewById(R.id.viewColor1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectedNoteColor = "#80C4C4C4";
                imageColor1.setImageResource(R.drawable.ic_done);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
            }
        });

        layoutNote.findViewById(R.id.viewColor2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectedNoteColor = "#CBDFBD";
                imageColor2.setImageResource(R.drawable.ic_done);
                imageColor1.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
            }
        });

        layoutNote.findViewById(R.id.viewColor3).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectedNoteColor = "#90E0EF";
                imageColor3.setImageResource(R.drawable.ic_done);
                imageColor2.setImageResource(0);
                imageColor1.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
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
            }
        });

        layoutNote.findViewById(R.id.viewColor5).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectedNoteColor = "#FFB703";
                imageColor5.setImageResource(R.drawable.ic_done);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor1.setImageResource(0);
            }
        });
        layoutNote.findViewById(R.id.layoutAddCover).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            CreateNote.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_STORAGE_PERMISSION
                    );
                }
                else {
                    selectImage();
                }
            }
        });
    }
    private void selectImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                selectImage();
            }
            else {
                Toast.makeText(this,"Permission Denied!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SELECT_IMAGE && requestCode == RESULT_OK){
            if(data != null){
                Uri selectedImageUri = data.getData();
                if(selectedImageUri != null){
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageCover.setImageBitmap(bitmap);
                        imageCover.setVisibility(View.VISIBLE);

                        selectedImagePath = getPathFromUri(selectedImageUri);
                    }
                    catch (Exception e) {
                        Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
           }
        }
    }
    private String getPathFromUri (Uri contentUri){
        String filePath;
        Cursor cursor = getContentResolver()
                .query(contentUri,null,null,null,null);
        if(cursor == null){
            filePath = contentUri.getPath();
        }
        else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }
}