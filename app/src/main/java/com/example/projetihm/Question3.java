package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class Question3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);



    }

    public void rollDice(android.view.View v){
        Random rand = new Random();
        int randomNum = rand.nextInt((5) + 1) ;
        Intent i =new Intent(Question3.this, ResultDice.class);
        startActivity(i);


    }

}