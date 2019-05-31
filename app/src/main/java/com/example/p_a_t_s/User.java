package com.example.p_a_t_s;

import java.util.Date;

// this is to keep track on the user details, we will need this when moving different screens
//to know which student are we dealing with
public class User {
    private int id;
    private String email;
    private String name;
    private  String staff;
    private  String group;


    public User(int id, String name, String email, String group, String staff) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.group = group;
        this.staff = staff;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStaff (String staff){
        this.staff = staff;
    }

    public String getStaff () {
        return staff;
    }
    public void setGroup (String group){
        this.group = group;
    }

    public String getGroup () {
        return group;
    }

}