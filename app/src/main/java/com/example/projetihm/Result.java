package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends AppCompatActivity {
    private User u;
    private TextView fullName;
    private ImageView image;
    private TextView texteResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle transmis = getIntent().getExtras();
        u = (User) transmis.getSerializable("User");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        fullName = findViewById(R.id.FullName);
        image= findViewById(R.id.ImageResult);
        texteResult=findViewById(R.id.ResultText);

        fullName.setText(u.getFirstname()+" "+u.getName());
        toast(u.getRomanceLevel()+" "+u.getTemerityLevel());


        if(u.getTemerityLevel()>5 && u.getRomanceLevel() > 5){
            image.setImageResource(R.drawable.resultlove);
            texteResult.setText(R.string.perfectLover);
        }
        if(between(u.getTemerityLevel(),0,5) && between(u.getRomanceLevel(),0,5)){
            image.setImageResource(R.drawable.resultlove);
            texteResult.setText(R.string.balancedLover);
        }
        if(u.getTemerityLevel()>10 && u.getRomanceLevel() < 5){
            image.setImageResource(R.drawable.resultlove);
            texteResult.setText(R.string.temerityLover);
        }
        if(u.getTemerityLevel()<5 && u.getRomanceLevel() > 10){
            image.setImageResource(R.drawable.resultlove);
            texteResult.setText(R.string.romanceLover);
        }
    }

    public boolean between(float valLooked,float min,float max){
        if(valLooked>=min && valLooked<=max){
            return true;
        }
        return false;
    }

    public void toast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

}