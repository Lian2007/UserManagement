package com.example.usermanagement;

import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.navigation.Navigation;
import java.util.Calendar;

public class TimePickerFragment extends Fragment {
    private TextView tvTime;
    private Button btnNext;
    private String selectedTime = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_picker, container, false);
        tvTime = view.findViewById(R.id.tvTime);
        btnNext = view.findViewById(R.id.btnNext);

        tvTime.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                    (view1, hourOfDay, minute) -> {
                        selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                        tvTime.setText(selectedTime);
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true);
            timePickerDialog.show();
        });

        btnNext.setOnClickListener(v -> {
            if (selectedTime.isEmpty()) {
                Toast.makeText(getActivity(), "Please select a time!", Toast.LENGTH_SHORT).show();
                return;
            }
            // Pass along all previous data
            Bundle bundle = getArguments() != null ? new Bundle(getArguments()) : new Bundle();
            bundle.putString("selectedTime", selectedTime);
            Navigation.findNavController(view).navigate(R.id.action_timePickerFragment_to_summaryFragment, bundle);
        });

        return view;
    }
}
