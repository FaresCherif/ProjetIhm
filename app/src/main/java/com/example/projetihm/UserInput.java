package com.example.projetihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserInput extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
    }

    public void toast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    public void back(android.view.View v){
        finish();
    }

    public boolean coherent(String name){
        Pattern notNameChar = Pattern.compile ("[!@#$%^&*'()_+=|<>?{}\\[\\]~0123456789]");
        Matcher coherentName = notNameChar.matcher(name);
        return !coherentName.find();
    }

    public void callQuestion1(android.view.View v){

        EditText firstName=findViewById(R.id.FirstNameEntreeArea);
        EditText lastName=findViewById(R.id.NameEntreeArea);
        Spinner money=findViewById(R.id.spinnerBankMoney);


        if(!firstName.getText().toString().isEmpty()&&!lastName.getText().toString().isEmpty() && coherent(firstName.getText().toString()) && coherent(lastName.getText().toString())) {
            User u=new User(firstName.getText().toString(),lastName.getText().toString());

            if(money.getSelectedItem().toString().equals(">100000")){
                u.addPerk("rich");
            }

            Intent i = new Intent(UserInput.this, Question1.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("User", u);
            i.putExtras(bundle);
            startActivity(i);
        }
        else{
            toast(v.getResources().getString(R.string.toastBadNameUserInput));
        }
    }
}