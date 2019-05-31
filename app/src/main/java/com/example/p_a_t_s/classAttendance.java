package com.example.p_a_t_s;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class classAttendance extends AppCompatActivity {
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_attendance);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openStaffMenu();
            }

        });
    }
    public void openStaffMenu(){
        Intent staffintent = new Intent (this, MainMenuStaff.class);
        startActivity(staffintent);
    }
}
