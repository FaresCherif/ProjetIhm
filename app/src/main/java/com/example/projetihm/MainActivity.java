package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callUserInput(android.view.View v){
        Intent i =new Intent(MainActivity.this, UserInput.class);
        startActivity(i);
    }
}