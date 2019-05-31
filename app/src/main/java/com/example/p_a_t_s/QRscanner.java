package com.example.p_a_t_s;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QRscanner extends AppCompatActivity {
    private Button QRReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        QRReturn = findViewById(R.id.QRReturn);
        QRReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAttendance();
            }
        });
    }

    public void openAttendance() {
        Intent attendance = new Intent(this, Attendance.class);
        startActivity(attendance);
    }
}