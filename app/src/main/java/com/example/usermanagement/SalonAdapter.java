package com.example.usermanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class SalonAdapter extends RecyclerView.Adapter<SalonAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Salon> restList;
    private OnSalonClickListener listener;

    public interface OnSalonClickListener {
        void onSalonClick(Salon salon);
    }

    public SalonAdapter(Context context, ArrayList<Salon> restList, OnSalonClickListener listener) {
        this.context = context;
        this.restList = restList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.salon_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Salon rest = restList.get(position);
        holder.tvName.setText(rest.getName());
        holder.tvPhone.setText(rest.getPhone());
        holder.itemView.setOnClickListener(v -> listener.onSalonClick(rest));
    }

    @Override
    public int getItemCount() {
        return restList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameSalon);
            tvPhone = itemView.findViewById(R.id.tvPhoneSalon);
        }
    }
}
