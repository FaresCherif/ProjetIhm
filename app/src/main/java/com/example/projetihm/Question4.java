package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Question4 extends AppCompatActivity {
    private String DndClass="";
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);
    }

    public void chooseClass(android.view.View v){
        Intent i =new Intent(Question4.this, Question5.class);
        startActivity(i);
    }
}