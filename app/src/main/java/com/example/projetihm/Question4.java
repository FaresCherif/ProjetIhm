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
    private User u;
    private User savedU;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle transmis = getIntent().getExtras();
        u = (User) transmis.getSerializable("User");
        savedU = new User(u);

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

    @Override
    public void onResume(){
        super.onResume();
        u=new User(savedU);
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
        Intent i =new Intent(Question4.this, Question5.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", u);
        i.putExtras(bundle);
        startActivity(i);
    }


    private void setImage(Drawable drawable,String classe)
    {
        switch (classe){
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


                return new BitmapDrawable(bMap);

            } catch (Exception e) {
                Log.e("Error reading file", e.toString());
            }

            return null;
        }

    }

}