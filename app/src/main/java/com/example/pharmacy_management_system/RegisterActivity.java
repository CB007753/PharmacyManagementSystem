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
import com.example.pharmacy_management_system.Model.RegistrationDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText reg_fullname,reg_email,reg_password,reg_contact;
    private Button login_btn,reg_btn;

    private API_Interface api_interface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        reg_fullname=findViewById(R.id.fullname);
        reg_email=findViewById(R.id.email);
        reg_password=findViewById(R.id.password);
        reg_contact=findViewById(R.id.mobile);

        reg_btn=findViewById(R.id.button_reg);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(reg_fullname.getText().toString().equals("")|| reg_email.getText().toString().equals("")
                        || reg_password.getText().toString().equals("")|| reg_contact.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Empty Values Cant Be Registered", Toast.LENGTH_SHORT).show();
                }
                else if (reg_password.length()<8)
                {
                    Toast.makeText(RegisterActivity.this, "Password Should Be atleast 8 Characters", Toast.LENGTH_LONG).show();
                }
                else if(reg_password.length()>10)
                {
                    Toast.makeText(RegisterActivity.this, "Password Should Be Below or Equal To 10 Characters", Toast.LENGTH_LONG).show();
                }
                else if (reg_contact.length()<10 || reg_contact.length()>10)
                {
                    Toast.makeText(RegisterActivity.this, "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    RegistrationDTO registrationDTO = new RegistrationDTO();

                        //setting values entered by user
                        registrationDTO.setFullname(reg_fullname.getText().toString());
                        registrationDTO.setEmail(reg_email.getText().toString());
                        registrationDTO.setMobile(reg_contact.getText().toString());
                        registrationDTO.setPassword(reg_password.getText().toString());

                        api_interface = Backend_API.getRetrofit().create(API_Interface.class);
                        Call<LoginRespo> call = api_interface.RegisterUser(registrationDTO);

                    call.enqueue(new Callback<LoginRespo>() {
                        @Override
                        public void onResponse(Call<LoginRespo> call, Response<LoginRespo> response) {
                           Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<LoginRespo> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        });

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
