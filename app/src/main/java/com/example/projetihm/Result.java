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

        String full = u.getFirstname()+" "+u.getName();
        fullName.setText(full);
        toast(u.getRomanceLevel()+" "+u.getTemerityLevel());


        if(u.containsPerk("rich")){
            image.setImageResource(R.drawable.richlover);
            texteResult.setText(R.string.richLover);
        }
        else if(u.getTemerityLevel()>5 && u.getRomanceLevel() > 5){
            image.setImageResource(R.drawable.perfectlover);
            texteResult.setText(R.string.perfectLover);
        }
        else if(between(u.getTemerityLevel(),0,5) && between(u.getRomanceLevel(),0,5)){
            image.setImageResource(R.drawable.balancedlover);
            texteResult.setText(R.string.balancedLover);
        }
        else if(u.getTemerityLevel()>10 && u.getRomanceLevel() < 5){
            image.setImageResource(R.drawable.temeritylover);
            texteResult.setText(R.string.temerityLover);
        }
        else if(u.getTemerityLevel()<5 && u.getRomanceLevel() > 10){
            image.setImageResource(R.drawable.romancelover);
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