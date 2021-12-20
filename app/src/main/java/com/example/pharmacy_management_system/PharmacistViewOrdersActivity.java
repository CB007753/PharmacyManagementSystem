package com.example.pharmacy_management_system;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pharmacy_management_system.Client.API_Interface;
import com.example.pharmacy_management_system.Client.Backend_API;
import com.example.pharmacy_management_system.Model.BuyDrugs;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//this is where the drugs will be listed which can be bought by the pharmacist.
public class PharmacistViewOrdersActivity  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private List<BuyDrugs> drugList=new ArrayList<>();
    private API_Interface api_interface;
    GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_view_order);

        gridView = findViewById(R.id.pharmacist_view_order_gridview);

        viewBuyDrugs();

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

    private void viewBuyDrugs(){

        api_interface = Backend_API.getRetrofit().create(API_Interface.class);
        Call<List<BuyDrugs>> call = api_interface.getBuyDrugs();
        call.enqueue(new Callback<List<BuyDrugs>>() {
            @Override
            public void onResponse(Call<List<BuyDrugs>> call, Response<List<BuyDrugs>> response) {

                if(response.isSuccessful())
                {

                    Toast.makeText(PharmacistViewOrdersActivity.this,"Drug List Displayed Successfully",Toast.LENGTH_SHORT).show();
                    drugList=response.body();
                    PharmacistViewOrdersActivity.DrugViewAdapter drugViewAdapter=new PharmacistViewOrdersActivity.DrugViewAdapter(drugList,PharmacistViewOrdersActivity.this);
                    gridView.setAdapter(drugViewAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<BuyDrugs>> call, Throwable t) {
                Toast.makeText(PharmacistViewOrdersActivity.this,"Buy Drugs List Failed To Display",Toast.LENGTH_LONG).show();

            }
        });
    }


    public class DrugViewAdapter extends BaseAdapter {


        private List<BuyDrugs> drugsList;
        private LayoutInflater layoutInflater;
        private Context context;

        public DrugViewAdapter(List<BuyDrugs> drugsList, Context context) {
            this.drugsList = drugsList;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            this.context = context;
        }

        @Override
        public int getCount() {
            return drugList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null)
            {
                convertView=layoutInflater.inflate(R.layout.list_buy_drugs,parent,false);
            }

            final TextView nameView=convertView.findViewById(R.id.buy_drug_name);
            final TextView unitView=convertView.findViewById(R.id.buy_drug_unit);
            final TextView descView=convertView.findViewById(R.id.desc_buy_drug);
            final TextView priceView = convertView.findViewById(R.id.buy_drug_price);

            nameView.setText(drugsList.get(position).getName());
            unitView.setText(drugsList.get(position).getUnit());
            priceView.setText("Rs."+drugsList.get(position).getPrice());
            descView.setText(drugsList.get(position).getDescription());

            final int price=drugsList.get(position).getPrice();

            Intent receive_email= getIntent();
            final String email=receive_email.getStringExtra("email");

            //buy button in try and catch
            try{
                Button buy_now_button=convertView.findViewById(R.id.buy_now_button);
                buy_now_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //passing the selected drug details to the next activity, passing the current logged in user's email
                        Intent intent=new Intent(context,PharmacistCheckoutActivity.class);
                        intent.putExtra("drugname",nameView.getText().toString());
                        intent.putExtra("drugprice",priceView.getText().toString());
                        intent.putExtra("drugunit",unitView.getText().toString());
                        intent.putExtra("price",price);
                        intent.putExtra("desc",descView.getText().toString());

                        intent.putExtra("email",email);
                        context.startActivity(intent);

                    }
                });
            }
            catch(Exception e) {
                //Using this to catch the execption coz my app crashes when buying drugs
                Toast.makeText(PharmacistViewOrdersActivity.this, "Error: "+e , Toast.LENGTH_LONG).show();

            }



            return convertView;
        }
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
                startActivity(new Intent(PharmacistViewOrdersActivity.this,PharmacistHomeActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_orders:
                startActivity(new Intent(PharmacistViewOrdersActivity.this,PharmacistViewOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_contact:
                startActivity(new Intent(PharmacistViewOrdersActivity.this,PharmacistContactActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_ontheway_orders:
                startActivity(new Intent(PharmacistViewOrdersActivity.this,PharmacistOntheWayOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_completed_orders:
                startActivity(new Intent(PharmacistViewOrdersActivity.this,PharmacistOrderHistoryActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_logout:
                startActivity(new Intent(PharmacistViewOrdersActivity.this,LoginActivity.class));
                break;
        }
        return false;
    }
}
