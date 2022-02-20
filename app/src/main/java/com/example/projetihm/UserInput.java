package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class UserInput extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    public static final String TAG = "UserInpur";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
    }

    public void toast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    public void callQuestion1(android.view.View v){
        firstName=findViewById(R.id.FirstNameEntreeArea);
        lastName=findViewById(R.id.NameEntreeArea);


        if(!firstName.getText().toString().isEmpty()&&!lastName.getText().toString().isEmpty()) {
            Intent i = new Intent(UserInput.this, Question1.class);
            startActivity(i);
        }
        else{
            toast("Veuillez entrer un nom et prenom");
        }
    }
}