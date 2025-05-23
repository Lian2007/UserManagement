package com.example.usermanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TreatmentAdapter extends RecyclerView.Adapter<TreatmentAdapter.MyViewHolder> {
    private ArrayList<Treatment> treatmentList;
    private OnTreatmentClickListener listener;

    public interface OnTreatmentClickListener {
        void onTreatmentClick(Treatment treatment);
    }

    public TreatmentAdapter(ArrayList<Treatment> treatmentList, OnTreatmentClickListener listener) {
        this.treatmentList = treatmentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.treatment_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Treatment t = treatmentList.get(position);
        holder.tvName.setText(t.getName());
        holder.tvDescription.setText(t.getDescription());
        holder.tvPrice.setText("₪" + t.getPrice());
        holder.tvTime.setText(t.getTime() + " hours");

        // TODO: Load image to holder.imageViewTreatmentPic if you want
        // For now, skipping image loading (e.g., use Glide or Picasso)

        holder.itemView.setOnClickListener(v -> listener.onTreatmentClick(t));
    }

    @Override
    public int getItemCount() {
        return treatmentList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDescription, tvPrice, tvTime;
        ImageView imageViewTreatmentPic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textViewTreatmentName);
            tvDescription = itemView.findViewById(R.id.textViewTreatmentDescription);
            tvPrice = itemView.findViewById(R.id.textViewTreatmentPrice);
            tvTime = itemView.findViewById(R.id.textViewTreatmentTime);
            imageViewTreatmentPic = itemView.findViewById(R.id.imageViewTreatmentPic);
        }
    }
}
