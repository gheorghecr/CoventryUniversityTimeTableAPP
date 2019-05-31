package com.example.p_a_t_s;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class qrCode extends AppCompatActivity {
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStaffMenu();
            }
        });
    }

    public void getStaffMenu() {
        Intent staffintent = new Intent(this, MainMenuStaff.class);
        startActivity(staffintent);
    }
}
