package com.example.p_a_t_s;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainMenuStaff extends AppCompatActivity {
    private ImageButton imageButton;
    private ImageButton stafftimetable;
    private ImageButton classattendance;
    private ImageButton genqr;
    private ImageButton register;
    private Button btLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_menu_staff);

        genqr = findViewById(R.id.genqr);
        genqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQRGen();
            }
        });

        classattendance = findViewById(R.id.classattendance);
        classattendance.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openClassAttendance();
            }
        });

        imageButton = findViewById(R.id.genupdate);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openGenerateUpdate();
            }
        });
        stafftimetable = findViewById(R.id.stafftimetable);
        stafftimetable.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openTimetable();
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

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });
    }
    public void openQRGen(){
        Intent qrgenintent = new Intent (this, generate_qrcode.class);
        startActivity(qrgenintent);
    }
    public void openClassAttendance(){
        Intent classintent = new Intent (this, classAttendance.class);
        startActivity(classintent);
    }
    public void openGenerateUpdate(){
        Intent genintent = new Intent(this, generate_update.class);
        startActivity(genintent);
    }
    public void openTimetable() {
        Intent gettimetable = new Intent(this, staff_timetable.class);
        startActivity(gettimetable);
    }
    public void openRegister(){
        Intent openR = new Intent (this, registerStudent.class);
        startActivity(openR);
    }

    public void logout(){
        SharedPrefManager.getInstance(getApplicationContext()).logout();
    }
}
