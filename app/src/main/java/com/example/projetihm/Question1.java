package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Question1 extends AppCompatActivity {
    private TextView otherGenreTitle;
    private EditText otherGenre;
    RadioGroup rg;
    private String nameGenre="";
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);
        otherGenreTitle = findViewById(R.id.OtherGenreTitle);
        otherGenre= findViewById(R.id.OtherGenre);

        otherGenreTitle.setVisibility(View.INVISIBLE);
        otherGenre.setVisibility(View.INVISIBLE);

        rg = findViewById(R.id.GenreButtonGroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.MaleGenreButton) {
                    nameGenre = "male";
                    otherGenreTitle.setVisibility(View.INVISIBLE);
                    otherGenre.setVisibility(View.INVISIBLE);
                }
                else if(checkedId==R.id.FemaleGenreButton) {
                    nameGenre = "female";
                    otherGenreTitle.setVisibility(View.INVISIBLE);
                    otherGenre.setVisibility(View.INVISIBLE);
                }
                else if(checkedId== R.id.NonBinaryGenreButton) {
                    nameGenre = "non binary";
                    otherGenreTitle.setVisibility(View.INVISIBLE);
                    otherGenre.setVisibility(View.INVISIBLE);
                }
                else if(checkedId== R.id.OtherGenreButton) {
                    otherGenreTitle.setVisibility(View.VISIBLE);
                    otherGenre.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void toast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    public void callNextQuestion(android.view.View v){

        if(rg.getCheckedRadioButtonId()!=-1) {
            int selectedId = rg.getCheckedRadioButtonId();
            RadioButton radioButtonGenreFinal = findViewById(selectedId);


            if (radioButtonGenreFinal.getId() == R.id.OtherGenreButton) {
                nameGenre = otherGenre.getText().toString();
            }
        }

        if(!nameGenre.isEmpty()){
            Intent i =new Intent(Question1.this, Question2.class);
            startActivity(i);
        }else{
            toast("Veuillez choisir un genre");
        }
    }
}