package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);
    }

    public void load(android.view.View v){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File actual_file = new File("test");//TODO a enlever

        File file6 = new File(folder, "activity6.txt");
        File file5 = new File(folder, "activity5.txt");
        File file4 = new File(folder, "activity4.txt");
        File file3 = new File(folder, "activity3.txt");
        File file2 = new File(folder, "activity2.txt");
        File file1 = new File(folder, "activity1.txt");
        int activity_number=0;
        if (file6.exists()){
            actual_file = file6;
            activity_number=6;
        }else if(file5.exists()){
            actual_file = file5;
            activity_number=5;
        }else if(file4.exists()){
            actual_file = file4;
            activity_number=4;
        }else if(file3.exists()){
            actual_file = file3;
            activity_number=3;
        }else if(file2.exists()){
            actual_file = file2;
            activity_number=2;
        }else if(file1.exists()){
            actual_file = file1;
            activity_number=1;
        }

        FileInputStream is = null;
        try {
            is = new FileInputStream(actual_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String line;
            int cpt=1;
            String name ="";
            String first_name="";
            String gender="";
            int dice=0;
            float temerityLevel=0;
            float romanceLevel=0;
            String temp="";
            ArrayList<String> perksList = new ArrayList<>();;

            while ( (line = rd.readLine()) != null ){
                if (cpt==1){//nom
                    name = line.substring(7,line.length());
                    System.out.println(name);
                }
                if (cpt==2){//prenom
                    first_name = line.substring(9,line.length());
                    System.out.println(first_name);
                }
                if (cpt==3){//genre
                    gender = line.substring(8,line.length());
                    System.out.println(gender);
                }
                if (cpt==4){//temerite
                    temerityLevel = Float.parseFloat(line.substring(18,line.length()));
                    System.out.println(temerityLevel);
                }
                if (cpt==5){//romance
                    romanceLevel = Float.parseFloat(line.substring(17,line.length()));
                    System.out.println(romanceLevel);
                }
                if (cpt==6){//dice
                    dice = Integer.parseInt(line.substring(15,line.length()));
                    System.out.println(dice);
                }
                if (cpt==7){//perk
                    temp = line.substring(7,line.length());
                    System.out.println(temp);
                    String[] perk_total =temp.split("-");
                    for(String elem : perk_total){
                        perksList.add(elem);
                    }
                }
                cpt++;
            }
            User u = new User(name,first_name,gender,temerityLevel,romanceLevel,dice,perksList);
            if (activity_number==1){
                Intent i =new Intent(Question1.this, UserInput.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("User", u);
                i.putExtras(bundle);
                startActivity(i);
            }else if (activity_number==2){
                Intent i =new Intent(Question2.this, UserInput.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("User", u);
                i.putExtras(bundle);
                startActivity(i);
            }else if (activity_number==3){
                Intent i =new Intent(Question3.this, UserInput.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("User", u);
                i.putExtras(bundle);
                startActivity(i);
            }else if (activity_number==4){
                Intent i =new Intent(Question4.this, UserInput.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("User", u);
                i.putExtras(bundle);
                startActivity(i);
            }else if (activity_number==5){
                Intent i =new Intent(Question5.this, UserInput.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("User", u);
                i.putExtras(bundle);
                startActivity(i);
            }else if (activity_number==6){
                Intent i =new Intent(Question6.this, UserInput.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("User", u);
                i.putExtras(bundle);
                startActivity(i);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void callUserInput(android.view.View v){
        Intent i =new Intent(MainActivity.this, UserInput.class);
        startActivity(i);
    }

    public void callSuccess(android.view.View v){
        Intent i =new Intent(MainActivity.this, Success.class);
        startActivity(i);
    }

    // Listes des permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static void verifyStoragePermissions(Activity activity) {
// Vérifie si nous avons les droits d'écriture
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
// Aie, il faut les demander àl'utilisateur
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}