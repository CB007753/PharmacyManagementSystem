package com.example.pharmacy_management_system;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText email_edittext,password_edittext;
    private Button login_button,register_button;

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
//code for connecting backend and doing tasks
    }

}
