package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Random;

public class Question3 extends AppCompatActivity {
    User u;
    private SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle transmis = getIntent().getExtras();
        u = (User) transmis.getSerializable("User");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);
        sb = findViewById(R.id.LuckScrollBar);
        toast(u.getRomanceLevel()+" "+u.getTemerityLevel());
    }

    public void toast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    public void rollDice(android.view.View v){

        if(sb.getProgress()<3){
            u.addTemerity(-1);
        }
        else{
            u.addTemerity(1);
        }

        Random rand = new Random();
        int randomNum = rand.nextInt((5) + 1) ;
        Intent i =new Intent(Question3.this, ResultDice.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", u);
        i.putExtras(bundle);
        i.putExtra("numDe",randomNum);
        i.putExtra("numChance",sb.getProgress());
        startActivity(i);


    }

}