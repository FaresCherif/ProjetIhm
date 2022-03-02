package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        TextView succes = (TextView) findViewById(R.id.listeSucces);

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File save_succes = new File(folder, "succes.txt");

        FileInputStream is = null;
        try {
            is = new FileInputStream(save_succes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder a_ecrire= new StringBuilder();
        int nb_succes=0;
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;


            while ( (line = rd.readLine()) != null ){
                a_ecrire.append(line).append(" ");
                if(line.matches("Succes obtenu : temerityLover")){ //--regex of what to search--
                    nb_succes++;
                }
                if (line.matches("Succes obtenu : richLover")){
                    nb_succes++;
                }
                if (line.matches("Succes obtenu : perfectLover")){
                    nb_succes++;
                }
                if (line.matches("Succes obtenu : balancedLover")){
                    nb_succes++;
                }
                if (line.matches("Succes obtenu : romanceLover")){
                    nb_succes++;
                }
                a_ecrire.append("\n");
            }
            SharedPreferences settings = getSharedPreferences("projet_IHM", MODE_PRIVATE);
            int total_number_succes=0;
            total_number_succes = settings.getInt("Number of succes",total_number_succes);

            System.out.println("succ"+total_number_succes);
            a_ecrire.append(total_number_succes).append("/5");//nb_succes
            succes.setText(a_ecrire.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException a){
            a_ecrire.append(nb_succes).append("/5");
            succes.setText(a_ecrire.toString());
        }

    }

    public void back(android.view.View v){
        finish();
    }

}