package com.example.pharmacy_management_system.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacy_management_system.Model.Order;
import com.example.pharmacy_management_system.PharmacistUpdateOrderActivity;
import com.example.pharmacy_management_system.R;

import java.util.ArrayList;

//using recycler view coz it supports most of the layout management such as vertical list, horizontal list, grids etc.
public class OnTheWayOrderAdapter extends RecyclerView.Adapter<OnTheWayOrderAdapter.ViewHolder>  {

    private ArrayList<Order> orderList;
    private Context context;

    //Constructors

    public OnTheWayOrderAdapter(ArrayList<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }


    //Overrided methods

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //setting the layout(design) to use when displaying order details
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.list_ontheway_order,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //final String order_id=String.valueOf(orderList.get(position).getId());
        final String order_name= orderList.get(position).getDrugname();
        //final String order_quantity=String.valueOf(orderList.get(position).getQuantity());
        final String order_total=String.valueOf(orderList.get(position).getTotal());
        final String order_price=String.valueOf(orderList.get(position).getPrice());
        final String order_email= orderList.get(position).getEmail();
        final String order_unit= orderList.get(position).getUnit();
        final String order_status= orderList.get(position).getStatus();
        final String order_date= orderList.get(position).getDate();

        //setting the values to view holder variables which will use those values to display "on the way order" details
        holder.orderid_View.setText(String.valueOf(orderList.get(position).getId()));
        holder.drugname_view.setText(order_name);
        holder.qnty_view.setText(String.valueOf(orderList.get(position).getQuantity()));
        holder.total_view.setText(order_total);
        holder.unit_view.setText(order_unit);
        holder.status_view.setText(order_status);
        holder.date_view.setText(order_date);

        holder.update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, PharmacistUpdateOrderActivity.class);

                //takes the retrieved order details to update activity page so they can be updated from there.(like confirmation page for update),Note: all details are sent in case of any need of all details
                intent.putExtra("drugname",order_name);
                intent.putExtra("drugprice",Integer.parseInt(order_price));
                intent.putExtra("total",Integer.parseInt(order_total));
                intent.putExtra("qnty",orderList.get(position).getQuantity());
                intent.putExtra("email",order_email);
                intent.putExtra("unit",order_unit);
                intent.putExtra("id",orderList.get(position).getId());
                intent.putExtra("status",order_status);
                intent.putExtra("date",order_date);

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }




    //Viewholder method

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView orderid_View, drugname_view, date_view, qnty_view, total_view, status_view, unit_view;
        private Button update_Btn;

        public ViewHolder(@NonNull View view)
        {

            super(view);
            orderid_View=itemView.findViewById(R.id.new_order_id_OTW);
            drugname_view=itemView.findViewById(R.id.drug_name_order_OTW);
            unit_view=itemView.findViewById(R.id.drug_unit_order_OTW);
            qnty_view=itemView.findViewById(R.id.drug_qnty_order_OTW);
            status_view=itemView.findViewById(R.id.drug_status_order_OTW);
            date_view=itemView.findViewById(R.id.date_order_OTW);
            total_view=itemView.findViewById(R.id.drug_total_order_OTW);

            update_Btn=itemView.findViewById(R.id.update_order_button_OTW);

        }
    }
}
