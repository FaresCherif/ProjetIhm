package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Question5 extends AppCompatActivity {
    private RadioGroup rg;
    private User u;
    private User savedU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle transmis = getIntent().getExtras();
        u = (User) transmis.getSerializable("User");
        savedU = new User(u);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);
        rg = findViewById(R.id.MeteoriteButtonGroup);
    }

    @Override
    public void onResume(){
        super.onResume();
        u=new User(savedU);
    }

    public void toast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    public void callNextQuestion(android.view.View v) {
        if(rg.getCheckedRadioButtonId()!=-1) {

            int selectedId = rg.getCheckedRadioButtonId();
            RadioButton radioButtonMeteoriteFinal = findViewById(selectedId);

            if(radioButtonMeteoriteFinal.getId()==R.id.BunkerButton) {
                u.addTemerity(-2);
            }
            else if(radioButtonMeteoriteFinal.getId() == R.id.FamillyButton ) {
                u.addRomance(2);
            }
            else if(radioButtonMeteoriteFinal.getId() ==R.id.CthuhlluButton) {
                u.addTemerity(1);
            }
            else if(radioButtonMeteoriteFinal.getId()== R.id.DestroyMeteoriteButton) {
                u.addTemerity(3);
            }


            Intent i = new Intent(Question5.this, Question6.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("User", u);
            i.putExtras(bundle);
            startActivity(i);
        }else{
            toast("Veuillez choisir une reponse");
        }
    }
}