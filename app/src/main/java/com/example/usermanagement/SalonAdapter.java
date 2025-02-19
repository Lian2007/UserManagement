package com.example.usermanagement;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SalonAdapter extends RecyclerView.Adapter<SalonAdapter.MyViewHolder> {
    Context context;
    ArrayList<Salon> restList;
    private FirebaseServices fbs;

    public SalonAdapter(Context context, ArrayList<Salon> restList) {
        this.context = context;
        this.restList = restList;
        this.fbs = FirebaseServices.getInstance();
    }

    @NonNull
    @Override
    public SalonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v= LayoutInflater.from(context).inflate(R.layout.salon_item,parent,false);
        return  new SalonAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SalonAdapter.MyViewHolder holder, int position) {
        Salon rest = restList.get(position);
        holder.tvName.setText(rest.getName());
        holder.tvPhone.setText(rest.getPhone());
    }

    @Override
    public int getItemCount(){
        return restList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvPhone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvNameSalon);
            tvPhone=itemView.findViewById(R.id.tvPhoneSalon);

        }
    }
}
