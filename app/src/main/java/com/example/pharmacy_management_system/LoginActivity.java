package com.example.pharmacy_management_system;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pharmacy_management_system.Client.API_Interface;
import com.example.pharmacy_management_system.Client.Backend_API;
import com.example.pharmacy_management_system.JsonResponse.LoginRespo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText email_edittext,password_edittext;
    private Button login_button,register_button;

    private API_Interface api_interface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        email_edittext=(EditText)findViewById(R.id.login_email);
        password_edittext=(EditText)findViewById(R.id.login_password);

        login_button=(Button)findViewById(R.id.button_login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(email_edittext.getText().toString().isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Email Is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(password_edittext.getText().toString().isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Password Is Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Login();
                }
            }
        });
        register_button=(Button)findViewById(R.id.button_register_in_login);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Login(){

    api_interface = Backend_API.getRetrofit().create(API_Interface.class);

        //getting username(email) and pass entered by user
        final String email = email_edittext.getText().toString();
        final String password = password_edittext.getText().toString();

        Call<LoginRespo> loginRespoCall =api_interface.getLoginResponse(email,password);

        loginRespoCall.enqueue(new Callback<LoginRespo>() {
            @Override
            public void onResponse(Call<LoginRespo> call, Response<LoginRespo> response) {

                if(response.body() != null){
                    try {
                        LoginRespo loginRespo = response.body();

                        if(loginRespo.getResponse().matches("Correct Password")){

                            LoggedInCache.loggedInUser = new LoggedInUser(email,"");
                            Toast.makeText(LoginActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();

                            //setting the location where the user should go after logging in successfully
                            Intent intent = new Intent(LoginActivity.this,PharmacistHomeActivity.class);

                            intent.putExtra("email", email);
                            LoginActivity.this.startActivity(intent);
                        }
                        else if (loginRespo.getResponse().matches("Password Incorrect")) {

                            Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();

                        }
                        else if(loginRespo.getResponse().matches("User does not exist")) {

                            Toast.makeText(LoginActivity.this, "User Does not exist", Toast.LENGTH_SHORT).show();

                        }
                    }
                    catch(Exception e) {
                        //Using this coz i got java.lang.NullPointerException (app is crashing when logging in)
                        Toast.makeText(LoginActivity.this, "Error: "+e , Toast.LENGTH_LONG).show();

                    }
                }




            }

            @Override
            public void onFailure(Call<LoginRespo> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error: "+t, Toast.LENGTH_LONG).show();
            }
        });


    }

}
