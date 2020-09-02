package org.example.braintraining;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends FragmentActivity implements OnClickListener {


    String Username = "";
    String Fullname = "";
    String ClassroomID = "";
    String Password = "";
    String Email = "";
    private static final String REGISTER_URL = "http://www.w1480440fyp.co.uk/addstudents.php";
    private static final String EMAIL_URL = "http://www.w1480440fyp.co.uk/sendemail.php";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_FULLNAME = "fullname";
    public static final String KEY_CLASSROOMID = "classroomid";

    private Button SubmitButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        SubmitButton = (Button) findViewById(R.id.signupButton);

        SubmitButton.setOnClickListener(this);

    }


    private void registerUser() {
        final EditText usernameField = (EditText) findViewById(R.id.usernameField);
        Username = usernameField.getText().toString().trim();
        EditText fullnameField = (EditText) findViewById(R.id.fullnameField);
        Fullname = fullnameField.getText().toString().trim();
        EditText emailField = (EditText) findViewById(R.id.emailField);
        Email = emailField.getText().toString().trim();
        EditText passwordField = (EditText) findViewById(R.id.passwordField);
        Password = passwordField.getText().toString().trim();
        EditText classidField = (EditText) findViewById(R.id.classidField);
        ClassroomID = classidField.getText().toString().trim();

        if (Username.equals("")) {
            usernameField.setError("Please enter a suitable username.");


        } else if (Password.equals("")) {
            passwordField.setError("Please enter a suitable password.");


        } else if (Fullname.equals("")) {
            fullnameField.setError("Please enter a suitable name.");

        } else if (Email.equals("")) {
            emailField.setError("Please enter a suitable email address.");

        } else if (isEmailValid(Email) == false) {
            emailField.setError("Please enter a suitable email address.");
        }
            else if (ClassroomID.equals("")) {
            classidField.setError("Please enter a suitable classroom ID.");

        }

        else if (Username.length() < 8) {
            usernameField.setError("Please enter more than 7 characters.");
        }
        else if (Password.length() < 5) {
            passwordField.setError("Please enter more than 4 characters.");
        }
        else if (Fullname.length() < 5) {
            fullnameField.setError("Please enter more than 4 characters.");
        }
        else if (ClassroomID.length() < 5) {
            classidField.setError("Please enter more than 4 characters.");
        }
        else if (Username.length() > 24) {
            usernameField.setError("Please enter less than 25 characters.");
        }
        else if (Password.length() > 24) {
            passwordField.setError("Please enter less than 25 characters.");
        }
        else if (Fullname.length() > 39) {
            fullnameField.setError("Please enter less than 40 characters.");
        }
        else if (ClassroomID.length() > 14) {
            classidField.setError("Please enter less than 15 characters.");
        }
        else {



            StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("Successfully Registered")) {
                                String loggedusername = "Login";
                                Toast.makeText(getApplicationContext(), "Registered",
                                        Toast.LENGTH_SHORT).show();
                                sendUserEmail();
                                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                intent.putExtra("Logged_User2", loggedusername);
                                finish();
                                startActivity(intent);
                            }
                            else if (response.equals("name already exists")){
                                usernameField.setError(usernameField.getText().toString() + " exists as a name already, choose another username.");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_USERNAME, Username);
                    params.put(KEY_PASSWORD, Password);
                    params.put(KEY_FULLNAME, Fullname);
                    params.put(KEY_EMAIL, Email);
                    params.put(KEY_CLASSROOMID, ClassroomID);
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
            registerUser();
        }
    }

    private void sendUserEmail() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EMAIL_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {

                        }
                        else if (response.equals("failed")){

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_FULLNAME, Fullname);
                params.put(KEY_EMAIL, Email);
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    //REFERENCE for below email validation: http://www.technotalkative.com/android-validate-email-address/
    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }
    }




