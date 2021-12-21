package com.example.pharmacy_management_system;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pharmacy_management_system.Client.API_Interface;
import com.example.pharmacy_management_system.Client.Backend_API;
import com.example.pharmacy_management_system.Model.OrderDTO;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//this page will be displayed when pharmacist clicks the buy button in last activity(view orders activity)
public class PharmacistCheckoutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private API_Interface api_interface;
    private EditText drugqnty;
    private Button place_order_Btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_checkout);

        Intent receive_email= getIntent();
        final String email=receive_email.getStringExtra("email");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigationDrawerOpen, R.string.navigationDrawerClose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //setting values retrieved from previous activity to the textview of this acrivity
        Intent intent= getIntent();
        final String drug_name=intent.getStringExtra("drugname");
        final String drug_price=intent.getStringExtra("drugprice");
        final String drug_unit=intent.getStringExtra("drugunit");
        String drug_desc=intent.getStringExtra("desc");
        final int price=intent.getIntExtra("price",0);


        TextView drug_name_view=(TextView)findViewById(R.id.drug_name_checkout);
        TextView drug_price_view=(TextView)findViewById(R.id.drug_price_checkout);
        TextView drug_unit_view=(TextView)findViewById(R.id.drug_unit_checkout);
        TextView drug_desc_view=(TextView)findViewById(R.id.drug_desc_checkout);
        TextView drug_email_view=(TextView)findViewById(R.id.drug_email_checkout);

        drug_name_view.setText(drug_name);
        drug_price_view.setText(drug_price);
        drug_unit_view.setText(drug_unit);
        drug_desc_view.setText(drug_desc);
        drug_email_view.setText(email);

        //--------------------------------------------------------------------------------------

        place_order_Btn=(Button)findViewById(R.id.place_order_btn);
        drugqnty= findViewById(R.id.drug_qnty_edit_text);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        final String Date_Time = sdf.format(new Date());


        place_order_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(drugqnty.getText().toString().isEmpty()){

                    Toast.makeText(PharmacistCheckoutActivity.this, "Quantity cannot be empty", Toast.LENGTH_LONG).show();

                }
                else if(drugqnty.length()>2) {
                    Toast.makeText(PharmacistCheckoutActivity.this, "Quantity Must Be Below 100", Toast.LENGTH_LONG).show();
                }
                else {

                    OrderDTO orderDTO = new OrderDTO();

                    orderDTO.setEmail(email);
                    orderDTO.setDrugname(drug_name);
                    orderDTO.setDate(Date_Time);
                    orderDTO.setStatus("On The Way");//this will be changed to delivered once the pharmacist checks and updates the order status
                    orderDTO.setUnit(drug_unit);

                     final int drug_Qnty = Integer.parseInt(drugqnty.getText().toString());

                        //--quantity not getting added to the database, should check later--
                           orderDTO.setQuantity(drug_Qnty);


                    //calculating the total price by multplying 1 item price with number of quantity selected and adding rs.100 which is delivery fees
                    final int Total = (price*drug_Qnty)+100;
                    orderDTO.setTotal(Total);
                    orderDTO.setPrice(price);



                    api_interface = Backend_API.getRetrofit().create(API_Interface.class);
                    Call<Void> call = api_interface.PlaceOrder(orderDTO);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            Toast.makeText(PharmacistCheckoutActivity.this, "Order Placed Successfully", Toast.LENGTH_LONG).show();
                            Intent intent= new Intent(PharmacistCheckoutActivity.this,PharmacistOrderReceiptActivity.class);
                            intent.putExtra("drugname",drug_name);
                            intent.putExtra("qnty",drug_Qnty);
                            intent.putExtra("total",Total);
                            intent.putExtra("email",email);
                            intent.putExtra("unit",drug_unit);
                            startActivity(intent);

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                            Toast.makeText(PharmacistCheckoutActivity.this, "Your Order Could Not Be Placed", Toast.LENGTH_LONG).show();

                        }
                    });

                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // using onCreateOptionsMenu() to specify the options menu for an activity
        getMenuInflater().inflate(R.menu.pharmacist_contact, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.navigation_home:
                startActivity(new Intent(PharmacistCheckoutActivity.this,PharmacistHomeActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_orders:
                startActivity(new Intent(PharmacistCheckoutActivity.this,PharmacistViewOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_contact:
                startActivity(new Intent(PharmacistCheckoutActivity.this,PharmacistContactActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_ontheway_orders:
                startActivity(new Intent(PharmacistCheckoutActivity.this,PharmacistOntheWayOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_completed_orders:
                startActivity(new Intent(PharmacistCheckoutActivity.this,PharmacistOrderHistoryActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_logout:
                startActivity(new Intent(PharmacistCheckoutActivity.this,LoginActivity.class));
                break;
        }
        return false;
    }
}
