package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

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

    public void back(android.view.View v){
        finish();
    }

    public void toast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    public void write_historic_in_file() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File save_user = new File(folder, "activity5.txt");
        try (FileOutputStream fos = new FileOutputStream(save_user)) {
            PrintStream ps = new PrintStream(fos);
            ps.println("Name : " + u.getName());
            ps.println("Prenom : " + u.getFirstname());
            ps.println("Genre : " + u.getGender());
            ps.println("Valeur temerite : " + u.getTemerityLevel());
            ps.println("Valeur romance : " + u.getRomanceLevel());
            ps.println("Valeur du dé : " + u.getDice());
            String array = "";
            for (String elem : u.getPerk()) {
                array += elem;
            }
            ps.println("Perk : " + array);
            ps.close();
        } catch (FileNotFoundException e) {
            //Log.e(APP_TAG,"File not found",e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            //Log.e(APP_TAG,"Error I/O",e);
        }
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


            write_historic_in_file();
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