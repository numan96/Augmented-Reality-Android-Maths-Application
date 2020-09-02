package org.example.braintraining;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.unity3d.player.*;

import java.io.File;

public class MainFragment extends Fragment {

    private AlertDialog mDialog;

    @Override






    public View onCreateView(LayoutInflater inflater, ViewGroup
            container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,
                container, false);

        final String loggedinuser = getActivity().getIntent().getStringExtra("Logged_User");
        final String classroomid = getActivity().getIntent().getStringExtra("Class_ID");
        // Handle buttons here...
        View helpButton = rootView.findViewById(R.id.help_button);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.about_title);
                builder.setMessage("To access the below activities, please login!" +
                       "\n\n" +
                "For help on how to setup the AR Markers, click the AR Marker button to the left of this button."
                       + "\n\n" +
                "Scan Activity - Use the worksheets (Bar Graph and 3d shapes) in the AR Markers PDF and you can scan the markers for extra help and fun!"
                        + "\n\n" +
                "Quiz Activity - Use the Quiz AR marker in the AR Markers PDF and you can scan it to pick a operation for the quiz to start and earn points for the leaderboard!"
                        + "Hover your hand over the menu options and the multiple choice answer as if they were on the paper, to progress and earn points."
                     +   "\n\n" +
                "User Details - This is to the top right! If you are not logged in, this will take you to the log in screen! If you are logged in," +
                " you can access your account! This is where you can access your class leaderboard (use the leaderboard AR marker from the"
                + " AR Markers PDF) and see how you are doing against your friends! To battle against your friends in the leaderboard, make sure you all have the same classroom ID! ");
                builder.setCancelable(false);
                builder.setPositiveButton(R.string.ok_label,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface,
                                                int i) {
                                // nothing
                            }
                        });

                mDialog = builder.show();
            }
        });

       final TextView loggedusername = (TextView) rootView.findViewById(R.id.logged_user);
        View userDetailsButton = rootView.findViewById(R.id.userdetailsButton);
        final View logoutButton = rootView.findViewById(R.id.logout_button);
        View quizButton = rootView.findViewById(R.id.quiz_button);
        View markersButton = rootView.findViewById(R.id.markers_button);
        View scanButton = rootView.findViewById(R.id.scan_button);

        loggedusername.setText(loggedinuser);

        if (loggedusername.getText().equals("")) {

            loggedusername.setText("Login");


}

        if (!loggedusername.getText().equals("Login")) {

            logoutButton.setVisibility(View.VISIBLE);


        }


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loggedusername.setText("Login");
                logoutButton.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Logged Out",
                        Toast.LENGTH_SHORT).show();
            }
        });

        userDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getBaseContext(), LoginActivity.class);
                intent.putExtra("Logged_User2", loggedusername.getText().toString());
                getActivity().startActivity(intent);
            }
        });
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loggedusername.getText().equals("Login")) {

                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(getActivity());
                    builder.setTitle("Not logged in");
                    builder.setMessage("Please login to access this activity!");
                    builder.setCancelable(false);
                    builder.setPositiveButton(R.string.ok_label,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface,
                                                    int i) {
                                    // nothing
                                }
                            });
                    mDialog = builder.show();

                }
                else {
                    Intent myIntent = new Intent(getActivity().getBaseContext(), UnityPlayerActivity.class);
                    myIntent.putExtra("Username", loggedinuser); //Optional parameters
                    myIntent.putExtra("ClassroomID", classroomid); //Optional parameters
                    myIntent.putExtra("sceneName", "Menu"); //Optional parameters
                    getActivity().startActivity(myIntent);
                }
            }
        });
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loggedusername.getText().equals("Login")) {

                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(getActivity());
                    builder.setTitle("Not logged in");
                    builder.setMessage("Please login to access this activity!");
                    builder.setCancelable(false);
                    builder.setPositiveButton(R.string.ok_label,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface,
                                                    int i) {
                                    // nothing
                                }
                            });
                    mDialog = builder.show();

                } else {
                    Intent myIntent = new Intent(getActivity().getBaseContext(), UnityPlayerActivity.class);
                    myIntent.putExtra("Username", loggedinuser); //Optional parameters
                    myIntent.putExtra("ClassroomID", classroomid); //Optional parameters
                    myIntent.putExtra("sceneName", "scan1"); //Optional parameters
                    getActivity().startActivity(myIntent);
                }
            }
        });
        markersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://w1480440fyp.co.uk/ARMarkers.pdf"));
                startActivity(browserIntent);
            }
        });
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mDialog != null)
            mDialog.dismiss();
    }

};



