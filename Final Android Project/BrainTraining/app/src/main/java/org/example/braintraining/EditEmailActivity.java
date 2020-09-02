package org.example.braintraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Numan2 on 22/03/2017.
 */

public class EditEmailActivity extends FragmentActivity implements View.OnClickListener {

    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    private static final String REGISTER_URL = "http://www.w1480440fyp.co.uk/getemail.php";
    private static final String REGISTER_URL2 = "http://www.w1480440fyp.co.uk/updateemail.php";
    private Button SubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editemail);
        getEmail();
        String loggedinuser = getIntent().getStringExtra("Logged_User");

        username = loggedinuser;


        SubmitButton = (Button) findViewById(R.id.submit_button);

        SubmitButton.setOnClickListener(this);



    }



    String username = "";
    String email = "";

    private void getEmail() {
        String Email = "";
        final TextView emailField = (TextView) findViewById(R.id.emailField);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            emailField.setText(response);
                            email = emailField.getText().toString();

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(EditEmailActivity.this, error.toString(), Toast.LENGTH_LONG).show();
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
        }


    private void updateEmail() {
        String Email = "";
        final TextView emailField = (TextView) findViewById(R.id.emailField);

        Email = emailField.getText().toString().trim();

        if (isEmailValid(Email) == false) {
            emailField.setError("Please enter a suitable email address.");
        } else if (Email.equals("")) {
            emailField.setError("Please enter a suitable email address.");

        } else {

            StringRequest stringRequest2 = new StringRequest(Request.Method.POST, REGISTER_URL2,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Intent intent = new Intent(getBaseContext(), AccountActivity.class);
                            intent.putExtra("Logged_User", username);
                            finish();

                            startActivity(intent);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(EditEmailActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_USERNAME, username);
                    params.put(KEY_EMAIL, emailField.getText().toString());
                    return params;
                }


            };
            RequestQueue requestQueue2 = Volley.newRequestQueue(this);
            requestQueue2.add(stringRequest2);
        }
    }

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

    public void onClick(View v) {
        if (v == SubmitButton) {
            updateEmail();
        }
    }

}





