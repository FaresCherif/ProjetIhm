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
    private RadioGroup rg;
    private String nameGenre="";
    private User u;
    private User savedU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle transmis = getIntent().getExtras();
        u = (User) transmis.getSerializable("User");
        savedU = new User(u);

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
    
    @Override
    public void onResume(){
        super.onResume();
        u=new User(savedU);
    }

    public void back(android.view.View v){
        finish();
    }

    public void toast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    public void callNextQuestion(android.view.View v){

        if(rg.getCheckedRadioButtonId()!=-1) {
            int selectedId = rg.getCheckedRadioButtonId();
            RadioButton radioButtonGenreFinal = findViewById(selectedId);


            if(radioButtonGenreFinal.getId() == R.id.OtherGenreButton) {
                nameGenre = otherGenre.getText().toString();
            }
            else if(radioButtonGenreFinal.getId() == R.id.MaleGenreButton) {
                u.addRomance(-1);
                u.addTemerity(1);
            }
            else if(radioButtonGenreFinal.getId() == R.id.FemaleGenreButton) {
                u.addRomance(1);
                u.addTemerity(-1);
            }
        }


        if(!nameGenre.isEmpty()){
            u.setGender(nameGenre);
            Intent i =new Intent(Question1.this, Question2.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("User", u);
            i.putExtras(bundle);
            startActivity(i);
        }else{
            toast("Veuillez choisir un genre");
        }
    }
}