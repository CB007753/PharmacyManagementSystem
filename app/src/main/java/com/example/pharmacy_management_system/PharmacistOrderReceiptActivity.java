package com.example.pharmacy_management_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//this will be a receipt viewing page so navigation menu and app bar are not displayed
public class PharmacistOrderReceiptActivity extends AppCompatActivity {

    private TextView drugnametextview,totaltextview,qntytextview,unittextview,emailtextview;
    private Button home_Btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_order_receipt);

        final Intent intent=getIntent();
        final String drug_name=intent.getStringExtra("drugname");
        final String email=intent.getStringExtra("email");
        final String unit=intent.getStringExtra("unit");
        final  int total=intent.getIntExtra("total",0);
        final int quantity=intent.getIntExtra("qnty",0);

        drugnametextview = (TextView) findViewById(R.id.order_details_drug_name);
        unittextview = (TextView) findViewById(R.id.order_details_drug_unit);
        totaltextview = (TextView) findViewById(R.id.order_details_drug_total);
        qntytextview = (TextView) findViewById(R.id.order_details_drug_qnty);
        emailtextview = (TextView) findViewById(R.id.order_details_drug_email) ;

        home_Btn = (Button) findViewById(R.id.order_details_return_home_button);

        String drug_total = String.valueOf(total);
        String drug_qnty = String.valueOf(quantity);


        //setting the values retrieved from the last activity(checkout actvity)
        drugnametextview.setText(drug_name);
        unittextview.setText(unit);
        totaltextview.setText(drug_total);
        qntytextview.setText(drug_qnty);
        emailtextview.setText(email);


            home_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(PharmacistOrderReceiptActivity.this,PharmacistHomeActivity.class);
                    intent.putExtra("email",email);
                    startActivity(intent);

                }
            });


    }


}
