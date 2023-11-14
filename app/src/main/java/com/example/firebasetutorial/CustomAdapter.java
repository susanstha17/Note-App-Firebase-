package com.example.firebasetutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter <CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList<DataModel> dm;
    public CustomAdapter(HomeActivity homeActivity, ArrayList<DataModel> arraylist) {
        context = homeActivity;
        dm = arraylist;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.singleitem,null);
       MyViewHolder myViewHolder = new MyViewHolder(v);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        DataModel data = dm.get(position);
        holder.name.setText(data.getName());
        holder.address.setText(data.getAddress());
        holder.phone.setText(data.getPhone());
    }

    @Override
    public int getItemCount() {
        return dm.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView name,address,phone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
