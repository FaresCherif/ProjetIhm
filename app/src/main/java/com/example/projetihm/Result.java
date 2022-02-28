package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Result extends AppCompatActivity {
    private User u;
    int numRomance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle transmis = getIntent().getExtras();
        u = (User) transmis.getSerializable("User");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView fullName = findViewById(R.id.FullName);
        ImageView image = findViewById(R.id.ImageResult);
        TextView texteResult = findViewById(R.id.ResultText);

        String full = u.getFirstname()+" "+u.getName();
        fullName.setText(full);
        toast(u.getRomanceLevel()+" "+u.getTemerityLevel());


        if(u.containsPerk("rich")){
            image.setImageResource(R.drawable.richlover);
            texteResult.setText(R.string.richLover);
            numRomance=0;
        }
        else if(u.getTemerityLevel()>5 && u.getRomanceLevel() > 5){
            image.setImageResource(R.drawable.perfectlover);
            texteResult.setText(R.string.perfectLover);
            numRomance=1;
        }
        else if(between(u.getTemerityLevel(),0,5) && between(u.getRomanceLevel(),0,5)){
            image.setImageResource(R.drawable.balancedlover);
            texteResult.setText(R.string.balancedLover);
            numRomance=2;
        }
        else if(u.getTemerityLevel()>10 && u.getRomanceLevel() < 5){
            image.setImageResource(R.drawable.temerritylover);
            texteResult.setText(R.string.temerityLover);
            numRomance=3;
        }
        else if(u.getTemerityLevel()<5 && u.getRomanceLevel() > 10){
            image.setImageResource(R.drawable.romancelover);
            texteResult.setText(R.string.romanceLover);
            numRomance=4;
        }
        else{
            image.setImageResource(R.drawable.romancelover);
            texteResult.setText(R.string.romanceLover);
            numRomance=5;
        }

        write_historic_in_file();
    }

    public boolean between(float valLooked,float min,float max){
        return valLooked >= min && valLooked <= max;
    }

    public void toast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    public void closeApp(android.view.View v){
        finishAffinity();
    }


    public void DeleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : Objects.requireNonNull(fileOrDirectory.listFiles())) {
                if(child.getName().contains("activity")) {
                    child.delete();
                    DeleteRecursive(child);
                }
            }
        }
        fileOrDirectory.delete();
    }

    public void write_historic_in_file() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);


        for (File child : Objects.requireNonNull(folder.listFiles())) {
            DeleteRecursive(child);
        }


        File save_user = new File(folder, u.getFirstname()+"-"+u.getName()+".txt");
        try (FileOutputStream fos = new FileOutputStream(save_user)) {
            PrintStream ps = new PrintStream(fos);
            ps.println("Name : "+u.getName());
            ps.println("Prenom : "+u.getFirstname());
            ps.println("Genre : "+u.getGender());
            ps.println("Valeur temerite : "+u.getTemerityLevel());
            ps.println("Valeur romance : "+u.getRomanceLevel());
            ps.println("Valeur du dé : "+u.getDice());
            StringBuilder array = new StringBuilder();
            for(String elem : u.getPerk()){
                array.append(elem);
                array.append("-");
            }
            ps.println("Perk : "+array);
            ps.close();
        } catch (IOException e) {
            //Log.e(APP_TAG,"File not found",e);
            e.printStackTrace();
        }//Log.e(APP_TAG,"Error I/O",e);


        File save_succes = new File(folder, "succes.txt");

        FileInputStream is = null;
        try {
            is = new FileInputStream(save_succes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            while ( (line = rd.readLine()) != null ){
                if(line.matches("Succes obtenu : temerityLover") && numRomance==3){ //--regex of what to search--
                    //--do something---
                    System.out.println("aaaa");
                    return; //--if not want to search further--
                }else if (line.matches("Succes obtenu : richLover") && numRomance==0){
                    System.out.println("aaaa");
                    return; //--if not want to search further--
                }else if (line.matches("Succes obtenu : perfectLover") && numRomance==1){
                    System.out.println("aaaa");
                    return; //--if not want to search further--
                }else if (line.matches("Succes obtenu : balancedLover") && numRomance==2){
                    System.out.println("aaaa");
                    return; //--if not want to search further--
                }else if (line.matches("Succes obtenu : romanceLover") && numRomance==4){
                    System.out.println("aaaa");
                    return; //--if not want to search further--
                }
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }


        try (FileOutputStream fos = new FileOutputStream(save_succes,true)) {
            PrintStream ps = new PrintStream(fos);

            ps.println("Name : "+u.getName());
            ps.println("Prenom : "+u.getFirstname());

            if(u.containsPerk("rich")){
                ps.println("Succes obtenu : "+"richLover");
            }
            else if(u.getTemerityLevel()>5 && u.getRomanceLevel() > 5){
                ps.println("Succes obtenu : "+"perfectLover");
            }
            else if(between(u.getTemerityLevel(),0,5) && between(u.getRomanceLevel(),0,5)){
                ps.println("Succes obtenu : "+"balancedLover");
            }
            else if(u.getTemerityLevel()>10 && u.getRomanceLevel() < 5){
                ps.println("Succes obtenu : "+"temerityLover");
            }
            else if(u.getTemerityLevel()<5 && u.getRomanceLevel() > 10){
                ps.println("Succes obtenu : "+"romanceLover");
            }else{
                ps.println("Succes obtenu : "+"no succes");
            }

            ps.println("\n");
            ps.close();
        } catch (IOException e) {
            //Log.e(APP_TAG,"File not found",e);
            e.printStackTrace();
        }//Log.e(APP_TAG,"Error I/O",e);

    }
}