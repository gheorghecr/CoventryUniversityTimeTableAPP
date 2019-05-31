package com.example.p_a_t_s;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity {
    Button loginBT;
    EditText usernameInput;
    EditText passwordInput;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainMenu.class));
        }

        // get the editText, username and password
        usernameInput = findViewById(R.id.UsernameInput);
        passwordInput = findViewById(R.id.PasswordInput);

        //when login button is clicked
        findViewById(R.id.loginBT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

        private void login(){
        //
        final String username = usernameInput.getText().toString();
        final String password = passwordInput.getText().toString();

        ////validation of inputs, will not let to advance without them
         if(TextUtils.isEmpty(username)){
             usernameInput.setError("Username Missing ");
             usernameInput.requestFocus();
             return;
         }
         if(TextUtils.isEmpty(password)){
            passwordInput.setError("Password Missing ");
            passwordInput.requestFocus();
            return;
         }

         //shows a progres dialog to give feedback for user
         pDialog = new ProgressDialog(MainActivity.this);
         pDialog.setMessage("Logging In. Please wait...");
         pDialog.setIndeterminate(false);
         pDialog.setCancelable(false);
         pDialog.show();
         //string with the URL for the online server with the login
         final String URL = "https://realpats.000webhostapp.com/registation.php?apicall=login";
            //if everything is fine
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {

                                //Subscribe to topics
                                FirebaseMessaging.getInstance().subscribeToTopic("all")
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                String msg = "Subscribed";
                                                if(!task.isSuccessful())
                                                {
                                                    msg = "Subscribe failed";
                                                }
                                                Log.d("MainActivity", msg);
                                            }
                                        });
                                //converting response to json object
                                JSONObject obj = new JSONObject(response);

                                //if no error in response
                                if (!obj.getBoolean("error")) {
                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                    //getting the user array from the response (php files in the server)
                                    JSONObject userJson = obj.getJSONObject("user");

                                    //creating a new user object, to store what we need from the database
                                    User user = new User(
                                            userJson.getInt("id"),
                                            userJson.getString("username"),
                                            userJson.getString("email"),
                                            userJson.getString("group"),
                                            userJson.getString("staff")

                                    );

                                    //storing the user in shared preferences
                                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                    //Open the MAINMENU activity
                                    finish();
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
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }
            };

            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }
}



