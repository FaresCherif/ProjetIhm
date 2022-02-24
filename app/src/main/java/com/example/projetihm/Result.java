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
import java.io.UnsupportedEncodingException;

public class Result extends AppCompatActivity {
    private User u;
    private TextView fullName;
    private ImageView image;
    private TextView texteResult;
    boolean rich = false;
    boolean perfect = false;
    boolean balanced = false;
    boolean temerity = false;
    boolean romance = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle transmis = getIntent().getExtras();
        u = (User) transmis.getSerializable("User");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        fullName = findViewById(R.id.FullName);
        image= findViewById(R.id.ImageResult);
        texteResult=findViewById(R.id.ResultText);

        String full = u.getFirstname()+" "+u.getName();
        fullName.setText(full);
        toast(u.getRomanceLevel()+" "+u.getTemerityLevel());


        if(u.containsPerk("rich")){
            image.setImageResource(R.drawable.richlover);
            texteResult.setText(R.string.richLover);
            rich = true;
        }
        else if(u.getTemerityLevel()>5 && u.getRomanceLevel() > 5){
            image.setImageResource(R.drawable.perfectlover);
            texteResult.setText(R.string.perfectLover);
            perfect = true;
        }
        else if(between(u.getTemerityLevel(),0,5) && between(u.getRomanceLevel(),0,5)){
            image.setImageResource(R.drawable.balancedlover);
            texteResult.setText(R.string.balancedLover);
            balanced = true;
        }
        else if(u.getTemerityLevel()>10 && u.getRomanceLevel() < 5){
            image.setImageResource(R.drawable.temerritylover);
            texteResult.setText(R.string.temerityLover);
            temerity = true;
        }
        else if(u.getTemerityLevel()<5 && u.getRomanceLevel() > 10){
            image.setImageResource(R.drawable.romancelover);
            texteResult.setText(R.string.romanceLover);
            romance = true;
        }

        write_historic_in_file();
    }

    public boolean between(float valLooked,float min,float max){
        if(valLooked>=min && valLooked<=max){
            return true;
        }
        return false;
    }

    public void toast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    public void closeApp(android.view.View v){
        finishAffinity();
    }

    public void write_historic_in_file() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File save_user = new File(folder, u.getFirstname()+"-"+u.getName()+".txt");
        try (FileOutputStream fos = new FileOutputStream(save_user)) {
            PrintStream ps = new PrintStream(fos);
            ps.println("Name : "+u.getName());
            ps.println("Prenom : "+u.getFirstname());
            ps.println("Genre : "+u.getGender());
            ps.println("Valeur temerite : "+u.getTemerityLevel());
            ps.println("Valeur romance : "+u.getRomanceLevel());
            ps.println("Valeur du dé : "+u.getDice());
            String array ="";
            for(String elem : u.getPerk()){
                array+=elem;
            }
            ps.println("Perk : "+array);
            ps.close();
        } catch (FileNotFoundException e) {
            //Log.e(APP_TAG,"File not found",e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            //Log.e(APP_TAG,"Error I/O",e);
        }

        File save_succes = new File(folder, "succes.txt");

        FileInputStream is = null;
        try {
            is = new FileInputStream(save_succes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String line;
            while ( (line = rd.readLine()) != null ){
                if(line.matches("Succes obtenu : temerityLover") && temerity==true){ //--regex of what to search--
                    //--do something---
                    System.out.println("aaaa");
                    return; //--if not want to search further--
                }else if (line.matches("Succes obtenu : richLover") && rich==true){
                    System.out.println("aaaa");
                    return; //--if not want to search further--
                }else if (line.matches("Succes obtenu : perfectLover") && perfect==true){
                    System.out.println("aaaa");
                    return; //--if not want to search further--
                }else if (line.matches("Succes obtenu : balancedLover") && balanced==true){
                    System.out.println("aaaa");
                    return; //--if not want to search further--
                }else if (line.matches("Succes obtenu : romanceLover") && romance==true){
                    System.out.println("aaaa");
                    return; //--if not want to search further--
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException e) {
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
        } catch (FileNotFoundException e) {
            //Log.e(APP_TAG,"File not found",e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            //Log.e(APP_TAG,"Error I/O",e);
        }
    }
}