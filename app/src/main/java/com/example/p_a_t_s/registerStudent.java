package com.example.p_a_t_s;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registerStudent extends AppCompatActivity {

    Button register;
    EditText usernameInput;
    EditText passwordInput;
    EditText stageInput;
    EditText groupInput;
    EditText courseIDInput;
    EditText emailInput;
    EditText staffInput;
    private Integer idvar;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);


        //when login button is clicked
        findViewById(R.id.registerBT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        // get the editText, username and password
        usernameInput = findViewById(R.id.StudentName);
        passwordInput = findViewById(R.id.PasswordInput);
        stageInput = findViewById(R.id.StudentStage);
        groupInput = findViewById(R.id.StudentGroup);
        courseIDInput = findViewById(R.id.StudentCourseID);
        emailInput = findViewById(R.id.StudentEmail);
        staffInput = findViewById(R.id.Staff);

    }
    private void register(){
        //

        final String username = usernameInput.getText().toString().trim();
        final String password = passwordInput.getText().toString().trim();
        final String email = emailInput.getText().toString().trim();
        final String courseID = courseIDInput.getText().toString().trim();
        final String group = groupInput.getText().toString().trim();
        final String stage = stageInput.getText().toString().trim();
        final String staff = staffInput.getText().toString().trim();

        ////validation of inputs, will not let to advance without them
        /*if(TextUtils.isEmpty(username)){
            usernameInput.setError("Username Missing ");
            usernameInput.requestFocus();
            return;
        }
        else if(TextUtils.isEmpty(password)){
            passwordInput.setError("Password Missing ");
            passwordInput.requestFocus();
            return;
        }
        else if(TextUtils.isEmpty(email)){
            emailInput.setError("Email Missing ");
            emailInput.requestFocus();
            return;
        }
        else if(TextUtils.isEmpty(courseID)){
            courseIDInput.setError("Course ID Missing ");
            courseIDInput.requestFocus();
            return;
        }
        else if(TextUtils.isEmpty(group)){
            groupInput.setError("Group Missing ");
            groupInput.requestFocus();
            return;
        }
        else if(TextUtils.isEmpty(stage)){
            stageInput.setError("Stage Missing ");
            stageInput.requestFocus();
            return;
        }*/


        //shows a progres dialog to give feedback for user
        pDialog = new ProgressDialog(registerStudent.this);
        pDialog.setMessage("Registing new Student. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        //string with the URL for the online server with the login
        final String URL = "https://realpats.000webhostapp.com/registation.php?apicall=signup";
        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user array from the response (php files in the server)
                                JSONObject userJson = obj.getJSONObject("user");

                                //storing the id of the new student
                                idvar =   userJson.getInt("id");


                                //Open the MAINMENU activity
                                //finish();
                                pDialog.dismiss(); //close dialog
                                startActivity(new Intent(getApplicationContext(), MainMenu.class));
                            } else {
                                pDialog.dismiss();//close dialog
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            pDialog.dismiss();//close dialog
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();//close dialog
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            //parametres used to login, this is how they send via POST method to the file
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", username);
                params.put("password", password);
                params.put("email", email);
                params.put("group", group);
                params.put("stage", stage);
                params.put("courseID", courseID);
                params.put("staff", staff);

                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
