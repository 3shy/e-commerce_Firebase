package com.apps.ssdco.ecommerce.admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.ssdco.ecommerce.Model.Orders;
import com.apps.ssdco.ecommerce.Model.Users;
import com.apps.ssdco.ecommerce.Prevalent.Prevalent;
import com.apps.ssdco.ecommerce.R;
import com.apps.ssdco.ecommerce.ViewHolder.OrderViewHolder;
import com.apps.ssdco.ecommerce.ViewHolder.UserViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAllUsersActivity extends AppCompatActivity {

    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_all_users);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ProductsRef = FirebaseDatabase.getInstance(Prevalent.URL).getReference().child("Users");


        FirebaseRecyclerOptions<Users> options =
                new FirebaseRecyclerOptions.Builder<Users>()
                        .setQuery(ProductsRef, Users.class)
                        .build();


        FirebaseRecyclerAdapter<Users, UserViewHolder> adapter =
                new FirebaseRecyclerAdapter<Users, UserViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull final Users model)
                    {


                    holder.txtUserName.setText("Name: " + model.getName());
                    holder.txtUserPhone.setText("Phone: " + model.getPhone());
                    holder.textPasword.setText("Password: " + model.getPassword());




                    }

                    @NonNull
                    @Override
                    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist, parent, false);
                        UserViewHolder holder = new UserViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }
}