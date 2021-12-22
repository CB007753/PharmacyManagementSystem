package com.example.pharmacy_management_system.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacy_management_system.Model.Order;
import com.example.pharmacy_management_system.R;

import java.util.ArrayList;

//this is a recycler view adapter used to display order history in recycler view
//using recycler view coz it supports most of the layout management such as vertical list, horizontal list, grids etc.
public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {

    private final ArrayList<Order> orderList;
    private final Context context;

    //Constructors
    public OrderHistoryAdapter(ArrayList<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //setting the layout(design) to use when displaying order details
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.list_order_history,parent,false);

        OrderHistoryAdapter.ViewHolder viewHolder= new OrderHistoryAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        final String order_id=String.valueOf(orderList.get(position).getId());
        final String order_name= orderList.get(position).getDrugname();
        final String order_quantity=String.valueOf(orderList.get(position).getQuantity());
        final String order_total=String.valueOf(orderList.get(position).getTotal());
        final String order_unit= orderList.get(position).getUnit();
        final String order_status= orderList.get(position).getStatus();
        final String order_date= orderList.get(position).getDate();

        //setting the values to view holder variables which will use those values to display "on the way order" details
        holder.orderid_View.setText(order_id);
        holder.drugname_view.setText(order_name);
        holder.qnty_view.setText(order_quantity);
        holder.total_view.setText(order_total);
        holder.unit_view.setText(order_unit);
        holder.status_view.setText(order_status);
        holder.date_view.setText(order_date);

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }


    //Viewholder method

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView orderid_View, drugname_view, date_view, qnty_view, total_view, status_view, unit_view;

        public ViewHolder(@NonNull View view)
        {

            super(view);
            orderid_View = itemView.findViewById(R.id.new_order_id_history);
            drugname_view = itemView.findViewById(R.id.drug_name_order_history);
            unit_view = itemView.findViewById(R.id.drug_unit_order_history);
            qnty_view = itemView.findViewById(R.id.drug_qnty_order_history);
            status_view = itemView.findViewById(R.id.drug_status_order_history);
            date_view = itemView.findViewById(R.id.date_order_history);
            total_view = itemView.findViewById(R.id.drug_total_order_history);


        }
    }
}
