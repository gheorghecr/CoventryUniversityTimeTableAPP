package com.example.p_a_t_s;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class generate_update extends AppCompatActivity {
    private Button back;
    private Button updatenow;
    private Button updatelater;
    private TextView AlertLater;
    private TextView AlertNow;
    private EditText editText;
    private String st;
    public static final String EXTRA_TEXT = "com.example.p_a_t_s.EXTRA_TEXT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_generate_update);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openStaffMenu();
            }
        });

        updatenow = findViewById(R.id.updatenow);
        AlertNow = findViewById(R.id.AlertNow);
        updatenow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*AlertDialog.Builder builder = new AlertDialog.Builder(generate_update.this);

                builder.setCancelable(true);
                builder.setTitle("Updated Now");
                builder.setMessage("You have sent this update");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertNow.setVisibility(View.VISIBLE);
                    }
                });
                builder.show();*/
                sendUpdate();
            }
        });

        updatelater = findViewById(R.id.updatelater);
        AlertLater = findViewById(R.id.AlertLater);
        updatelater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(generate_update.this);

                builder.setCancelable(true);
                builder.setTitle("Set update to later");
                builder.setMessage("You have sent this update later");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertLater.setVisibility(View.VISIBLE);
                    }
                });
                builder.show();
            }
        });

}

    public void openStaffMenu(){
        Intent staffintent = new Intent (this, MainMenuStaff.class);
        startActivity(staffintent);
    }
    public void sendUpdate(){
        editText = findViewById(R.id.editText);
        String text = editText.getText().toString();
        Intent intent = new Intent(this, Updates.class);
        intent.putExtra(EXTRA_TEXT, text);
        startActivity(intent);
    }
}
