package com.example.p_a_t_s;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Timetabling extends AppCompatActivity {
    private Button MainMenuReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetabling);

        MainMenuReturn = findViewById(R.id.back);
        MainMenuReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });
    }

    public void openMainMenu() {
        Intent mainmenu = new Intent(this, MainMenu.class);
        startActivity(mainmenu);
    }
}