package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
    }

    public void back(android.view.View v){
        finish();
    }
}