package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

public class Question2 extends AppCompatActivity {
    private User u;
    private SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle transmis = getIntent().getExtras();
        u = (User) transmis.getSerializable("User");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);
        toast(u.getRomanceLevel()+" "+u.getTemerityLevel());

        sb=findViewById(R.id.FidelityScrollBar);
    }

    public void toast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    public void callNextQuestion(android.view.View v) {
        Intent i =new Intent(Question2.this, Question3.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", u);
        u.addRomance(sb.getProgress());
        i.putExtras(bundle);
        startActivity(i);
    }

}