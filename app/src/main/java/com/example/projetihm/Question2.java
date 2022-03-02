package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.SeekBar;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Question2 extends AppCompatActivity {
    private User u;
    private User savedU;
    private SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle dataSent = getIntent().getExtras();
        u = (User) dataSent.getSerializable("User");
        savedU = new User(u);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        sb=findViewById(R.id.FidelityScrollBar);
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
        File save_user = new File(folder, "activity2.txt");
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

        write_historic_in_file();

        Intent i =new Intent(Question2.this, Question3.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", u);
        u.addRomance(sb.getProgress());
        i.putExtras(bundle);
        startActivity(i);
    }

}