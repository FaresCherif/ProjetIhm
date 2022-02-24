package com.example.projetihm;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String firstname;
    private String name;
    private String gender;
    private int dice;
    private float temerityLevel=0;
    private float romanceLevel=0;
    private ArrayList<String> perksList;

    public User(String f,String n){
        this.firstname=f;
        this.name=n;
        this.dice = 0;
        perksList=new ArrayList<>();
    }

    public User(User u){
        this.firstname=u.firstname;
        this.name=u.name;
        this.gender=u.gender;
        this.temerityLevel=u.temerityLevel;
        this.romanceLevel=u.romanceLevel;
        this.dice = u.dice;
        this.perksList=new ArrayList<>();
        this.perksList.addAll(u.perksList);
    }

    public User(String f,String n,String g,float t,float r,int d,ArrayList<String> p){
        this.firstname=f;
        this.name=n;
        this.gender=g;
        this.temerityLevel=t;
        this.romanceLevel=r;
        this.dice = d;
        this.perksList=p;
    }

    public void addTemerity(float t){
        this.temerityLevel+=t;
    }

    public void addRomance(float r){
        this.romanceLevel+=r;
    }

    public void setGender(String g){
        this.gender=g;
    }

    public void setDice(int val){
        this.dice=val;
    }

    public int getDice(){return this.dice;}

    public String getName(){
        return this.name;
    }

    public String getFirstname(){
        return this.firstname;
    }

    public ArrayList<String> getPerk(){
        return this.perksList;
    }

    public String getGender(){
        return this.gender;
    }

    public float getTemerityLevel(){
        return this.temerityLevel;
    }

    public float getRomanceLevel(){
        return this.romanceLevel;
    }

    public void addPerk(String p){
        perksList.add(p);
    }

    public boolean containsPerk(String p){
        return perksList.contains(p);
    }

}
