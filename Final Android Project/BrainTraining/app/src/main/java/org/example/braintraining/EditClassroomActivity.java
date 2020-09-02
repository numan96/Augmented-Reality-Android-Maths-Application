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

/**
 * Created by Numan2 on 22/03/2017.
 */

public class EditClassroomActivity extends FragmentActivity implements View.OnClickListener {

    public static final String KEY_USERNAME = "username";
    public static final String KEY_CLASSROOMID = "classroomid";
    private static final String REGISTER_URL = "http://www.w1480440fyp.co.uk/getclassid.php";
    private static final String REGISTER_URL2 = "http://www.w1480440fyp.co.uk/updateclassid.php";
    private Button SubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editclassid);
        getClassroom();
        String loggedinuser = getIntent().getStringExtra("Logged_User");

        username = loggedinuser;


        SubmitButton = (Button) findViewById(R.id.submit_button);

        SubmitButton.setOnClickListener(this);



    }



    String username = "";
    String classroomid = "";

    private void getClassroom() {

        final TextView classidField = (TextView) findViewById(R.id.classidField);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        classidField.setText(response);
                        classroomid = classidField.getText().toString();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditClassroomActivity.this, error.toString(), Toast.LENGTH_LONG).show();
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


    private void updateClassroom() {
        String ClassroomID = "";
        final TextView classidField = (TextView) findViewById(R.id.classidField);

        ClassroomID = classidField.getText().toString().trim();


        if (ClassroomID.equals("")) {
            classidField.setError("Please enter a suitable classroom ID.");

        } else if (ClassroomID.length() < 5) {
            classidField.setError("Please enter more than 4 characters.");


        } else if (ClassroomID.length() > 14) {
            classidField.setError("Please enter less than 15 characters.");


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
                            Toast.makeText(EditClassroomActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_USERNAME, username);
                    params.put(KEY_CLASSROOMID, classidField.getText().toString());
                    return params;
                }


            };
            RequestQueue requestQueue2 = Volley.newRequestQueue(this);
            requestQueue2.add(stringRequest2);
        }
    }



    public void onClick(View v) {
        if (v == SubmitButton) {
            updateClassroom();
        }
    }

}





