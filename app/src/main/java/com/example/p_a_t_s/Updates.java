package com.example.p_a_t_s;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Updates extends AppCompatActivity {
    private Button MainMenuReturn;
    private TextView tv;
    private String string;
    ArrayList<String> addArray = new ArrayList<String>();
    private ListView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);
        show = findViewById(R.id.show);
        MainMenuReturn = findViewById(R.id.back);
        MainMenuReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });

        Intent intent = getIntent();
        String text = intent.getStringExtra(generate_update.EXTRA_TEXT);
        tv = findViewById(R.id.textView3);
        tv.setText(text);
        /*addArray.add(text);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Updates.this, android.R.layout.simple_list_item_1, addArray);
        show.setAdapter(adapter);
        ((TextView)findViewById(R.id.textView3)).setText(" ");*/
    }

    public void openMainMenu() {
        Intent mainmenu = new Intent(this, MainMenu.class);
        startActivity(mainmenu);
    }
}