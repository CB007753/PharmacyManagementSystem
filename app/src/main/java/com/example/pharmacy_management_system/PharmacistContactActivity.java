package com.example.pharmacy_management_system;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pharmacy_management_system.Client.API_Interface;
import com.example.pharmacy_management_system.Client.Backend_API;
import com.example.pharmacy_management_system.Model.ContactDTO;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//implementing nav view to use "onNavigationItemSelected" methods to add navigations for menu items
public class PharmacistContactActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private EditText message_edittext;
    private Button submit_Btn,home_Btn;
    private ImageView msg_sent_img_view;
    private TextView msg_sent_text_view, page_info_txt_view;

    private API_Interface api_interface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_contact);

        message_edittext=findViewById(R.id.user_contact_edit_text);
        submit_Btn=findViewById(R.id.contact_submit_button);
        msg_sent_img_view = findViewById((R.id.msg_sent_image_view));
        msg_sent_text_view = findViewById((R.id.msg_sent_text_view));
        page_info_txt_view = findViewById((R.id.user_contact_page_info_text_view));

        Intent receive_email= getIntent();
        final String email = receive_email.getStringExtra("email");

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        final String DateandTime = simpleDateFormat.format(new Date());

        submit_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContactDTO contactDTO=new ContactDTO();

                contactDTO.setMessage(message_edittext.getText().toString());
                contactDTO.setDate(DateandTime);
                contactDTO.setEmail(email);

                api_interface = Backend_API.getRetrofit().create(API_Interface.class);

                Call<Void> call =api_interface.SendMessage(contactDTO);
                call.enqueue(new Callback<Void>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        Toast.makeText(PharmacistContactActivity.this, "Message Sent To Admin !", Toast.LENGTH_SHORT).show();

                        msg_sent_text_view.setText("Message Sent Successfully !");
                        msg_sent_img_view.setImageResource(R.drawable.msg_sent);

                        //hides the button,edittext and text view
                        submit_Btn.setVisibility(View.GONE);
                        message_edittext.setVisibility(View.GONE);
                        page_info_txt_view.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                        Toast.makeText(PharmacistContactActivity.this, "Message failed to send !", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        try {
            //home button- sends pharmacist back to home
            home_Btn = (Button) findViewById(R.id.user_contact_home_button);
            home_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(PharmacistContactActivity.this, PharmacistHomeActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);

                }
            });
        }
        catch (Exception e){
            Toast.makeText(PharmacistContactActivity.this, "Error: "+e, Toast.LENGTH_SHORT).show();
        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigationDrawerOpen, R.string.navigationDrawerClose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // using onCreateOptionsMenu() to specify the options menu for an activity
        getMenuInflater().inflate(R.menu.pharmacist_home, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.navigation_home:
                startActivity(new Intent(PharmacistContactActivity.this,PharmacistHomeActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_orders:
                startActivity(new Intent(PharmacistContactActivity.this,PharmacistViewOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_ontheway_orders:
                startActivity(new Intent(PharmacistContactActivity.this,PharmacistOntheWayOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_completed_orders:
                startActivity(new Intent(PharmacistContactActivity.this,PharmacistOrderHistoryActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_profile:
                startActivity(new Intent(PharmacistContactActivity.this,PharmacistViewProfileActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_logout:
                startActivity(new Intent(PharmacistContactActivity.this,LoginActivity.class));
                break;
        }
        return false;
    }

}






