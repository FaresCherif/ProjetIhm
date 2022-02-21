package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class Question3 extends AppCompatActivity {
    private Button diceButton;
    private ImageView dice;
    private Button validate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        diceButton=findViewById(R.id.RollDiceButton);
        dice=findViewById(R.id.DiceImage);
        validate=findViewById(R.id.ButtonNextQuestion3);

        dice.setVisibility(View.INVISIBLE);
        validate.setVisibility(View.INVISIBLE);
    }

    public void rollDice(android.view.View v){
        Random rand = new Random();
        int randomNum = rand.nextInt((5) + 1) ;

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
        dice.setVisibility(View.VISIBLE);
        validate.setVisibility(View.VISIBLE);
    }

    public void callNextQuestion(android.view.View v) {
        Intent i =new Intent(Question3.this, Question4.class);
        startActivity(i);
    }
}