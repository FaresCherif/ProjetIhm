package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Question5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);
    }

    public void callNextQuestion(android.view.View v) {
        Intent i =new Intent(Question5.this, Question6.class);
        startActivity(i);
    }
}