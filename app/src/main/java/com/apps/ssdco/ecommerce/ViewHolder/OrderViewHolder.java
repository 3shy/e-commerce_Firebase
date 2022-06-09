package com.apps.ssdco.ecommerce.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.apps.ssdco.ecommerce.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    public TextView user_name, user_phone, user_address, user_total_price, user_status , user_date , user_time;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        user_name = itemView.findViewById(R.id.user_name);
        user_phone = itemView.findViewById(R.id.user_phone);
        user_address = itemView.findViewById(R.id.user_address);
        user_total_price = itemView.findViewById(R.id.user_amount);
        user_status = itemView.findViewById(R.id.user_state);
        user_date = itemView.findViewById(R.id.user_date);
        user_time = itemView.findViewById(R.id.user_time);

    }
}
