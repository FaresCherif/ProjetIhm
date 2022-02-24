package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Success extends AppCompatActivity {

    private TextView succes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        succes = (TextView)findViewById(R.id.listeSucces);

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File save_succes = new File(folder, "succes.txt");

        FileInputStream is = null;
        try {
            is = new FileInputStream(save_succes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String a_ecrire="";
        int nb_succes=0;
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String line;


            while ( (line = rd.readLine()) != null ){
                a_ecrire+=line+" ";
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
                a_ecrire+="\n";
            }
            a_ecrire+=nb_succes+"/5";
            succes.setText(a_ecrire);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException a){
            a_ecrire+=nb_succes+"/5";
            succes.setText(a_ecrire);
        }

    }

    public void back(android.view.View v){
        finish();
    }

}