package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
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
        Bundle dataSent = getIntent().getExtras();
        u = (User) dataSent.getSerializable("User");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView fullName = findViewById(R.id.FullName);
        ImageView image = findViewById(R.id.ImageResult);
        TextView textResult = findViewById(R.id.ResultText);

        String full = u.getFirstname()+" "+u.getName();
        fullName.setText(full);
        toast(u.getRomanceLevel()+" "+u.getTemerityLevel());


        if(u.containsPerk("rich")){
            image.setImageResource(R.drawable.richlover);
            textResult.setText(R.string.richLover);
            numRomance=0;
        }
        else if(u.getTemerityLevel()>5 && u.getRomanceLevel() > 5){
            image.setImageResource(R.drawable.perfectlover);
            textResult.setText(R.string.perfectLover);
            numRomance=1;
        }
        else if(between(u.getTemerityLevel(),0,5) && between(u.getRomanceLevel(),0,5)){
            image.setImageResource(R.drawable.balancedlover);
            textResult.setText(R.string.balancedLover);
            numRomance=2;
        }
        else if(u.getTemerityLevel()>10 && u.getRomanceLevel() < 5){
            image.setImageResource(R.drawable.temerritylover);
            textResult.setText(R.string.temerityLover);
            numRomance=3;
        }
        else if(u.getTemerityLevel()<5 && u.getRomanceLevel() > 10){
            image.setImageResource(R.drawable.romancelover);
            textResult.setText(R.string.romanceLover);
            numRomance=4;
        }
        else{
            image.setImageResource(R.drawable.romancelover);
            textResult.setText(R.string.romanceLover);
            numRomance=5;
        }

        write_historic_in_file();

        SharedPreferences settings = getSharedPreferences("projet_IHM", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putString("User Name", u.getName());
        prefEditor.putString("User FIrstName", u.getFirstname());
        prefEditor.putString("Gender", u.getGender());
        prefEditor.putInt("Dice Value", u.getDice());
        prefEditor.putFloat("Romance Level", u.getRomanceLevel());
        prefEditor.putFloat("Temerity Level", u.getTemerityLevel());
        prefEditor.commit();
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
            ps.println("Name : " + u.getName());
            ps.println("FirstName : " + u.getFirstname());
            ps.println("Genre : " + u.getGender());
            ps.println("Temerity value : " + u.getTemerityLevel());
            ps.println("Romance value : " + u.getRomanceLevel());
            ps.println("Dice value : " + u.getDice());
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


        File save_success = new File(folder, "succes.txt");

        FileInputStream is = null;
        try {
            is = new FileInputStream(save_success);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            while ( (line = rd.readLine()) != null ){
                if(line.matches("Succes obtenu : temerityLover") && numRomance==3){ //--regex of what to search--
                    //--do something---
                    return; //--if not want to search further--
                }else if (line.matches("Succes obtenu : richLover") && numRomance==0){
                    return; //--if not want to search further--
                }else if (line.matches("Succes obtenu : perfectLover") && numRomance==1){
                    return; //--if not want to search further--
                }else if (line.matches("Succes obtenu : balancedLover") && numRomance==2){
                    return; //--if not want to search further--
                }else if (line.matches("Succes obtenu : romanceLover") && numRomance==4){
                    return; //--if not want to search further--
                }
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }


        try (FileOutputStream fos = new FileOutputStream(save_success,true)) {
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