package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Question5 extends AppCompatActivity {
    RadioGroup rg;
    User u;
    RadioButton destroy;
    RadioButton family;
    RadioButton cthuhllu;
    RadioButton bunker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle transmis = getIntent().getExtras();
        u = (User) transmis.getSerializable("User");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);
        rg = findViewById(R.id.MeteoriteButtonGroup);
        destroy = findViewById(R.id.DestroyMeteoriteButton);
        family = findViewById(R.id.FamillyButton);
        cthuhllu = findViewById(R.id.CthuhlluButton);
        bunker = findViewById(R.id.BunkerButton);

    }

    public void toast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    public void callNextQuestion(android.view.View v) {
        if(rg.getCheckedRadioButtonId()!=-1) {
            Intent i = new Intent(Question5.this, Question6.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("User", u);
            i.putExtras(bundle);
            startActivity(i);
            if (bunker.isChecked()){
                u.addTemerity(-2);
            }else if (family.isChecked()){
                u.addRomance(2);
            }else if (cthuhllu.isChecked()){
                u.addTemerity(1);
            }else if (destroy.isChecked()){
                u.addTemerity(3);
            }
        }else{
            toast("Veuillez choisir une reponse");
        }
    }
}