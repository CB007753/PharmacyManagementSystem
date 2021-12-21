package com.example.pharmacy_management_system;

import  android.content.Context;
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
import com.example.pharmacy_management_system.Model.Drugs;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//implementing nav view to use "onNavigationItemSelected" methods to add navigations for menu items
public class PharmacistHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private List<Drugs> drugList=new ArrayList<>();
    private API_Interface api_interface;
    GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_home);

        gridView = findViewById(R.id.pharmacist_menu_gridview);

        viewAllDrugs();


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

        private void viewAllDrugs(){

        api_interface = Backend_API.getRetrofit().create(API_Interface.class);

            Call<List<Drugs>> call = api_interface.getAllDrugs();
            call.enqueue(new Callback<List<Drugs>>() {
                @Override
                public void onResponse(Call<List<Drugs>> call, Response<List<Drugs>> response) {

                    if(response.isSuccessful())
                    {

                        Toast.makeText(PharmacistHomeActivity.this,"Drug List Displayed Successfully",Toast.LENGTH_SHORT).show();
                        drugList=response.body();
                        PharmacistHomeActivity.DrugViewAdapter drugViewAdapter=new PharmacistHomeActivity.DrugViewAdapter(drugList,PharmacistHomeActivity.this);
                        gridView.setAdapter(drugViewAdapter);

                    }

                }

                @Override
                public void onFailure(Call<List<Drugs>> call, Throwable t) {

                    Toast.makeText(PharmacistHomeActivity.this,"Drug List Failed To Display",Toast.LENGTH_LONG).show();


                }
            });

        }

    //using base adapter instead of Array adapter to reduce the coding effort in implementation and to get the flexibility i need in my data being displayed.
    public class DrugViewAdapter extends BaseAdapter{

        private List<Drugs> drugsList;
        private LayoutInflater layoutInflater;


        public DrugViewAdapter(List<Drugs> drugsList, Context context) {
            this.drugsList = drugsList;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
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
                convertView=layoutInflater.inflate(R.layout.list_all_drugs,parent,false);

            }

            final TextView nameView=convertView.findViewById(R.id.drug_name);
            final TextView unitView=convertView.findViewById(R.id.drug_unit);
            final TextView descView=convertView.findViewById(R.id.desc_drug);
            final TextView quantity = convertView.findViewById(R.id.drug_qnty);

            nameView.setText(drugsList.get(position).getName());
            unitView.setText("Unit: "+drugsList.get(position).getUnit());
            quantity.setText("Quantity: 100 items");
            descView.setText(drugsList.get(position).getDescription());

            //this button is used as a line to seperate drugs
            Button drug_seperator=convertView.findViewById(R.id.drug_seperator);


            return convertView;

        }
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
            case R.id.navigation_orders:
                startActivity(new Intent(PharmacistHomeActivity.this,PharmacistViewOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_contact:
                startActivity(new Intent(PharmacistHomeActivity.this,PharmacistContactActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_ontheway_orders:
                startActivity(new Intent(PharmacistHomeActivity.this,PharmacistOntheWayOrdersActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_completed_orders:
                startActivity(new Intent(PharmacistHomeActivity.this,PharmacistOrderHistoryActivity.class).putExtra("email",getIntent().getStringExtra("email")));
                break;
            case R.id.navigation_logout:
                startActivity(new Intent(PharmacistHomeActivity.this,LoginActivity.class));
                break;
        }
        return false;
    }
}
