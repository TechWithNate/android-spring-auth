package com.tech.nate.androidspringauthentication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.tech.nate.androidspringauthentication.model.User;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText firstname;
    private EditText lastname;
    private EditText email;
    private EditText password;
    private EditText confirmP;
    private MaterialButton createBtn;
    private JsonPlaceHolderApi jsonPlaceHolderApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFields();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

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
        String firstname1 = firstname.getText().toString();
        String lastname1 = lastname.getText().toString();
        String email1 = email.getText().toString();
        String password1 = password.getText().toString();
        if (firstname1.isEmpty()){
            Toast.makeText(this, "Enter First name", Toast.LENGTH_SHORT).show();
        }else if (lastname1.isEmpty()){
            Toast.makeText(this, "Enter last name", Toast.LENGTH_SHORT).show();
        } else if (email1.isEmpty()) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
        } else if (password1.isEmpty()) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        } else if (!confirmP.getText().toString().equals(password1)) {
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        }else {
            //TODO: Implement Create Account Logic
            createAccount(firstname1, lastname1, email1, password1);
        }
    }

    private void createAccount(String firstname, String lastname, String email, String password){
        User user = new User(firstname, lastname, email, password);

        Call<User> call = jsonPlaceHolderApi.createUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Code: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, "Hurray! Account Created Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Hmmm: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}