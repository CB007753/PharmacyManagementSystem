package com.example.pharmacy_management_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacy_management_system.Adapter.OnTheWayOrderAdapter;
import com.example.pharmacy_management_system.Client.API_Interface;
import com.example.pharmacy_management_system.Client.Backend_API;
import com.example.pharmacy_management_system.Model.Order;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PharmacistOntheWayOrdersActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private API_Interface api_interface;

    ArrayList<Order> orderList=new ArrayList<>();
    private RecyclerView recyclerView;
    private OnTheWayOrderAdapter onTheWayOrderAdatper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_ontheway_order);


        recyclerView=findViewById(R.id.pharmacist_ontheway_orders_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent=getIntent();
        String email=intent.getStringExtra("email");//this will be used to search on the way orders
        String status= "On The Way";//this will be used to search on the way orders

        api_interface = Backend_API.getRetrofit().create(API_Interface.class);
        Call<List<Order>> call=api_interface.ViewOnTheWayOrders(email,status);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {


                try{
                    Toast.makeText(PharmacistOntheWayOrdersActivity.this,"On The Way Orders Displayed Successfully!",Toast.LENGTH_LONG).show();

                    orderList= new ArrayList<>(response.body());
                    onTheWayOrderAdatper=new OnTheWayOrderAdapter(orderList,PharmacistOntheWayOrdersActivity.this);
                    recyclerView.setAdapter(onTheWayOrderAdatper);

                }
                catch(Exception e)
                {
                    Toast.makeText(PharmacistOntheWayOrdersActivity.this,"Error: "+e,Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

                Toast.makeText(PharmacistOntheWayOrdersActivity.this,"Failed To Display",Toast.LENGTH_LONG).show();

            }
        });



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
                startActivity(new Intent(PharmacistOntheWayOrdersActivity.this,PharmacistHomeActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_orders:
                startActivity(new Intent(PharmacistOntheWayOrdersActivity.this,PharmacistViewOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_contact:
                startActivity(new Intent(PharmacistOntheWayOrdersActivity.this,PharmacistContactActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_ontheway_orders:
                startActivity(new Intent(PharmacistOntheWayOrdersActivity.this,PharmacistOntheWayOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_completed_orders:
                startActivity(new Intent(PharmacistOntheWayOrdersActivity.this,PharmacistOrderHistoryActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_logout:
                startActivity(new Intent(PharmacistOntheWayOrdersActivity.this,LoginActivity.class));
                break;
        }
        return false;
    }
}
