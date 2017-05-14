package com.example.anthonsteiness.firebasetest2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoggedInActivity extends AppCompatActivity {
    Button logoutBtn;
    TextView userMailView;
    private FirebaseAuth firebaseAuth;
    //private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        firebaseAuth = FirebaseAuth.getInstance();
        // Check if Firebase is logged in to
        if (firebaseAuth.getCurrentUser() == null)
        {
            // The Firebase is not logged in
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        //progressDialog = new ProgressDialog(this);
        logoutBtn = (Button) findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(buttonClickListener2);


        userMailView = (TextView) findViewById(R.id.textViewUserEmail);

        userMailView.setText("Welcome " + user.getEmail());
    }


    private View.OnClickListener buttonClickListener2 = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {

            switch(view.getId())
            {
                case R.id.logoutBtn:
                    Toast.makeText(LoggedInActivity.this, "You are trying to logout...", Toast.LENGTH_SHORT).show();
                    //userLogout();
                    break;
            }
        }
    };

    private void userLogout()
    {

    }
}
