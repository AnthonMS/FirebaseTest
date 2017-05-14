package com.example.anthonsteiness.firebasetest2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText editMail;
    private EditText editPass;
    private Button registerBtn;
    private Button loginBtn;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        // Check if Firebase is already logged in to
        if (firebaseAuth.getCurrentUser() != null)
        {
            // The Firebase is already logged in to
            //finish();
            //Intent loggedInIntent = new Intent(MainActivity.this, LoggedInActivity.class);
            //startActivity(loggedInIntent);
        }

        progressDialog = new ProgressDialog(this);

        registerBtn = (Button) findViewById(R.id.registerBtn);
        editMail = (EditText) findViewById(R.id.editText1);
        editPass = (EditText) findViewById(R.id.editText2);
        registerBtn.setOnClickListener(buttonClickListener);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {

            switch(view.getId())
            {
                case R.id.registerBtn:
                    userRegister();
                    break;
                case R.id.loginBtn:
                    userLogin();
                    break;
            }
        }
    };

    private void userLogin()
    {
        String email = editMail.getText().toString().trim();
        String pass  = editPass.getText().toString().trim();

        if((TextUtils.isEmpty(email)) || (TextUtils.isEmpty(pass)))
        {
            // Email or Password empty
            Toast.makeText(MainActivity.this, "Please enter valid values in text fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // If EditText not empty it comes to here
        progressDialog.setMessage("Checking user information...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful())
                        {
                            // The login is successful
                            Toast.makeText(MainActivity.this, "User Login successful", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), LoggedInActivity.class));
                        }
                        else
                        {
                            // Not successful
                            Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void userRegister()
    {
        String email = editMail.getText().toString().trim();
        String pass  = editPass.getText().toString().trim();

        if((TextUtils.isEmpty(email)) || (TextUtils.isEmpty(pass)))
        {
            // Email or Password empty
            Toast.makeText(MainActivity.this, "Please enter valid values in text fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // If EditText not empty it comes to here
        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        progressDialog.dismiss();
                        if (task.isSuccessful())
                        {
                            // Registration Successful
                            Toast.makeText(MainActivity.this, "User Registration successful", Toast.LENGTH_SHORT).show();

                            //finish();
                            //Intent loggedInIntent = new Intent(MainActivity.this, LoggedInActivity.class);
                            //startActivity(loggedInIntent);
                        }
                        else
                        {
                            // Not successful
                            Toast.makeText(MainActivity.this, "User Registration not successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
