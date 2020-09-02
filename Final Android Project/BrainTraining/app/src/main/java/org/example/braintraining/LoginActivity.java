package org.example.braintraining;


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;



public class LoginActivity extends FragmentActivity implements OnClickListener {

    String Username = "";
    String Password = "";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    private static final String REGISTER_URL = "http://www.w1480440fyp.co.uk/checklogin.php";


    private Button SubmitButton;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        check_login();
        setContentView(R.layout.activity_login);


        SubmitButton = (Button) findViewById(R.id.signin_button);

        SubmitButton.setOnClickListener(this);


        View signupButton = findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                finish();
                startActivity(intent);

            }
        });

    }


    private void check_login() {
        String loggedinuser = getIntent().getStringExtra("Logged_User2");

        if (!loggedinuser.equals("Login")) {
            //goes to user accounts page.
            Intent intent = new Intent(getBaseContext(), AccountActivity.class);
            intent.putExtra("Logged_User", loggedinuser);
            finish();
            startActivity(intent);
        }

    }


    private void checkUser() {
        final EditText usernameField = (EditText) findViewById(R.id.loginname);
        Username = usernameField.getText().toString().trim();

        EditText passwordField = (EditText) findViewById(R.id.loginpass);
        Password = passwordField.getText().toString().trim();


        if (Username.equals("")) {
            usernameField.setError("Please enter a suitable username.");


        } else if (Password.equals("")) {
            passwordField.setError("Please enter a suitable password.");

        }  else {


            StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("failed")) {
                                Toast.makeText(getApplicationContext(), "Failed to login",
                                        Toast.LENGTH_SHORT).show();



                            }
                            else {
                                String loggedusername = Username;
                                Toast.makeText(getApplicationContext(), "Logged in as " + Username,
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                intent.putExtra("Logged_User", loggedusername);
                                intent.putExtra("Class_ID", response);
                                finish();
                                startActivity(intent);                 }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_USERNAME, Username);
                    params.put(KEY_PASSWORD, Password);
                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
    @Override
    public void onClick(View v) {
        if (v == SubmitButton) {
            checkUser();
        }
    }


}




