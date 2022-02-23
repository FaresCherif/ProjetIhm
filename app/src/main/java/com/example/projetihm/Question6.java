package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;

public class Question6 extends AppCompatActivity {
    User u;
    SwitchCompat nuclear;
    SwitchCompat urbex;
    SwitchCompat movie;
    SwitchCompat restarurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle transmis = getIntent().getExtras();
        u = (User) transmis.getSerializable("User");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question6);
        nuclear = findViewById(R.id.nuclear);
        urbex = findViewById(R.id.urbex);
        movie = findViewById(R.id.movie);
        restarurant = findViewById(R.id.restaurant);
    }

    public void callNextQuestion(android.view.View v) {
        if (urbex.isChecked()){
            u.addTemerity(3);
        }
        if (nuclear.isChecked()){
            u.addTemerity(-1);
            u.addRomance(-1);
        }
        if (movie.isChecked()){
            u.addRomance(3);
        }
        if (restarurant.isChecked()){
            u.addRomance(3);
        }
        Intent i =new Intent(Question6.this, Result.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", u);
        i.putExtras(bundle);
        startActivity(i);

    }
}