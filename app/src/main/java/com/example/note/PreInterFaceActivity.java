package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.zip.Inflater;

public class PreInterFaceActivity extends AppCompatActivity {
    CardView cardViewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_inter_face);
        cardViewNote = findViewById(R.id.card_view_note);

        cardViewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PreInterFaceActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PreInterFaceActivity.this,MainActivity.class);
                startActivity(i);
            }
        });


    }
}