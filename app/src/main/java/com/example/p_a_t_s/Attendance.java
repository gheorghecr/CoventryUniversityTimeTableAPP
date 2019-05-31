package com.example.p_a_t_s;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class Attendance extends AppCompatActivity {
    private Button MainMenuReturn;
    private Button QRnavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        MainMenuReturn = findViewById(R.id.back);
        MainMenuReturn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMainMenu();
            }
        });
        QRnavigation = findViewById(R.id.QRnavigation);
        QRnavigation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openQrScanner();
            }
        });

    }
    public void openMainMenu(){
        Intent mainmenu = new Intent(this, MainMenu.class);
        startActivity(mainmenu);
    }
    public void openQrScanner(){
        Intent qrscanner = new Intent(this, QRscanner.class);
        startActivity(qrscanner);
    }
}
