package org.example.braintraining;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AccountActivity extends Activity {

    public static final String KEY_USERNAME = "username";
    private static final String REGISTER_URL = "http://www.w1480440fyp.co.uk/getpoints.php";
    private static final String REGISTER_URL2 = "http://www.w1480440fyp.co.uk/getclassid.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        checkUser();
        String loggedinuser = getIntent().getStringExtra("Logged_User");
        TextView loggedusername = (TextView) findViewById(R.id.usernameField);
        final TextView pointsField = (TextView) findViewById(R.id.pointsField);
        final TextView classidField = (TextView) findViewById(R.id.emailField);
        username = loggedinuser;
        loggedusername.setText(loggedinuser);

        View leaderboardButton = findViewById(R.id.leaderboardButton);
        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //LEADERBOARD ACTIVITY.
                Intent myIntent = new Intent(AccountActivity.this, UnityPlayerActivity.class);
                myIntent.putExtra("Username", username); //Optional parameters
                myIntent.putExtra("ClassroomID", classidField.getText()); //Optional parameters
                myIntent.putExtra("sceneName", "AR"); //Optional parameters
                AccountActivity.this.startActivity(myIntent);

            }
        });



        View homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TASKS LIST ACTIVITY.
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("Logged_User", username);
                startActivity(intent);

            }
        });


        View editclassButton = findViewById(R.id.editclassButton);
        editclassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DETAILS ACTIVITY.
                Intent intent = new Intent(getBaseContext(), EditClassroomActivity.class);
                intent.putExtra("Logged_User", username);
                startActivity(intent);
            }
        });


        View editemailButton = findViewById(R.id.editemailbutton);
        editemailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DETAILS ACTIVITY.

                Intent intent = new Intent(getBaseContext(), EditEmailActivity.class);
                intent.putExtra("Logged_User", username);
                startActivity(intent);

            }
        });







    }

    String userpoints = "0";
    String username = "";
String classroomid = "";

    private void checkUser() {

        final TextView pointsField = (TextView) findViewById(R.id.pointsField);
        final TextView classidField = (TextView) findViewById(R.id.emailField);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pointsField.setText(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AccountActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME, username);
                return params;
            }


        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, REGISTER_URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        classidField.setText(response);
classroomid = response;

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AccountActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME, username);
                return params;
            }


        };
        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        requestQueue2.add(stringRequest2);
    }



}








