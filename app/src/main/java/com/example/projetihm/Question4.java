package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.URL;

public class Question4 extends AppCompatActivity {
    private String DndClass="";
    private ImageButton barb;
    private ImageButton pal;
    private ImageButton magi;
    private ImageButton bard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);

        barb = findViewById(R.id.barbarianImage);
        magi = findViewById(R.id.wizardImage);
        bard = findViewById(R.id.bardImage);
        pal = findViewById(R.id.paladinImage);

        //Drawable image_barbare = LoadImageFromWebOperations("https://www.aidedd.org/regles/classes/barbare/");
        //barb.setImageBitmap(fetchImage("https://www.aidedd.org/assets/regles/classes/barbare.jpg"));
        //barb.setBackground(image_barbare);

        new DownloadImage().execute("https://www.aidedd.org/assets/regles/classes/barbare.jpg", "barb");
        new DownloadImage().execute("https://www.aidedd.org/assets/regles/classes/magicien.jpg", "magi");
        new DownloadImage().execute("https://www.aidedd.org/assets/regles/classes/barde.jpg", "bard");
        new DownloadImage().execute("https://www.aidedd.org/assets/regles/classes/paladin.jpg", "pal");

    }

    public void chooseClass(android.view.View v){
        Intent i =new Intent(Question4.this, Question5.class);
        startActivity(i);
    }


    private void setImage(Drawable drawable,String classe)
    {
        if (classe.equals("barb")){
            barb.setBackgroundDrawable(drawable);
        }else if(classe.equals("pal")){
            pal.setBackgroundDrawable(drawable);
        }else if(classe.equals("bard")){
            bard.setBackgroundDrawable(drawable);
        }else if(classe.equals("magi")){
            magi.setBackgroundDrawable(drawable);
        }

    }



    public class DownloadImage extends AsyncTask<String, Integer, Drawable> {

        private String classe;

        @Override
        protected Drawable doInBackground(String... arg0) {
            return downloadImage(arg0[0],arg0[1]);
        }

        protected void onPostExecute(Drawable image)
        {
            setImage(image,classe);
        }


        private Drawable downloadImage(String _url,String clas)
        {
            classe=clas;
            URL url;
            BufferedOutputStream out;
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
                if (buf != null) {
                    buf.close();
                }

                return new BitmapDrawable(bMap);

            } catch (Exception e) {
                Log.e("Error reading file", e.toString());
            }

            return null;
        }

    }

}