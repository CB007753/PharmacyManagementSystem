package com.example.pharmacy_management_system;

import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    private EditText reg_fname,reg_email,reg_password,reg_contact;
    private Button login_btn,reg_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        reg_fname=findViewById(R.id.fullname);
        reg_email=findViewById(R.id.email);
        reg_password=findViewById(R.id.password);
        reg_contact=findViewById(R.id.mobile);

        reg_btn=findViewById(R.id.button_reg);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(reg_fname.getText().toString().equals("")|| reg_email.getText().toString().equals("")
                        || reg_password.getText().toString().equals("")|| reg_contact.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Empty Values Cant Be Registered", Toast.LENGTH_SHORT).show();
                }
                else if (reg_password.length()<8)
                {
                    Toast.makeText(RegisterActivity.this, "Password Should Be More Than 8 Characters", Toast.LENGTH_LONG).show();
                }
                else if (reg_contact.length()<10 || reg_contact.length()>10)
                {
                    Toast.makeText(RegisterActivity.this, "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }

            }
        });

        login_btn=findViewById(R.id.button_login_in_register);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
