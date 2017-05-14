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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoggedInActivity extends AppCompatActivity {
    Button logoutBtn;
    TextView userMailView;
    private FirebaseAuth firebaseAuth;
    //private ProgressDialog progressDialog;

    private EditText editMail;
    private EditText editTextFName;
    private EditText editCVR;
    private EditText editTextLName;
    Button saveInfoBtn;

    private DatabaseReference databaseReference;

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

        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        //progressDialog = new ProgressDialog(this);
        logoutBtn = (Button) findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(buttonClickListener2);

        editMail = (EditText) findViewById(R.id.editTextMail);
        editTextFName = (EditText) findViewById(R.id.editTextFName);
        editTextLName = (EditText) findViewById(R.id.editTextLName);
        editCVR = (EditText) findViewById(R.id.editTextCVR);

        saveInfoBtn = (Button) findViewById(R.id.saveInfoBtn);
        saveInfoBtn.setOnClickListener(buttonClickListener2);

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
                    //Toast.makeText(LoggedInActivity.this, "You are trying to logout...", Toast.LENGTH_SHORT).show();
                    userLogout();
                    break;
                case R.id.saveInfoBtn:
                    //Toast.makeText(LoggedInActivity.this, "You are trying to save info...", Toast.LENGTH_SHORT).show();
                    saveUserInfo();
                    break;
            }
        }
    };

    private void userLogout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    private void saveUserInfo()
    {
        String fName = editTextFName.getText().toString().trim();
        String lName = editTextLName.getText().toString().trim();
        String mail = editMail.getText().toString().trim();
        String cvr = editCVR.getText().toString().trim();

        User customer = new User(mail, cvr, fName, lName);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(customer);

        Toast.makeText(LoggedInActivity.this, "User Information Saved...", Toast.LENGTH_SHORT).show();
    }
}
