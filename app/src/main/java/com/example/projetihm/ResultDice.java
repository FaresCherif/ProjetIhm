package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class ResultDice extends AppCompatActivity {
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_dice);

        /*
        change l'image selon le numero recupere
        switch (randomNum){
            case 0:
                dice.setImageResource(R.drawable.dice1);
                break;
            case 1:
                dice.setImageResource(R.drawable.dice2);
                break;
            case 2:
                dice.setImageResource(R.drawable.dice3);
                break;
            case 3:
                dice.setImageResource(R.drawable.dice4);
                break;
            case 4:
                dice.setImageResource(R.drawable.dice5);
                break;
            case 5:
                dice.setImageResource(R.drawable.dice6);
                break;
        }
        */

    }

    public void callNextQuestion(android.view.View v) {
        Intent i =new Intent(ResultDice.this, Question4.class);
        startActivity(i);
    }
}