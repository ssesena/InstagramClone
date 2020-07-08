package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.instagramclone.databinding.ActivitySignUpBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String username = binding.etUsername.getText().toString();
                String password = binding.etPassword.getText().toString();
                if(username.length() != 0 && password.length() != 0) {
                    signUpUser(username, password);
                } else {
                    Toast.makeText(SignUpActivity.this, "Username and Password cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }

    private void signUpUser(String username, String password) {
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {

            @Override
            public void done(ParseException e) {
                if (e != null){
                    Log.e(TAG,"Issue with login" + e.toString());
                    Toast.makeText(SignUpActivity.this, "Account already exists for this username.", Toast.LENGTH_SHORT).show();
                    return;
                }
                goLoginActivity();
                Toast.makeText(SignUpActivity.this, "Success!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void goLoginActivity() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

}