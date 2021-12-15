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

//implementing nav view to use "onNavigationItemSelected" methods to add navigations for menu items
public class PharmacistHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

   // private List<Drugs> drugList=new ArrayList<>();
    //private TextView textViewResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_home);

        //-----------------------------------

//        textViewResult = findViewById(R.id.text_view_result);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        API_Interface api_interface = retrofit.create(API_Interface.class);
//
//        Call<List<API_Model>> call = api_interface.getPosts();
//
//        call.enqueue(new Callback<List<API_Model>>() {
//            @Override
//            public void onResponse(Call<List<API_Model>> call, Response<List<API_Model>> response) {
//                if(!response.isSuccessful()){
//                    textViewResult.setText("Code: " + response.code());
//                    return;
//                }
//
//                List<API_Model> posts = response.body();
//
//                for (API_Model post : posts){
//                    String content ="";
//                    content += "ID: " + post.getId() + "\n";
//                    content += "User ID: " + post.getUserId() + "\n";
//                    content += "Title: " + post.getTitle() + "\n";
//                    content += "Text: " + post.getText() + "\n\n";
//
//                    textViewResult.append(content);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<API_Model>> call, Throwable t) {
//                        textViewResult.setText(t.getMessage());
//            }
//        });

        //------------------------------------------


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
        getMenuInflater().inflate(R.menu.pharmacist_contact, menu);
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
