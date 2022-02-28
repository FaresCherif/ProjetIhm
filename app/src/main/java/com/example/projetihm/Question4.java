package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageButton;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;

public class Question4 extends AppCompatActivity {
    private ImageButton barb;
    private ImageButton pal;
    private ImageButton magi;
    private ImageButton bard;
    private User u;
    private User savedU;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle dataSent = getIntent().getExtras();
        u = (User) dataSent.getSerializable("User");
        savedU = new User(u);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);

        barb = findViewById(R.id.barbarianImage);
        magi = findViewById(R.id.wizardImage);
        bard = findViewById(R.id.bardImage);
        pal = findViewById(R.id.paladinImage);

        new DownloadImage().execute("https://www.aidedd.org/assets/regles/classes/barbare.jpg", "barb");
        new DownloadImage().execute("https://www.aidedd.org/assets/regles/classes/magicien.jpg", "magi");
        new DownloadImage().execute("https://www.aidedd.org/assets/regles/classes/barde.jpg", "bard");
        new DownloadImage().execute("https://www.aidedd.org/assets/regles/classes/paladin.jpg", "pal");

    }

    @Override
    public void onResume(){
        super.onResume();
        u=new User(savedU);
    }

    public void write_historic_in_file() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File save_user = new File(folder, "activity4.txt");
        try (FileOutputStream fos = new FileOutputStream(save_user)) {
            PrintStream ps = new PrintStream(fos);
            ps.println("Name : " + u.getName());
            ps.println("Prenom : " + u.getFirstname());
            ps.println("Genre : " + u.getGender());
            ps.println("Valeur temerite : " + u.getTemerityLevel());
            ps.println("Valeur romance : " + u.getRomanceLevel());
            ps.println("Valeur du dé : " + u.getDice());
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

    public void chooseClass(android.view.View v){

        if(bard.isPressed()){
            u.addRomance(1);
        }else if(magi.isPressed()){
            u.addTemerity(-1);
        }else if(barb.isPressed()){
            u.addTemerity(3);
        }else if(pal.isPressed()){
            u.addTemerity(1);
            u.addRomance(1);
        }

        write_historic_in_file();
        Intent i =new Intent(Question4.this, Question5.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", u);
        i.putExtras(bundle);
        startActivity(i);
    }


    private void setImage(Drawable drawable,String DndClass)
    {
        switch (DndClass){
            case ("barb") :
                barb.setImageDrawable(drawable);
                break;
            case ("pal") :
                pal.setImageDrawable(drawable);
                break;
            case ("bard"):
                bard.setImageDrawable(drawable);
                break;
            case ("magi") :
                magi.setImageDrawable(drawable);
                break;
        }

    }

    public void back(android.view.View v){
        finish();
    }

    @SuppressLint("StaticFieldLeak")
    public class DownloadImage extends AsyncTask<String, Integer, Drawable> {

        private String DndClass;

        @Override
        protected Drawable doInBackground(String... arg0) {
            return downloadImage(arg0[0],arg0[1]);
        }

        protected void onPostExecute(Drawable image)
        {
            setImage(image,DndClass);
        }


        private Drawable downloadImage(String _url,String dndC)
        {
            DndClass=dndC;
            URL url;
            InputStream in;
            BufferedInputStream buf;

            try {
                url = new URL(_url);
                in = url.openStream();

                buf = new BufferedInputStream(in);

                Bitmap bMap = BitmapFactory.decodeStream(buf);
                if (in != null) {
                    in.close();
                }


                return new BitmapDrawable(bMap);

            } catch (Exception e) {
                Log.e("Error reading file", e.toString());
            }

            return null;
        }

    }

}