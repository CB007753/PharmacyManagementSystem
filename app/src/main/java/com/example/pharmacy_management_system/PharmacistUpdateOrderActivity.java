package com.example.pharmacy_management_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.example.pharmacy_management_system.Model.Order;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PharmacistUpdateOrderActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private API_Interface api_interface;
    private TextView updated_textview,delivered_textview;
    private Button update_Btn,home_Btn;
    private ImageView updated_imageview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_update_order);

        //getting values from intent which was put into it in last activity(On the way order activity(adapter))
        Intent new_intent= getIntent();
        final String drug_name = new_intent.getStringExtra("drugname");
        final int order_price = new_intent.getIntExtra("drugprice",0);
        final int order_qnty = new_intent.getIntExtra("qnty",0);
        final int order_id = new_intent.getIntExtra("id",0);
        //final String drug_status=intent.getStringExtra("status");
        final String order_email = new_intent.getStringExtra("email");
        final int order_total = new_intent.getIntExtra("total",0);
        final String order_date = new_intent.getStringExtra("date");
        final String order_unit = new_intent.getStringExtra("unit");

        updated_textview=findViewById(R.id.updated_text_view);
        delivered_textview=findViewById(R.id.delivered_textview);
        updated_imageview=findViewById(R.id.updated_image_view);
        update_Btn=findViewById(R.id.update_order_button);

        update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                String status="Delivered";

                //this obj will be used to update order
                Order order = new Order();

                order.setDrugname(drug_name);
               order.setPrice(order_price);
                order.setQuantity(order_qnty);
                order.setTotal(order_total);
                order.setDate(order_date);
                order.setUnit(order_unit);
                order.setEmail(order_email);
                order.setId(order_id);

                //updating status to delivered on click
                order.setStatus(status);


                api_interface = Backend_API.getRetrofit().create(API_Interface.class);
                Call<Void> call = api_interface.update_Order(order);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(PharmacistUpdateOrderActivity.this, "Order Delivered Successfully !", Toast.LENGTH_SHORT).show();

                        updated_textview.setText("Get Back To The Drug Store If You Need More !");
                        delivered_textview.setText("Great !");
                        updated_imageview.setImageResource(R.drawable.delivered);

                        //hides the update button
                        update_Btn.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                        Toast.makeText(PharmacistUpdateOrderActivity.this, "Error Updating Order !", Toast.LENGTH_SHORT).show();
                        updated_textview.setText("Error Updating Order !");

                    }
                });


            }
        });

        try {
            //home button- sends pharmacist back to home
            home_Btn = (Button) findViewById(R.id.update_order_home_button);
            home_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(PharmacistUpdateOrderActivity.this, PharmacistHomeActivity.class);
                    intent.putExtra("email", order_email);
                    startActivity(intent);

                }
            });
        }
        catch (Exception e){
            Toast.makeText(PharmacistUpdateOrderActivity.this, "Error: "+e, Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(PharmacistUpdateOrderActivity.this,PharmacistHomeActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_orders:
                startActivity(new Intent(PharmacistUpdateOrderActivity.this,PharmacistViewOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_contact:
                startActivity(new Intent(PharmacistUpdateOrderActivity.this,PharmacistContactActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_ontheway_orders:
                startActivity(new Intent(PharmacistUpdateOrderActivity.this,PharmacistOntheWayOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_completed_orders:
                startActivity(new Intent(PharmacistUpdateOrderActivity.this,PharmacistOrderHistoryActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_profile:
                startActivity(new Intent(PharmacistUpdateOrderActivity.this,PharmacistViewProfileActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_logout:
                startActivity(new Intent(PharmacistUpdateOrderActivity.this,LoginActivity.class));
                break;
        }
        return false;
    }
}
