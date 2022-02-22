package com.example.projetihm;

public class User {
    private String firstname;
    private String name;
    private String gender;
    private float temerityLevel=0;
    private float romanceLevel=0;

    public User(String f,String n){
        this.firstname=f;
        this.name=n;
    }

    public void addTemerity(float t){
        this.temerityLevel+=t;
    }

    public void addRomance(float r){
        this.temerityLevel+=r;
    }

    public void setGender(String g){
        this.gender=g;
    }

    public String getName(){
        return this.name;
    }

    public String getFirstname(){
        return this.firstname;
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
}
