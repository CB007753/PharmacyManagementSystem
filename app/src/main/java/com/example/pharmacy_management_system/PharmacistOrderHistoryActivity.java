package com.example.pharmacy_management_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class PharmacistOrderHistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_order_history);

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
                startActivity(new Intent(PharmacistOrderHistoryActivity.this,PharmacistHomeActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_orders:
                startActivity(new Intent(PharmacistOrderHistoryActivity.this,PharmacistViewOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_contact:
                startActivity(new Intent(PharmacistOrderHistoryActivity.this,PharmacistContactActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_ontheway_orders:
                startActivity(new Intent(PharmacistOrderHistoryActivity.this,PharmacistOntheWayOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_completed_orders:
                startActivity(new Intent(PharmacistOrderHistoryActivity.this,PharmacistOrderHistoryActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_logout:
                startActivity(new Intent(PharmacistOrderHistoryActivity.this,LoginActivity.class));
                break;
        }
        return false;
    }
}
