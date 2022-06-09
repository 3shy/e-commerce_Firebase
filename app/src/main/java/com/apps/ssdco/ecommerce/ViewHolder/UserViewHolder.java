package com.apps.ssdco.ecommerce.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.apps.ssdco.ecommerce.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView txtUserName, txtUserPhone, textPasword;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        txtUserName = itemView.findViewById(R.id.user_name);
        txtUserPhone = itemView.findViewById(R.id.user_phone);
        textPasword = itemView.findViewById(R.id.user_password);
    }
}
