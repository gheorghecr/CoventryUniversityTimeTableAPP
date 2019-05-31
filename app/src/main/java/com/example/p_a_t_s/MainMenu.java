package com.example.p_a_t_s;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    private ImageButton Attendance;
    private ImageButton Timetabling;
    private ImageButton Updates;
    private ImageButton CampusMap;
    private Button btLogout;
    private String staff;
    private Integer ssid;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        // checks if the user is logged in either redirects to the login page
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            User user = SharedPrefManager.getInstance(this).getUser();
            staff = user.getStaff(); // get the variable to know if it is staff or not
            ssid = user.getId(); // get the Student ID
        }
        else{
            Intent loginPG = new Intent ( this, MainActivity.class);
            startActivity(loginPG);
            finish();
        }

        if(staff.contentEquals("1")){
            openStaffMenu();
        }

        Attendance = findViewById(R.id.Attendance);
        Attendance.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openAttendance();
            }
        });
        Timetabling = findViewById(R.id.Timetabling);
        Timetabling.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openTimetabling();
            }
        });
        Updates = findViewById(R.id.Updates);
        Updates.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openUpdates();
            }
        });
        CampusMap = findViewById(R.id.CampusMap);
        CampusMap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCampusMap();
            }
        });
        //log out button
        btLogout = findViewById(R.id.btLogOut);

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    public void openAttendance() {
        Intent attintent = new Intent(this, Attendance.class);
        startActivity(attintent);
    }
    public void openTimetabling() {
        Intent timeintent = new Intent (this, Timetabling.class);
        startActivity(timeintent);
    }
    public void openUpdates() {
        Intent updintent = new Intent ( this, Updates.class);
        startActivity(updintent);
    }
    public void openStaffMenu() {
        Intent staffintent = new Intent ( this, MainMenuStaff.class);
        startActivity(staffintent);
    }
    public void openCampusMap(){
        Intent campusMap = new Intent (this, CampusMap.class);
        startActivity(campusMap);
    }
    public void logout(){
        SharedPrefManager.getInstance(getApplicationContext()).logout();
    }
}
