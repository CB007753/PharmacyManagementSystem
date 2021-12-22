package com.example.pharmacy_management_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pharmacy_management_system.Client.API_Interface;
import com.example.pharmacy_management_system.Client.Backend_API;
import com.example.pharmacy_management_system.Model.User;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PharmacistViewProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView user_name,user_email,user_mobile;
    private API_Interface api_interface;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_viewprofile);

        user_name = findViewById(R.id.profile_name);
        user_email = findViewById(R.id.profile_email);
        user_mobile = findViewById(R.id.profile_mobile);

        viewProfile();

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

    private void viewProfile(){

        api_interface = Backend_API.getRetrofit().create(API_Interface.class);

        Intent receive_email= getIntent();
        final String email=receive_email.getStringExtra("email");

        Call<User> call = api_interface.getUserByEmail(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful())
                {

                    Toast.makeText(PharmacistViewProfileActivity.this,"User Details Displayed Successfully !",Toast.LENGTH_SHORT).show();

                    //null pointer execption may occur, so try catch is used to prevent crash
                    try{
                        //getting the user details from backend through response body
                        User user = response.body();
                        //setting the user details which was retrieved from backend through response body
                        user_name.setText(user.getFullname());
                        user_email.setText(user.getEmail());
                        user_mobile.setText(user.getMobile());
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(PharmacistViewProfileActivity.this,"Error: "+e,Toast.LENGTH_LONG).show();

                    }

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(PharmacistViewProfileActivity.this,"Failed To Display !",Toast.LENGTH_SHORT).show();

            }
        });

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
                startActivity(new Intent(PharmacistViewProfileActivity.this,PharmacistHomeActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_orders:
                startActivity(new Intent(PharmacistViewProfileActivity.this,PharmacistViewOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_contact:
                startActivity(new Intent(PharmacistViewProfileActivity.this,PharmacistContactActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_ontheway_orders:
                startActivity(new Intent(PharmacistViewProfileActivity.this,PharmacistOntheWayOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_completed_orders:
                startActivity(new Intent(PharmacistViewProfileActivity.this,PharmacistOrderHistoryActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_logout:
                startActivity(new Intent(PharmacistViewProfileActivity.this,LoginActivity.class));
                break;
        }
        return false;
    }
}
