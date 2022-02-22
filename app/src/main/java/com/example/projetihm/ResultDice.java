package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class ResultDice extends AppCompatActivity {
    User u;
    private ImageView dice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle transmis = getIntent().getExtras();
        u = (User) transmis.getSerializable("User");
        int randomNum = Integer.parseInt(transmis.get("numDe").toString());
        int numChance = Integer.parseInt(transmis.get("numChance").toString());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_dice);
        dice = findViewById(R.id.ImageDice);

        if(numChance>=randomNum){
            u.addRomance(numChance);
        }else{
            u.addRomance(numChance-randomNum);
        }

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


    }

    public void callNextQuestion(android.view.View v) {
        Intent i =new Intent(ResultDice.this, Question4.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", u);
        i.putExtras(bundle);
        startActivity(i);
    }
}