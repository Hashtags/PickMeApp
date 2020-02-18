package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText mTextUsername, mTextPassword, mTextConfirmPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextUsername = findViewById(R.id.register_edit_text_username);
        mTextPassword = findViewById(R.id.register_edit_text_password);
        mTextConfirmPassword = findViewById(R.id.register_edit_text_confirm_password);
        mButtonRegister = findViewById(R.id.register_button_register);
        mTextViewLogin = findViewById(R.id.register_text_view_login);

        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim(),
                        password = mTextPassword.getText().toString().trim(),
                        confirmPassword = mTextConfirmPassword.getText().toString().trim();

                if (password.equals(confirmPassword)) {
                    long val = db.addUser(user, password);

                    if (val > 0) {

                        Toast.makeText(RegisterActivity.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(moveToLogin);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Password's Don't Match!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
