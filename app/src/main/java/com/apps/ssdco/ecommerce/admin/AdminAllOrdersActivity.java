package com.apps.ssdco.ecommerce.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apps.ssdco.ecommerce.ConfirmFinalOrderActivity;
import com.apps.ssdco.ecommerce.HomeActivity;
import com.apps.ssdco.ecommerce.Model.Orders;
import com.apps.ssdco.ecommerce.Model.Products;
import com.apps.ssdco.ecommerce.Prevalent.Prevalent;
import com.apps.ssdco.ecommerce.ProductDetailsActivity;
import com.apps.ssdco.ecommerce.R;
import com.apps.ssdco.ecommerce.ViewHolder.OrderViewHolder;
import com.apps.ssdco.ecommerce.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AdminAllOrdersActivity extends AppCompatActivity {

    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Orders, OrderViewHolder> adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_all_orders);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ProductsRef = FirebaseDatabase.getInstance(Prevalent.URL).getReference().child("Orders");

        FirebaseRecyclerOptions<Orders> options =
                new FirebaseRecyclerOptions.Builder<Orders>()
                        .setQuery(ProductsRef, Orders.class)
                        .build();


        adapter =
                new FirebaseRecyclerAdapter<Orders, OrderViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull OrderViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Orders model)
                    {

                        holder.user_name.setText("Name: " + model.getName());
                        holder.user_phone.setText("Phone: " + model.getPhone());
                        holder.user_address.setText("Address: " + model.getAddress());
                        holder.user_total_price.setText("Total: " + model.getTotal());
                        holder.user_date.setText("Date: " + model.getDate());
                        holder.user_time.setText("Time: " + model.getTime());
                        holder.user_status.setText("Status: " + model.getState());

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                final DatabaseReference OrdersRef=FirebaseDatabase.getInstance(Prevalent.URL).getReference()
                                        .child("Orders")
                                        .child(adapter.getRef(position).getKey());

                                HashMap<String ,Object> orderMap=new HashMap<>();
                                orderMap.put("total",model.getTotal());
                                orderMap.put("Name",model.getName());
                                orderMap.put("Phone",model.getPhone());
                                orderMap.put("Address",model.getAddress());
                                orderMap.put("City",model.getCity());
                                orderMap.put("date",model.getDate());
                                orderMap.put("time",model.getTime());
                                orderMap.put("State","shipped");

                                OrdersRef.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(AdminAllOrdersActivity.this, "Order Shipped Successfully", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                            }
                        });





                    }

                    @NonNull
                    @Override
                    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderlist, parent, false);
                        OrderViewHolder holder = new OrderViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}