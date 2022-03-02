package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private final String TAG="Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);

    }

    public void load(android.view.View v){

        Log.e(TAG,"load");
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File actual_file = new File("test");//TODO to take out

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
            Log.e(TAG,"file not found");
        }
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            int cpt=1;
            String name ="";
            String first_name="";
            String gender="";
            int dice=0;
            float temerityLevel=0;
            float romanceLevel=0;
            String temp;
            ArrayList<String> perksList = new ArrayList<>();

            while ( (line = rd.readLine()) != null ){
                if (cpt==1){//name
                    name = line.substring(7);
                    System.out.println(name);
                }
                if (cpt==2){//firstname
                    first_name = line.substring(9);
                    System.out.println(first_name);
                }
                if (cpt==3){//genre
                    gender = line.substring(8);
                    System.out.println(gender);
                }
                if (cpt==4){//temerity
                    temerityLevel = Float.parseFloat(line.substring(18));
                    System.out.println(temerityLevel);
                }
                if (cpt==5){//romance
                    romanceLevel = Float.parseFloat(line.substring(17));
                    System.out.println(romanceLevel);
                }
                if (cpt==6){//dice
                    dice = Integer.parseInt(line.substring(15));
                    System.out.println(dice);
                }
                if (cpt==7){//perk
                    temp = line.substring(7);
                    System.out.println(temp);
                    String[] perk_total =temp.split("-");
                    Collections.addAll(perksList,perk_total);
                }
                cpt++;
            }
            Log.e(TAG,name+" "+first_name+" "+activity_number);
            User u = new User(name,first_name,gender,temerityLevel,romanceLevel,dice,perksList);
            if(activity_number==0){
                Intent i =new Intent(MainActivity.this, UserInput.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("User", u);
                i.putExtras(bundle);
                startActivity(i);
            }else if (activity_number==1){
                Intent i =new Intent(MainActivity.this, Question2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("User", u);
                i.putExtras(bundle);
                startActivity(i);
            }else if (activity_number==2){
                Intent i =new Intent(MainActivity.this, Question3.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("User", u);
                i.putExtras(bundle);
                startActivity(i);
            }else if (activity_number==3){
                Intent i =new Intent(MainActivity.this, Question4.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("User", u);
                i.putExtras(bundle);
                startActivity(i);
            }else if (activity_number==4){
                Intent i =new Intent(MainActivity.this, Question5.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("User", u);
                i.putExtras(bundle);
                startActivity(i);
            }else if (activity_number==5){
                Intent i =new Intent(MainActivity.this, Question6.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("User", u);
                i.putExtras(bundle);
                startActivity(i);
            }


        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        Log.e(TAG,"fin load");

    }

    public void callUserInput(android.view.View v){
        Intent i =new Intent(MainActivity.this, UserInput.class);
        startActivity(i);
    }

    public void callSuccess(android.view.View v){
        Intent i =new Intent(MainActivity.this, Success.class);
        startActivity(i);
    }

    // permission lists
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private final static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static void verifyStoragePermissions(Activity activity) {
// check writing write
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
// ask user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}