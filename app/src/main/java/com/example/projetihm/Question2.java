package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Question2 extends AppCompatActivity {
    private User u;
    private User savedU;
    private SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle transmis = getIntent().getExtras();
        u = (User) transmis.getSerializable("User");
        savedU = new User(u);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);
        toast(u.getRomanceLevel()+" "+u.getTemerityLevel());

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

    public void toast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    public void write_historic_in_file() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File save_user = new File(folder, "activity2.txt");
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

        write_historic_in_file();

        Intent i =new Intent(Question2.this, Question3.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", u);
        u.addRomance(sb.getProgress());
        i.putExtras(bundle);
        startActivity(i);
    }

}