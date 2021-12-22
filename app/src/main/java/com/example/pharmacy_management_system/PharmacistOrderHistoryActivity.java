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

import com.example.pharmacy_management_system.Adapter.OrderHistoryAdapter;
import com.example.pharmacy_management_system.Client.API_Interface;
import com.example.pharmacy_management_system.Client.Backend_API;
import com.example.pharmacy_management_system.Model.Order;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PharmacistOrderHistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private API_Interface api_interface;

    ArrayList<Order> orderList=new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderHistoryAdapter orderHistoryAdapter;

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


        recyclerView=findViewById(R.id.pharmacist_delivered_orders_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent=getIntent();
        String email=intent.getStringExtra("email");//this will be used to search order history of current user
        String status= "Delivered";//this will be used to search order history

        api_interface = Backend_API.getRetrofit().create(API_Interface.class);

        Call<List<Order>> call = api_interface.ViewDeliveredOrders(email,status);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {

                try{

                    if(response.isSuccessful()) {

                        Toast.makeText(PharmacistOrderHistoryActivity.this, "Order History Displayed Successfully!", Toast.LENGTH_LONG).show();

                        orderList = new ArrayList<>(response.body());
                        orderHistoryAdapter = new OrderHistoryAdapter(orderList, PharmacistOrderHistoryActivity.this);
                        recyclerView.setAdapter(orderHistoryAdapter);

                    }
                    else
                    {

                        Toast.makeText(PharmacistOrderHistoryActivity.this, "Response Not Successful !", Toast.LENGTH_LONG).show();

                    }

                }
                catch(Exception e)
                {
                    Toast.makeText(PharmacistOrderHistoryActivity.this,"Error: "+e,Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

                Toast.makeText(PharmacistOrderHistoryActivity.this,"Failed To Display",Toast.LENGTH_LONG).show();

            }
        });




    }//end of onCreate method

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
            case R.id.navigation_profile:
                startActivity(new Intent(PharmacistOrderHistoryActivity.this,PharmacistViewProfileActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_logout:
                startActivity(new Intent(PharmacistOrderHistoryActivity.this,LoginActivity.class));
                break;
        }
        return false;
    }
}
