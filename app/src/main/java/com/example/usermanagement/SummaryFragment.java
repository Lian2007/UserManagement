package com.example.usermanagement;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class SummaryFragment extends Fragment {
    private TextView tvSummary;
    private Button btnConfirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        tvSummary = view.findViewById(R.id.tvSummary);
        btnConfirm = view.findViewById(R.id.btnConfirm);

        // Show summary info when fragment loads
        Bundle args = getArguments();

        String salon = "";
        if (args != null && args.containsKey("salon")) {
            Salon selectedSalon = (Salon) args.getSerializable("salon");
            if (selectedSalon != null) {
                salon = selectedSalon.getName();
            }
        }

        String treatmentName = "";
        if (args != null && args.containsKey("selectedTreatment")) {
            Treatment selectedTreatment = (Treatment) args.getSerializable("selectedTreatment");
            if (selectedTreatment != null) {
                treatmentName = selectedTreatment.getName();
            }
        }

        String date = (args != null) ? args.getString("selectedDate", "") : "";
        String time = (args != null) ? args.getString("selectedTime", "") : "";

        String summary = "Salon: " + salon + "\nTreatment: " + treatmentName + "\nDate: " + date + "\nTime: " + time;
        tvSummary.setText(summary);

        btnConfirm.setOnClickListener(v -> {
            // Retrieve latest values INSIDE lambda
            String currentSalon = "";
            if (getArguments() != null && getArguments().containsKey("salon")) {
                Salon selectedSalon = (Salon) getArguments().getSerializable("salon");
                if (selectedSalon != null) {
                    currentSalon = selectedSalon.getName();
                }
            }

            String currentTreatment = "";
            if (getArguments() != null && getArguments().containsKey("selectedTreatment")) {
                Treatment selectedTreatment = (Treatment) getArguments().getSerializable("selectedTreatment");
                if (selectedTreatment != null) {
                    currentTreatment = selectedTreatment.getName();
                }
            }

            String currentDate = (getArguments() != null) ? getArguments().getString("selectedDate", "") : "";
            String currentTime = (getArguments() != null) ? getArguments().getString("selectedTime", "") : "";

            // Prepare booking data for Firestore
            Map<String, Object> booking = new HashMap<>();
            booking.put("salon", currentSalon);
            booking.put("treatment", currentTreatment);
            booking.put("date", currentDate);
            booking.put("time", currentTime);
            // Optionally add userId: booking.put("userId", FirebaseServices.getInstance().getAuth().getUid());

            FirebaseFirestore db = FirebaseServices.getInstance().getFire();
            db.collection("bookings")
                    .add(booking)
                    .addOnSuccessListener(documentReference -> {
                        tvSummary.setText("Booking confirmed! Thank you.");
                        Toast.makeText(getContext(), "Booking saved!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Failed to save booking: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        });

        return view;
    }
}
