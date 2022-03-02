package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Question6 extends AppCompatActivity {
    private User u;
    private User savedU;
    private SwitchCompat nuclear;
    private SwitchCompat urbex;
    private SwitchCompat movie;
    private SwitchCompat restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle dataSent = getIntent().getExtras();
        u = (User) dataSent.getSerializable("User");
        savedU = new User(u);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question6);
        nuclear = findViewById(R.id.nuclear);
        urbex = findViewById(R.id.urbex);
        movie = findViewById(R.id.movie);
        restaurant = findViewById(R.id.restaurant);
    }

    @Override
    public void onResume(){
        super.onResume();
        u=new User(savedU);
    }

    public void back(android.view.View v){
        finish();
    }

    public void write_historic_in_file() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File save_user = new File(folder, "activity6.txt");
        try (FileOutputStream fos = new FileOutputStream(save_user)) {
            PrintStream ps = new PrintStream(fos);
            ps.println("Name : " + u.getName());
            ps.println("FirstName : " + u.getFirstname());
            ps.println("Genre : " + u.getGender());
            ps.println("Temerity value : " + u.getTemerityLevel());
            ps.println("Romance value : " + u.getRomanceLevel());
            ps.println("Dice value : " + u.getDice());
            StringBuilder array = new StringBuilder();
            for (String elem : u.getPerk()) {
                array.append(elem);
            }
            ps.println("Perk : " + array);
            ps.close();
        } catch (IOException e) {
            //Log.e(APP_TAG,"File not found",e);
            e.printStackTrace();
        }//Log.e(APP_TAG,"Error I/O",e);

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
        if (restaurant.isChecked()){
            u.addRomance(3);
        }

        write_historic_in_file();
        Intent i =new Intent(Question6.this, Result.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", u);
        i.putExtras(bundle);
        startActivity(i);

    }
}