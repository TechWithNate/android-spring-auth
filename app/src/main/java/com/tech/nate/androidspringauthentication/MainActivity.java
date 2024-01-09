package com.tech.nate.androidspringauthentication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText firstname;
    private EditText lastname;
    private EditText email;
    private EditText password;
    private EditText confirmP;
    private MaterialButton createBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFields();

        createBtn.setOnClickListener(v -> {
            checkFields();
        });

    }

    private void initFields(){
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmP = findViewById(R.id.confirm_password);
        createBtn = findViewById(R.id.create_btn);
    }
    private void checkFields(){
        if (firstname.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter First name", Toast.LENGTH_SHORT).show();
        }else if (lastname.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter last name", Toast.LENGTH_SHORT).show();
        } else if (email.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        } else if (!confirmP.getText().toString().equals(password.getText().toString())) {
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        }else {
            //TODO: Implement Create Account Logic
        }
    }
}