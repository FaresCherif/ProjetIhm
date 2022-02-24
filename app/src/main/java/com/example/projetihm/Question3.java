package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class Question3 extends AppCompatActivity {
    private User u;
    private User savedU;
    private SeekBar sb;
    private ImageView dice;
    private Button rollButton;
    private LinearLayout nextQuestion;
    private String TAG="Question3Rotation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle transmis = getIntent().getExtras();
        u = (User) transmis.getSerializable("User");
        savedU = new User(u);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);
        sb = findViewById(R.id.LuckScrollBar);
        dice = findViewById(R.id.imageDice);
        rollButton=findViewById(R.id.RollDiceButton);
        nextQuestion=findViewById(R.id.layoutButton);
        toast(u.getRomanceLevel()+" "+u.getTemerityLevel());


    }

    @Override
    public void onResume(){
        super.onResume();
        u=new User(savedU);
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

        u.setDice(randomNum+1);

        int numChance = sb.getProgress();

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
        nextQuestion.setVisibility(View.VISIBLE);
        dice.setVisibility(View.VISIBLE);
        rollButton.setEnabled(false);

    }


    public void back(android.view.View v){
        finish();
    }

    public void write_historic_in_file() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File save_user = new File(folder, "activity3.txt");
        try (FileOutputStream fos = new FileOutputStream(save_user)) {
            PrintStream ps = new PrintStream(fos);
            ps.println("Name : " + u.getName());
            ps.println("Prenom : " + u.getFirstname());
            ps.println("Genre : " + u.getGender());
            ps.println("Valeur temerite : " + u.getTemerityLevel());
            ps.println("Valeur romance : " + u.getRomanceLevel());
            ps.println("Valeur du dé : " + u.getDice());
            String array = "";
            for (String elem : u.getPerk()) {
                array += elem;
            }
            ps.println("Perk : " + array);
            ps.close();
        } catch (FileNotFoundException e) {
            //Log.e(APP_TAG,"File not found",e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            //Log.e(APP_TAG,"Error I/O",e);
        }
    }

    public void callNextQuestion(android.view.View v) {

        write_historic_in_file();

        Intent i =new Intent(Question3.this, Question4.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", u);
        i.putExtras(bundle);
        startActivity(i);
    }

}