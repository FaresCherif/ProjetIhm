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


    public void DeleteRecursive(File child) {
        if(child.getName().contains("activity")) {
            child.delete();
            //System.out.println("name child "+child.getName());
        }
    }

    public void write_historic_in_file() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);


        for (File child : Objects.requireNonNull(folder.listFiles())) {
            //System.out.println("child "+child.getName());
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
                if(line.matches("Succes obtenu : temerityLover") && numRomance==3){
                    return;
                }else if (line.matches("Succes obtenu : richLover") && numRomance==0){
                    return;
                }else if (line.matches("Succes obtenu : perfectLover") && numRomance==1){
                    return;
                }else if (line.matches("Succes obtenu : balancedLover") && numRomance==2){
                    return;
                }else if (line.matches("Succes obtenu : romanceLover") && numRomance==4){
                    return;
                }
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }


        try (FileOutputStream fos = new FileOutputStream(save_success,true)) {
            PrintStream ps = new PrintStream(fos);

            ps.println("Name : "+u.getName());
            ps.println("Prenom : "+u.getFirstname());

            SharedPreferences settings = getSharedPreferences("projet_IHM", MODE_PRIVATE);
            int total_number_succes=0;
            total_number_succes = settings.getInt("Number of succes",total_number_succes);

            System.out.println(u.getTemerityLevel()+" "+u.getRomanceLevel());
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
            System.out.println("total");
            System.out.println(total_number_succes);
            SharedPreferences.Editor prefEditor = settings.edit();
            total_number_succes++;
            System.out.println(total_number_succes);
            prefEditor.putInt("Number of succes", total_number_succes);
            prefEditor.apply();

            ps.println("\n");
            fos.close();

        } catch (IOException e) {
            //Log.e(APP_TAG,"File not found",e);
            e.printStackTrace();
        }//Log.e(APP_TAG,"Error I/O",e);

    }
}